package IntruderDetection.SRC.DataCollectors;

public class DistanceDataCollector extends DataCollector {

	private DistanceController distanceController;

	public DistanceDataCollector(DistanceController distanceController) {
		this.distanceController = distanceController;
	}

	@Override
	public void insertData(Float data) {
		notifyDistanceController(data);
		return ;
	}

	private void notifyDistanceController(float distance) {
		distanceController.computeAction(distance,false);
		return ;
	}

}
