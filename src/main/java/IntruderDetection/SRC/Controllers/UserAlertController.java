package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.Contracts.AlertNotification;
import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.NotificationHandlers.AlertNotificationHandler;

import java.util.Deque;
import java.util.LinkedList;


public class UserAlertController  {

	private static INotificationHandler alertNotificationHandler = new AlertNotificationHandler();
	private int queueThreshold = 1024000;
	private int threeFourthOfThreshold = 768000;
	private Deque<byte[][]> cameraQueue;
	public UserAlertController(){
		cameraQueue = new LinkedList<>();
	}

	public void updateCameraQueue(byte[][] cameraFrame) {
		updatedIfQueueReachesThreshold();
		cameraQueue.add(cameraFrame);
		return ;
	}

/* since this is doubled ended queue we can insert and remove from both ends.
When size breaches the threshold, it should remove stale images (remove from front).
* */
	private void updatedIfQueueReachesThreshold() {
		if(cameraQueue.size() >= queueThreshold){
			while(cameraQueue.size() > threeFourthOfThreshold){
				cameraQueue.poll();
			}
		}
	}
// poll last we get the latest camera stream added to the queue to send to user with alert.
	public boolean raiseAlert(boolean raiseAlert){
		if(raiseAlert == true){
			var camera = (cameraQueue.isEmpty() == false)?cameraQueue.pollLast(): null;
			var notification = createNotification(true, camera);
			alertNotificationHandler.notify(notification);
		}
		else{
			var notification = createNotification(false, null);
			alertNotificationHandler.notify(notification);
		}
		return raiseAlert;
	}

	private AlertNotification createNotification(boolean raiseAlert, byte[][] latestCameraStream){
		return new AlertNotification( latestCameraStream, raiseAlert);
	}
	public void notify(Boolean raiseNotification) {
		if(raiseNotification ==  true) {
			System.out.println("The user has been alerted about the intruder");
			System.out.println("The image of intruder is captured and sent to the intruder");
		} else {
			System.out.println("The intruder is not in the sur.veil·lance");
			System.out.println("The user is not notified about any alert");
		}
	}

}
