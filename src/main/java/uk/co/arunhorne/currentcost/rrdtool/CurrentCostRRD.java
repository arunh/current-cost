package uk.co.arunhorne.currentcost.rrdtool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.arunhorne.currentcost.model.WattsAndTemperature;

import java.io.IOException;
import java.util.Properties;

public class CurrentCostRRD {

    private static Logger log = LoggerFactory.getLogger(CurrentCostRRD.class);

    private final String rrdExecutable;
    private final String rrdDbFile;

    public CurrentCostRRD(Properties properties) {
        rrdExecutable = properties.getProperty("rrdtool.executable");
        rrdDbFile = properties.getProperty("rrdtool.db.file");
    }
    
    public void write(WattsAndTemperature wattsAndTemperature) {
        final String command = createUpdateCommand(wattsAndTemperature);
        executeSystemCommnd(command);
    }

    //TODO: What if error here?
    private void executeSystemCommnd(String command) {
        Runtime r = Runtime.getRuntime();
        log.debug("About to execute system command: {}", command);
        try {
            Process p = r.exec(command);
            p.waitFor();
            log.debug("Return code was: {}", p.exitValue());
        } catch (IOException e) {
            log.error("Failed to execute command", e);
        }
        catch (InterruptedException e) {
            log.error("Interrupted while waiting for command", e);
            Thread.currentThread().interrupt();
        }
    }

    //TODO: Use real time instead of N so log file can be replayed
    private String createUpdateCommand(WattsAndTemperature wattsAndTemperature) {
        return String.format("%s update %s N:%s:%s", rrdExecutable, rrdDbFile,
            wattsAndTemperature.getWatts(), wattsAndTemperature.getTemperature());
    }

}
