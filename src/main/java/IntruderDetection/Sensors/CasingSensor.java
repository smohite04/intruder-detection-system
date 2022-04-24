package IntruderDetection.Sensors;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class CasingSensor {
    private final String debugTag;
    List<CasingSensorObserver> casingSensorObservers;

    public CasingSensor() {
        debugTag = this.getClass().getSimpleName();
        casingSensorObservers = new LinkedList<>();
    }

    public void registerListener(CasingSensorObserver casingSensorObserver) {
        casingSensorObservers.add(casingSensorObserver);
    }

    public synchronized void instruct(boolean instruction) {
        out.println(debugTag + " Received instruction to " + (instruction ? "close " : "open ") + " casing");
        Thread thread = new Thread("casingsensor") {
            public void run() {
                try {
                    out.println(debugTag + " casing starting to " + (instruction ? "close " : "open "));
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println(debugTag + " casing instruction to  " + (instruction ? "close " : "open ") + " complete");
                notifyObservers(instruction);
            }
        };
        thread.start();
    }

    private void notifyObservers(Boolean enclosed) {
        for (CasingSensorObserver casingSensorObserver : casingSensorObservers) {
            casingSensorObserver.notify(enclosed);
        }
    }
}
