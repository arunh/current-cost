import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.Converter;

public class Reading {

    @XStreamConverter(ZeroPaddedStringToInt.class)
    private int watts;

    @Override
    public String toString() {
        return "Reading{" +
                "watts=" + watts +
                '}';
    }

}
