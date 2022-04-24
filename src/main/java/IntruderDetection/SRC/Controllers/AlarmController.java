package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.NotificationHandlers.AlarmNotification;
import IntruderDetection.SRC.Contracts.INotificationHandler;
import IntruderDetection.SRC.NotificationHandlers.AlarmNotificationHandler;

public class AlarmController {

	private static INotificationHandler alarmNotificationHandler = new AlarmNotificationHandler();
	private AlarmNotification alarmNotification;

	public AlarmController(AlarmNotification alarmNotification) {
		this.alarmNotification = alarmNotification;
	}

	public boolean raiseAlarm(boolean alarm) {
		//alarmNotification.setAlarm(alarm);
		alarmNotificationHandler.notify(alarmNotification);
		return alarm;
	}


	public boolean notify(boolean raiseNotification) {
		System.out.println("The value of raiseNotification is : " +raiseNotification);
		alarmNotification.startAlarmProcess(raiseNotification);
		return raiseNotification;
	}

}
