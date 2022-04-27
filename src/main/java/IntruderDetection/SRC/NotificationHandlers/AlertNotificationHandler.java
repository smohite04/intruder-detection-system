package IntruderDetection.SRC.NotificationHandlers;

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
            System.out.println("Intruder Detected");
            System.out.println("Image Notification Sent");
            File outputFile = new File("imageNotifications/" + alertNotification.getCameraStream().getRound() + ".jpg");
            try {
                ImageIO.write((BufferedImage) alertNotification.getCameraStream().getImage(), "jpg", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
