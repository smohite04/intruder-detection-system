public class CameraDataCollector {

	private UserAlertController userAlertController;
	
	public CameraDataController(UserAlertController userAlertController) {
		this.userAlertController = userAlertController;
	}

	public void insertData(Byte[][] data) {
		provideDataToUserController(data);
		return ;
	}

	private void provideDataToUserController(Byte[][] imageFrame) {
		userAlertController.updateCameraQueue(imageFrame);
		return;
	}
}
