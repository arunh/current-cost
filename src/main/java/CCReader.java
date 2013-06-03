import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCReader {

    private static SerialPort serialPort;
    private static Logger log = LoggerFactory.getLogger(CCReader.class);

    public static void main(String[] args) {

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

    private static final class SerialPortReader implements SerialPortEventListener {

        private final StringBuilder sb = new StringBuilder(2048);

        public void serialEvent(SerialPortEvent event) {
            int val = event.getEventValue();
            if (val > 0) {
                try {
                    byte buffer[] = serialPort.readBytes(val);
                    sb.append(new String(buffer));
                    if (buffer[buffer.length - 1] == '\n') {
                        final String msg = sb.toString();
                        if (msg.contains("<hist>")) {
                            log.trace("History message read (ignoring): {}");
                        }
                        else {
                            log.debug("Real time message reader: {}", msg);
                        }

                    }
                } catch (SerialPortException e) {
                    log.error("Error reading bytes from serial port", e);
                }
            }
        }
    }
}

