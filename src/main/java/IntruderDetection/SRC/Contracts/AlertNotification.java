package IntruderDetection.SRC.Contracts;

public class AlertNotification extends Notification{
    private byte[][] cameraStream;
    private boolean alertRaised = false;

    public AlertNotification(byte[][] cameraStream, boolean alertRaised){

        this.cameraStream = cameraStream;
        this.alertRaised = alertRaised;
    }

    public byte[][] getCameraStream() {
        return cameraStream;
    }

    public boolean isAlertRaised() {
        return alertRaised;
    }
}
