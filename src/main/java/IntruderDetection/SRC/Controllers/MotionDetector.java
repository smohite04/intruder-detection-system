package IntruderDetection.SRC.Controllers;

public class MotionDetector {

	private final UserAlertController userAlertController;

	public MotionDetector(UserAlertController userAlertController) {
		this.userAlertController = userAlertController;
	}

	public void computeAction(boolean pirSensorIn) {
		userAlertController.raiseAlert(pirSensorIn);
		//userAlertController.notify(pirSensorIn);
	}

}
