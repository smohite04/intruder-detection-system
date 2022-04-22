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
		//return null;
	}

}
