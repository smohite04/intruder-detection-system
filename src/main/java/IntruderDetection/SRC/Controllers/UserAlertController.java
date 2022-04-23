package IntruderDetection.SRC.Controllers;

import java.util.Deque;

public class UserAlertController  {

	private int queueThreshold = 1024000;

	private Deque<byte[][]> cameraQueue;


	public void updateCameraQueue(byte[][] cameraFrame) {
		cameraQueue.add(cameraFrame);
		return ;
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
