package IntruderDetection.SRC.Contracts;

import java.awt.*;

public class AlertNotification extends Notification{
    private Image cameraStream;
    private boolean alertRaised = false;

    private Integer round;

    public AlertNotification(Image cameraStream, boolean alertRaised, Integer round){

        this.cameraStream = cameraStream;
        this.alertRaised = alertRaised;
        this.round = round;
    }

    public Image getCameraStream() {
        return cameraStream;
    }

    public boolean isAlertRaised() {
        return alertRaised;
    }

    public Integer getRound() { return round; }
}
