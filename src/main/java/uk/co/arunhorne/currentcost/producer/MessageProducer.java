package uk.co.arunhorne.currentcost.producer;

import com.thoughtworks.xstream.XStream;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.arunhorne.currentcost.model.WattsAndTemperature;
import uk.co.arunhorne.currentcost.xml.RealtimeMessage;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageProducer {

    private static Logger log = LoggerFactory.getLogger(MessageProducer.class);
    private SerialPort serialPort;

    private final LinkedBlockingQueue<WattsAndTemperature> msgQueue;
    private final XStream xStream;

    public MessageProducer(LinkedBlockingQueue<WattsAndTemperature> msgQueue, XStream xStream) {
        this.msgQueue = msgQueue;
        this.xStream = xStream;
    }

    public void start() {
        serialPort = new SerialPort("/dev/ttyUSB0");
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_57600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            int mask = SerialPort.MASK_RXCHAR;
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(new SerialPortReader());
        } catch (SerialPortException e) {
            log.error("Failed to setup serial port", e);
        }
    }

    private final class SerialPortReader implements SerialPortEventListener {

        private StringBuilder sb = new StringBuilder(2048);

        public void serialEvent(SerialPortEvent event) {
            int val = event.getEventValue();
            if (val > 0) {
                try {
                    byte buffer[] = serialPort.readBytes(val);
                    sb.append(new String(buffer));
                    if (buffer[buffer.length - 1] == '\n') {
                        final String tmpBuffer = sb.toString();
                        final String msg = tmpBuffer.substring(0, tmpBuffer.length() - 1);
                        if (msg.contains("<hist>")) {
                            log.trace("History message was ignored: {}");
                        }
                        else {
                            parseAndEnqueue(msg);
                        }
                        //Reset string buffer for next message
                        sb = new StringBuilder(2048);
                    }
                } catch (SerialPortException e) {
                    log.error("Error reading bytes from serial port", e);
                }
            }
        }

        private void parseAndEnqueue(String msg) {
            RealtimeMessage rtMsg = (RealtimeMessage)xStream.fromXML(msg);
            try {
                msgQueue.put(new WattsAndTemperature(rtMsg.getCh1().getWatts(), rtMsg.getTemperature()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.debug("Wrote to queue: {}", rtMsg);

        }
    }
}

