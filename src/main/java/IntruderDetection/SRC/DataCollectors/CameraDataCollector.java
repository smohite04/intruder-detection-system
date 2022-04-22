package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.UserAlertController;
import static java.lang.System.out;

public class CameraDataCollector {

    private final UserAlertController userAlertController;
	private final String debugTAG;

    public CameraDataCollector(UserAlertController userAlertController) {
        this.userAlertController = userAlertController;
		debugTAG = this.getClass().getSimpleName();
    }

    public void insertData(Byte[][] data) {
		out.println(debugTAG + " received image input");
        provideDataToUserController(data);
        return;
    }

    private void provideDataToUserController(Byte[][] imageFrame) {
        //userAlertController.updateCameraQueue(imageFrame);
        return;
    }
}
