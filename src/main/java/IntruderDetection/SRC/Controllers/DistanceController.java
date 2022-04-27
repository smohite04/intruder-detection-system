package IntruderDetection.SRC.Controllers;

import IntruderDetection.Constants;
import IntruderDetection.Sensors.CasingSensorObserver;

import static java.lang.System.out;

public class DistanceController implements CasingSensorObserver {

    private final AlarmController alarmController;
    private final CasingController casingController;
    private final float casingThreshold = Constants.CASING_THRESHOLD;
    private final float alarmThreshold = Constants.ALARM_THRESHOLD;
    private boolean enclosed = false;

    private final String debugTag;

    public DistanceController(AlarmController alarmController, CasingController casingController) {
        this.alarmController = alarmController;
        this.casingController = casingController;
        debugTag = this.getClass().getSimpleName();
    }

    // distance will be -1 if invoked by casing controller
    public void computeAction(Float distance, boolean enclosedIn) {
        if (distance !=null){
            if(distance >= 0) {
                verifyDistanceAndRaiseAlert(distance);
            }else{
                enclosed = enclosedIn;
            }
        } else{
            verifyDistanceAndRaiseAlert(null);
        }
        return;
    }

    private void verifyDistanceAndRaiseAlert(Float distance) {
        out.println("Casing:\t" + this.casingController.getCasingMode());
        if(distance != null){
            if (!enclosed && distance <= casingThreshold) {
                casingController.handleCasingOfObject(true);
                alarmController.raiseAlarm(true);
            } else if (distance <= alarmThreshold) {
                alarmController.raiseAlarm(true);
            } else if (distance > alarmThreshold) {
                alarmController.raiseAlarm(false);
                if(enclosed) {
                    casingController.handleCasingOfObject(false);
                }
            }
        } else {
            alarmController.raiseAlarm(false);
            if(enclosed) {
                casingController.handleCasingOfObject(false);
            }
        }
        return;
    }

    @Override
    public void notify(Boolean enclosed) {
        computeAction((float) -1, enclosed);
    }
}
