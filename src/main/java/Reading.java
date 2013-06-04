import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.Converter;

public class Reading {

    @XStreamConverter(ZeroPaddedStringToInt.class)
    private String watts;

    @Override
    public String toString() {
        return "Reading{" +
                "watts=" + watts +
                '}';
    }

}
