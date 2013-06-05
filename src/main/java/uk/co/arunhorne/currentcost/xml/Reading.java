package uk.co.arunhorne.currentcost.xml;

import com.thoughtworks.xstream.annotations.XStreamConverter;

public class Reading {

    @XStreamConverter(ZeroPaddedStringToInt.class)
    private int watts;

    public int getWatts() {
        return watts;
    }

}
