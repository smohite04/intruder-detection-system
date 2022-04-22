public class UserAlertController implements INotificationHandler {

	private int queueThreshold = 1024000;

	private Queue<byte[][]> cameraQueue;

	private AlertNotification[] alertNotification;

	public boolean updateCameraQueue(byte[][] camera) {
		return false;
	}


	/**
	 * @see INotificationHandler#notify(int)
	 */
	public Notification notify(int raiseNotification) {
		return null;
	}

}
