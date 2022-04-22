package IntruderDetection.SRC.Controllers;

import IntruderDetection.Constants;

public class DistanceController {

	private float casingThreshold = Constants.CASING_THRESHOLD;
	private float alarmThreshold = Constants.ALARM_THRESHOLD;
	private boolean enclosed = false;


	private final AlarmController alarmController;
	private final CasingController casingController;

	public DistanceController(AlarmController alarmController, CasingController casingController) {
		this.alarmController = alarmController;
		this.casingController = casingController;
	}

	// distance will be -1 if invoked by casing controller
	public void computeAction(float distance, boolean enclosedIn) {
		enclosed = enclosedIn;
		if(distance >= 0){
			verifyDistanceAndRaiseAlert(distance);
		}
		return ;
	}

	private void verifyDistanceAndRaiseAlert(float distance) {
		if(!enclosed && distance <= casingThreshold){
			alarmController.notify(true);
		}else if( !enclosed && distance <= alarmThreshold){
			casingController.handleCasingOfObject(true);
		}else if(enclosed && distance > alarmThreshold){
			alarmController.notify(false);
			casingController.handleCasingOfObject(false);
		}
		return ;
	}
}
