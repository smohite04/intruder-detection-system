package IntruderDetection.SRC.NotificationHandlers;

import IntruderDetection.SRC.Contracts.Notification;
import IntruderDetection.SRC.DataCollectors.ImageDetails;


public class AlertNotification extends Notification {
    private ImageDetails cameraStream;
    private boolean alertRaised = false;

    //private Integer round;

    public AlertNotification(ImageDetails cameraStream, boolean alertRaised){

        this.cameraStream = cameraStream;
        this.alertRaised = alertRaised;
      //  this.round = round;
    }

    public ImageDetails getCameraStream() {
        return cameraStream;
    }

    public boolean isAlertRaised() {
        return alertRaised;
    }

   // public Integer getRound() { return round; }
}
