import com.thoughtworks.xstream.XStream;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class SerialReader {

    private static Logger log = LoggerFactory.getLogger(SerialReader.class);
    private SerialPort serialPort;

    private final LinkedBlockingQueue<RealtimeMessage> msgQueue;
    private final XStream xStream;

    public SerialReader(LinkedBlockingQueue<RealtimeMessage> msgQueue, XStream xStream) {
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
                            RealtimeMessage rtMsg = (RealtimeMessage)xStream.fromXML(msg);
                            try {
                                msgQueue.put(rtMsg);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            log.debug("Wrote real time message to queue: {}", rtMsg);
                        }
                        //Reset string buffer for next message
                        sb = new StringBuilder(2048);
                    }
                } catch (SerialPortException e) {
                    log.error("Error reading bytes from serial port", e);
                }
            }
        }
    }
}

