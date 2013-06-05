package uk.co.arunhorne.currentcost.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("msg")
public class RealtimeMessage {

    private String src;

    @XStreamAlias("dsb")
    @XStreamConverter(ZeroPaddedStringToInt.class)
    private int daysSinceBirth;

    private String time;

    @XStreamAlias("tmpr")
    private float temperature;

    private int sensor;

    private int id;

    private int type;

    private Reading ch1;

    public String getSrc() {
        return src;
    }

    public int getDaysSinceBirth() {
        return daysSinceBirth;
    }

    public String getTime() {
        return time;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getSensor() {
        return sensor;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public Reading getCh1() {
        return ch1;
    }

}
