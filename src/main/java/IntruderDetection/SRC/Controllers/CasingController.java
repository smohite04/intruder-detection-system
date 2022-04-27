package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.CasingMode;
import IntruderDetection.Sensors.CasingSensor;
import IntruderDetection.Sensors.CasingSensorObserver;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class CasingController implements CasingSensorObserver {

    private final CasingSensor casingSensor;
    private final String debugTag;
    private final List<CasingSensorObserver> casingSensorObservers;
    private CasingMode casingMode = CasingMode.open;
    private int ignoreSensorInput = 0;

    public CasingController(CasingSensor casingSensor) {
        this.debugTag = this.getClass().getSimpleName();
        this.casingSensorObservers = new LinkedList<>();
        this.casingSensor = casingSensor;
    }

    public void registerListener(CasingSensorObserver casingSensorObserver) {
        this.casingSensorObservers.add(casingSensorObserver);
    }

    private void notifyObservers(Boolean enclosed) {
        for (CasingSensorObserver casingSensorObserver : casingSensorObservers) {
            casingSensorObserver.notify(enclosed);
        }
    }

    public void handleCasingOfObject(boolean toEnclose) {
        if (toEnclose && casingMode != CasingMode.enclosing && casingMode != CasingMode.enclosed) {
            if (casingMode == CasingMode.opening) {
                ignoreSensorInput++;
            }
            out.println("Casing Enclosing");
            casingMode = CasingMode.enclosing;
            casingSensor.instruct(true);
        }
        if (!toEnclose && casingMode != CasingMode.open && casingMode != CasingMode.opening) {
            if (casingMode == CasingMode.enclosing) {
                ignoreSensorInput++;
            }
            out.println("Casing Opening");
            casingMode = CasingMode.opening;
            casingSensor.instruct(false);
        }
        // handleCasingDoneAcknowledgement(false);
        return;
    }

    public void handleCasingDoneAcknowledgement(boolean sensorIn) {
            if (ignoreSensorInput == 0) {
                if (casingMode == CasingMode.opening) {
                    casingMode = CasingMode.open;
                    notifyObservers(false);
                } else if (casingMode == CasingMode.enclosing) {
                    casingMode = CasingMode.enclosed;
                    notifyObservers(true);
                }
            } else {
                ignoreSensorInput--;
            }

        return;
    }

    @Override
    public void notify(Boolean enclosed) {
        handleCasingDoneAcknowledgement(enclosed);
    }

    public CasingMode getCasingMode() {
        return casingMode;
    }

    public void setCasingMode(CasingMode casingMode) {
        this.casingMode = casingMode;
    }
}
