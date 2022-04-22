package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.DistanceController;

public class DistanceDataCollector {

	private DistanceController distanceController;

	public DistanceDataCollector(DistanceController distanceController) {
		this.distanceController = distanceController;
	}


	public void insertData(Float data) {
		notifyDistanceController(data);
		return ;
	}

	private void notifyDistanceController(float distance) {
		distanceController.computeAction(distance,false);
		return ;
	}

}
