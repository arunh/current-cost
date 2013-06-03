import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("msg")
public class RealtimeMessage {

    private String src;

    @XStreamAlias("dsb")
    private String daysSinceBirth;

    private String time;

    @XStreamAlias("tmpr")
    private float temperature;

    private int sensor;

    private int id;

    private int type;

    private Reading ch1;

}
