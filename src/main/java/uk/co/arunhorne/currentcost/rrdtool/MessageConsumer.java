package uk.co.arunhorne.currentcost.rrdtool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.arunhorne.currentcost.model.WattsAndTemperature;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageConsumer {

    private static Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    private final LinkedBlockingQueue<WattsAndTemperature> msgQueue;
    private final CurrentCostRRD currentCostRRD;

    public MessageConsumer(LinkedBlockingQueue<WattsAndTemperature> msgQueue, CurrentCostRRD currentCostRRD) {
        this.msgQueue = msgQueue;
        this.currentCostRRD = currentCostRRD;
    }

    public void start() {
        new Thread(new Consumer(), "rrdwriter").start();
    }

    private final class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    WattsAndTemperature wtMsg = msgQueue.take();
                    log.debug("Read real time message from queue: {}", wtMsg);
                    currentCostRRD.write(wtMsg);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
