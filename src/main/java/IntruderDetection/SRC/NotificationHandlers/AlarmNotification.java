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

	public AlarmNotification(String filepath) {
		this.filepath = filepath;
		this.statusClip = "paused";

		try {
			System.out.println("Deciding whether alarm should be triggered or not");
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
			System.out.println("The alarm is already on");
		} else {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			statusClip = "play";
			System.out.println("The Alarm is triggered");
		}
	}

	public void pause() {
		if(statusClip.equals("paused")) {
			System.out.println("The alarm is already paused");
		} else {
			clip.stop();
			statusClip="paused";
			System.out.println("The alarm is turned off as intruder is moving away from proximity area");
		}

	}

	public boolean isAlarm() {
		return false;
	}

}
