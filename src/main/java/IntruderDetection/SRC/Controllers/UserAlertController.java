package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.NotificationHandlers.AlertNotification;
import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.DataCollectors.ImageDetails;
import IntruderDetection.SRC.NotificationHandlers.AlertNotificationHandler;

import java.util.Deque;
import java.util.LinkedList;


public class UserAlertController  {

	private static INotificationHandler alertNotificationHandler = new AlertNotificationHandler();
	private int queueThreshold = 10;
	private int threeFourthOfThreshold = 7;
	private Deque<ImageDetails> cameraQueue;
	//private Deque<Integer> roundQueue;
	public UserAlertController(){
		cameraQueue = new LinkedList<>();
		//roundQueue = new LinkedList<>();
	}

	public void updateCameraQueue(ImageDetails imageDetails) {
		updatedIfQueueReachesThreshold();
		cameraQueue.add(imageDetails);
		//roundQueue.add(round);
		return ;
	}

/* since this is doubled ended queue we can insert and remove from both ends.
When size breaches the threshold, it should remove stale images (remove from front).
* */
	private void updatedIfQueueReachesThreshold() {
		if(cameraQueue.size() >= queueThreshold){
			while(cameraQueue.size() > threeFourthOfThreshold){
				cameraQueue.poll();
				//roundQueue.poll();
			}
		}
	}
// poll last we get the latest camera stream added to the queue to send to user with alert.
	public boolean raiseAlert(boolean raiseAlert){
		if(raiseAlert == true){
			var latestStream = (!cameraQueue.isEmpty())?cameraQueue.pollLast() : null;
		//	int round = (!roundQueue.isEmpty())? roundQueue.pollLast() : null;
			var notification = createNotification(true, latestStream);
			alertNotificationHandler.notify(notification);
		}
		else{
			var notification = createNotification(false, null);
			alertNotificationHandler.notify(notification);
		}
		return raiseAlert;
	}

	private AlertNotification createNotification(boolean raiseAlert, ImageDetails latestCameraStream){
		return new AlertNotification( latestCameraStream, raiseAlert);
	}
}
