package IntruderDetection.SRC.NotificationHandlers;

import IntruderDetection.SRC.Contracts.Notification;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AlarmNotification extends Notification {

	Clip clip;
	String statusClip; // current status of the clip
	private boolean alarm;
	AudioInputStream audioInputStream;
	String filepath;

	private AlarmNotification(String filepath) {
		this.filepath = filepath;
		this.statusClip = "paused";

		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(this.filepath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e1) {
			System.out.println("UnSupported Audio File Exception has occured");
			e1.printStackTrace();
		} catch (IOException e2) {
			System.out.println("IO exception has occured");
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
			System.out.println("LineUnavailable Exception has occured");
			e3.printStackTrace();
		}
	}
	private static AlarmNotification instance = null;
	public static AlarmNotification getOrCreateInstance(String filepath){
		if(instance == null){
			var alarmNotification = new AlarmNotification(filepath);
			instance = alarmNotification;
		}
		return instance;
	}
	public void startAlarmProcess(boolean alarm) {
		this.alarm = alarm;
		if (alarm) {
			play();
		} else {
			pause();
		}
	}

	public void play() {
		if ( statusClip!=null && statusClip.equals("play")) {
			System.out.println("Alarm:\ton");
		} else {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			statusClip = "play";
			System.out.println("Alarm Turning On");
		}
	}

	public void pause() {
		if(statusClip.equals("paused")) {
			System.out.println("Alarm:\toff");
		} else {
			clip.stop();
			statusClip="paused";
			System.out.println("Alarm Turning Off");
		}

	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
}
