package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.MotionDetector;
import static java.lang.System.out;

public class PirDataCollector extends DataCollector<Boolean> {

	private final MotionDetector motionDetector;
	private final String debugTAG;


	public PirDataCollector(MotionDetector motionDetector) {
		debugTAG = this.getClass().getSimpleName();
		this.motionDetector = motionDetector;
	}

	public boolean insertData(Boolean data) {
		notifyMotionDetector(data);
		return true;
	}

	private void notifyMotionDetector(Boolean raiseAlert) {
		motionDetector.computeAction(raiseAlert);
	}

}
