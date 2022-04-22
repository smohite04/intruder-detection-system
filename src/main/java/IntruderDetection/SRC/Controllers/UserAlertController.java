public class UserAlertController implements INotificationHandler {

	private int queueThreshold = 1024000;

	private Deque<byte[][]> cameraQueue;

	private AlertNotification[] alertNotification;

	public void updateCameraQueue(byte[][] cameraFrame) {
		cameraQueue.add(cameraFrame);
		return ;
	}


	/**
	 * @see INotificationHandler#notify(int)
	 */
	public Notification notify(int raiseNotification) {
		return null;
	}

}
