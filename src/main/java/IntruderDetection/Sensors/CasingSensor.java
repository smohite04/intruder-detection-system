
public class CasingSensor {
    
    private CasingController casingController;

    public CasingSensor(CasingController cosingController){
        this.casingController = casingController;
    }

    public synchronized void instruct(boolean instruction) {
        Thread thread = new Thread("casingsensor") {
            public void run(){
                TimeUnit.SECONDS.sleep(2);
                casingController.handleCasingDoneAcknowledgement(instruction);
            }
        };
        thread.start();
    }
}
