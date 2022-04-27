package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.DistanceController;

import static java.lang.System.out;

public class DistanceDataCollector extends DataCollector<Float>{

    private final DistanceController distanceController;
    private final String debugTAG;


    public DistanceDataCollector(DistanceController distanceController) {
        debugTAG = this.getClass().getSimpleName();
        this.distanceController = distanceController;
    }


    public boolean insertData(Float data) {
        notifyDistanceController(data);
        return true;
    }

    private void notifyDistanceController(Float distance) {
        distanceController.computeAction(distance, false);
        return;
    }

}
