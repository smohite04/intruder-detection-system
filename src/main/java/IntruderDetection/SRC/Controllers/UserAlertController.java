package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.Contracts.AlertNotification;
import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.NotificationHandlers.AlertNotificationHandler;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;


public class UserAlertController  {

	private static INotificationHandler alertNotificationHandler = new AlertNotificationHandler();
	private int queueThreshold = 10;
	private int threeFourthOfThreshold = 7;
	private Deque<Image> cameraQueue;
	private Deque<Integer> roundQueue;
	public UserAlertController(){
		cameraQueue = new LinkedList<>();
		roundQueue = new LinkedList<>();
	}

	public void updateCameraQueue(Image cameraFrame, int round) {
		updatedIfQueueReachesThreshold();
		cameraQueue.add(cameraFrame);
		roundQueue.add(round);
		return ;
	}

/* since this is doubled ended queue we can insert and remove from both ends.
When size breaches the threshold, it should remove stale images (remove from front).
* */
	private void updatedIfQueueReachesThreshold() {
		if(cameraQueue.size() >= queueThreshold){
			while(cameraQueue.size() > threeFourthOfThreshold){
				cameraQueue.poll();
				roundQueue.poll();
			}
		}
	}
// poll last we get the latest camera stream added to the queue to send to user with alert.
	public boolean raiseAlert(boolean raiseAlert){
		if(raiseAlert == true){
			var camera = (!cameraQueue.isEmpty())?cameraQueue.pollLast() : null;
			int round = (!roundQueue.isEmpty())? roundQueue.pollLast() : null;
			var notification = createNotification(true, camera, round);
			alertNotificationHandler.notify(notification);
		}
		else{
			var notification = createNotification(false, null, null);
			alertNotificationHandler.notify(notification);
		}
		return raiseAlert;
	}

	private AlertNotification createNotification(boolean raiseAlert, Image latestCameraStream, Integer round){
		return new AlertNotification( latestCameraStream, raiseAlert, round);
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
