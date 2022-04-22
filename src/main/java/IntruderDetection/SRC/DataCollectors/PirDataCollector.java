package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.MotionDetector;

public class PirDataCollector {

	private MotionDetector motionDetector;

	public PirDataCollector(MotionDetector motionDetector) {
		this.motionDetector = motionDetector;
	}

	public void insertData(Boolean data) {
		notifyMotionDetector(data);
	}

	private void notifyMotionDetector(boolean raiseAlert) {
		motionDetector.computeAction(raiseAlert);
	}

}
