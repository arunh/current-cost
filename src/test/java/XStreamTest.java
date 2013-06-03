import com.thoughtworks.xstream.XStream;
import org.junit.Test;

public class XStreamTest {

    private static final String MSG_REAL_TIME = "<msg><src>CC128-v1.16</src><dsb>00902</dsb><time>21:36:17</time><tmpr>26.0</tmpr><sensor>0</sensor><id>00077</id><type>1</type><ch1><watts>00362</watts></ch1></msg>";

    @Test
    public void testRealTime() {
        XStream xs = new XStream();
        xs.processAnnotations(RealtimeMessage.class);
        RealtimeMessage msg = (RealtimeMessage)xs.fromXML(MSG_REAL_TIME);
    }

}
