package uk.co.arunhorne.currentcost.model;

public class WattsAndTemperature {

    private final float temperature;
    private final int watts;

    public WattsAndTemperature(int watts, float temperature) {
        this.watts = watts;
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getWatts() {
        return watts;
    }
}
