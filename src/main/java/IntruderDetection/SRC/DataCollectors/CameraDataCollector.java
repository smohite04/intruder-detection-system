package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.UserAlertController;

import java.awt.*;

import static java.lang.System.out;

public class CameraDataCollector {

    private final UserAlertController userAlertController;
	private final String debugTAG;

    public CameraDataCollector(UserAlertController userAlertController) {
        this.userAlertController = userAlertController;
		debugTAG = this.getClass().getSimpleName();
    }

    public void insertData(Image data, int round) {
		out.println(debugTAG + " received image input");
        if (data != null) {
            provideDataToUserController(data, round);
        }
        return;
    }

    private void provideDataToUserController(Image imageFrame, int round) {
        userAlertController.updateCameraQueue(imageFrame, round);
        return;
    }
}
