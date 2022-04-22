package IntruderDetection.Sensors;

import IntruderDetection.SRC.Controllers.CasingController;

import java.util.concurrent.TimeUnit;

public class CasingSensor {
    
    private CasingController casingController;

    public CasingSensor(CasingController cosingController){
        this.casingController = casingController;
    }

    public synchronized void instruct(boolean instruction) {
        Thread thread = new Thread("casingsensor") {
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                casingController.handleCasingDoneAcknowledgement(instruction);

            }
        };
        thread.start();
    }
}
