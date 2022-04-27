package IntruderDetection.SRC.DataCollectors;

public class SurveyRunner extends Thread{
    private Thread t;

    private Float value;
    private ImageDetails imageDetails;
    private SurveillanceDataCollector surveillanceDataCollector;



    SurveyRunner(Float value,ImageDetails imageDetails, SurveillanceDataCollector surveillanceDataCollector) {
        this.value = value;
        this.imageDetails = imageDetails;
        this.surveillanceDataCollector = surveillanceDataCollector;
    }

    public void run() {
        synchronized(surveillanceDataCollector) {
            surveillanceDataCollector.getCameraDataCollector().insertData(value <= 10? imageDetails : null);
            surveillanceDataCollector.getPirDataCollector().insertData(value <= 10);
        }
        System.out.println("Thread with" +  value + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  value );
        if (t == null) {
            t = new Thread (this, value.toString());
            t.start ();
        }
    }
}
