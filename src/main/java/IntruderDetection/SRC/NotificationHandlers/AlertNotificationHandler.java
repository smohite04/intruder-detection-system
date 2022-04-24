package IntruderDetection.SRC.NotificationHandlers;

import IntruderDetection.SRC.Contracts.AlertNotification;
import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.Contracts.Notification;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AlertNotificationHandler implements INotificationHandler {
    @Override
    public void notify(Notification notification) {
        AlertNotification alertNotification = (AlertNotification) notification;
        if(alertNotification.isAlertRaised()) {
            System.out.println("The user has been alerted about the intruder");
            System.out.println("The image of intruder is captured and sent to the intruder");
            File outputFile = new File("imageNotifications/" + ((AlertNotification) notification).getRound() + ".jpg");
            try {
                ImageIO.write((BufferedImage)((AlertNotification) notification).getCameraStream(), "jpg", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("The intruder is not in the sur.veil·lance");
            System.out.println("The user is not notified about any alert");
        }
    }
}
