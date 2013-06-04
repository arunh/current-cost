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

    @Override
    public String toString() {
        return "RealtimeMessage{" +
                "src='" + src + '\'' +
                ", daysSinceBirth='" + daysSinceBirth + '\'' +
                ", time='" + time + '\'' +
                ", temperature=" + temperature +
                ", sensor=" + sensor +
                ", id=" + id +
                ", type=" + type +
                ", ch1=" + ch1 +
                '}';
    }
}
