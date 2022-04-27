package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.UserAlertController;

import java.awt.*;

import static java.lang.System.out;

public class CameraDataCollector extends DataCollector<ImageDetails>{

    private final UserAlertController userAlertController;
	private final String debugTAG;

    public CameraDataCollector(UserAlertController userAlertController) {
        this.userAlertController = userAlertController;
		debugTAG = this.getClass().getSimpleName();
    }

    public boolean insertData(ImageDetails imageDetails) {
        if (imageDetails != null && imageDetails.getImage() != null) {
            provideDataToUserController(imageDetails);
        }
        return true;
    }

    private void provideDataToUserController(ImageDetails imageDetails) {
        userAlertController.updateCameraQueue(imageDetails);
        return;
    }
}
