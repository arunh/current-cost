import com.thoughtworks.xstream.XStream;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue<RealtimeMessage> msgQueue = new LinkedBlockingQueue<RealtimeMessage>();
        XStream xStream = new XStream();
        xStream.processAnnotations(RealtimeMessage.class);

        SerialReader serialReader = new SerialReader(msgQueue, xStream);
        RRDWriter rrdWriter = new RRDWriter(msgQueue);

//        serialReader.start();
        msgQueue.put(new RealtimeMessage());
        rrdWriter.start();
    }

}
