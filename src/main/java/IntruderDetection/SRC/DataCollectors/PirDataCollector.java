package IntruderDetection.SRC.DataCollectors;

public class PirDataCollector extends DataCollector_Boolean_ {

	private MotionDetector motionDetector;

	public PirDataCollector(MotionDetector motionDetector) {
		this.motionDetector = motionDetector;
	}

	public void insertData(Boolean data) {
		notifyDistanceController(data);
	}

	private void notifyMotionDetector(boolean raiseAlert) {
		motionDetector.computeAction(raiseAlert);
	}

}
