package IntruderDetection.SRC.NotificationHandlers;

import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.Contracts.Notification;

public class AlarmNotificationHandler implements INotificationHandler {

    @Override
    public void notify(Notification notification) {
        var alarmNotification = (AlarmNotification) notification;
        System.out.println("The value of raiseNotification is : " +true);
        //TODO: need to remove the alarm argument
        var alarm = alarmNotification.isAlarm();
        alarmNotification.startAlarmProcess(alarm);
        return;
    }
}
