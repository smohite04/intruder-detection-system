package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.AlarmNotification;

public class AlarmController {

	private AlarmNotification alarmNotification;

	public AlarmController(AlarmNotification alarmNotification) {
		this.alarmNotification = alarmNotification;
	}

	private boolean updateAlarm() {
		return false;
	}


	public boolean notify(boolean raiseNotification) {
		System.out.println("The value of raiseNotification is : " +raiseNotification);
		alarmNotification.startAlarmProcess(raiseNotification);
		return raiseNotification;
	}

}
