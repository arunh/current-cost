package uk.co.arunhorne.currentcost;

import com.thoughtworks.xstream.XStream;
import uk.co.arunhorne.currentcost.model.WattsAndTemperature;
import uk.co.arunhorne.currentcost.producer.MessageProducer;
import uk.co.arunhorne.currentcost.rrdtool.CurrentCostRRD;
import uk.co.arunhorne.currentcost.rrdtool.MessageConsumer;
import uk.co.arunhorne.currentcost.xml.RealtimeMessage;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue<WattsAndTemperature> msgQueue = new LinkedBlockingQueue<WattsAndTemperature>();
        XStream xStream = new XStream();
        xStream.processAnnotations(RealtimeMessage.class);

        MessageProducer messageProducer = new MessageProducer(msgQueue, xStream);
        messageProducer.start();

        Properties rrdProps = new Properties();
        rrdProps.load(Main.class.getResourceAsStream("/rrdtool.properties"));
        CurrentCostRRD ccRRD = new CurrentCostRRD(rrdProps);

        MessageConsumer messageConsumer = new MessageConsumer(msgQueue, ccRRD);
        messageConsumer.start();
    }

}
