package IntruderDetection.SRC.DataCollectors;

public class DistanceDataCollectorRunner extends Thread{
    private Thread t;

    private Float value;
    private DistanceDataCollector distanceDataCollector;


    DistanceDataCollectorRunner( Float value, DistanceDataCollector distanceDataCollector) {
        this.value = value;
        this.distanceDataCollector = distanceDataCollector;

        //PD = pd;
    }

    public void run() {
        synchronized(distanceDataCollector) {
            distanceDataCollector.insertData(value);
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
