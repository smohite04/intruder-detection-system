public class DistanceController {

	private float casingThreshold = Constants.CASING_THRESHOLD;
	private float alarmThreshold = Constants.ALARM_THRESHOLD;
	private boolean enclosed = false;


	private AlarmController alarmController;
	private CasingController casingController;

	public void computeAction(float distance, boolean enclosedIn) {
		enclosed = enclosedIn;
		verifyDistanceAndRaiseAlert(distance)
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
