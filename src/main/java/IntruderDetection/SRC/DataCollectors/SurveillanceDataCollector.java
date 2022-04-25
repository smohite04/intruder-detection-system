package IntruderDetection.SRC.DataCollectors;

public class SurveillanceDataCollector {
    private DataCollector cameraDataCollector;
    private DataCollector pirDataCollector;

    public SurveillanceDataCollector(DataCollector cameraDataCollector, DataCollector pirDataCollector){

        this.cameraDataCollector = cameraDataCollector;
        this.pirDataCollector = pirDataCollector;
    }

    public DataCollector getCameraDataCollector() {
        return cameraDataCollector;
    }

    public DataCollector getPirDataCollector() {
        return pirDataCollector;
    }
}
