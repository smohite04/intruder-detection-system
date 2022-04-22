
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
	static String filepath;


	public AlarmNotification(boolean alarm) throws UnsupportedAudioFileException,IOException,LineUnavailableException {
		this.alarm = alarm;
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

		clip = AudioSystem.getClip();
		clip.open(audioInputStream);

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}


	public void play() {
		clip.start();
		statusClip = "play";
	}

	public void pause() {
		if(statusClip.equals("paused")) {
			System.out.println("The audio is already paused");
		}
		clip.stop();
		statusClip="paused";
	}

	public boolean isAlarm() {
		return false;
	}

}
