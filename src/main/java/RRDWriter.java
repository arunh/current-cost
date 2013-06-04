import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class RRDWriter {

    private static Logger log = LoggerFactory.getLogger(RRDWriter.class);
    private final LinkedBlockingQueue<RealtimeMessage> msgQueue;

    public RRDWriter(LinkedBlockingQueue<RealtimeMessage> msgQueue) {
        this.msgQueue = msgQueue;
    }

    public void start() {
        new Thread(new Consumer(), "rrdwriter").start();
    }

    private final class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                RealtimeMessage rtMsg = null;
                try {
                    rtMsg = msgQueue.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.debug("Read real time message from queue: {}", rtMsg);
            }
        }
    }
}
