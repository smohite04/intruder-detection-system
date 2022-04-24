package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.DistanceController;

import static java.lang.System.out;

public class DistanceDataCollector {

    private final DistanceController distanceController;
    private final String debugTAG;


    public DistanceDataCollector(DistanceController distanceController) {
        debugTAG = this.getClass().getSimpleName();
        this.distanceController = distanceController;
    }


    public boolean insertData(Float data) {
        out.println(debugTAG + ": received input distance " + data);
        notifyDistanceController(data);
        return true;
    }

    private void notifyDistanceController(Float distance) {
        out.println(debugTAG + "Notifying DistanceController distance: " + distance);
        distanceController.computeAction(distance, false);
        return;
    }

}
