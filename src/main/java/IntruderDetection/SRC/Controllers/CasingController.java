package IntruderDetection.SRC.Controllers;

import IntruderDetection.SRC.CasingMode;
import IntruderDetection.Sensors.CasingSensor;

public class CasingController {

	private CasingMode casingMode = CasingMode.open;
	private int ignoreSensorInput = 0;

	private DistanceController distanceController;
	private CasingSensor casingSensor;

	public CasingController( CasingSensor casingSensor){
		this.casingSensor = casingSensor;
	}

	public void handleCasingOfObject(boolean toEnclose) {
		if( toEnclose && casingMode != CasingMode.enclosing && casingMode != CasingMode.enclosed){
			if(casingMode == CasingMode.opening){
				ignoreSensorInput++;
			}
			casingMode = CasingMode.enclosing;
			casingSensor.instruct(true);
		}
		if( !toEnclose && casingMode != CasingMode.open && casingMode != CasingMode.opening){
			if(casingMode == CasingMode.enclosing){
				ignoreSensorInput++;
			}
			casingMode = CasingMode.opening;
			casingSensor.instruct(false);
		}
		handleCasingDoneAcknowledgement(false);
		return ;
	}

	public void handleCasingDoneAcknowledgement(boolean sensorIn) {
		if(sensorIn){
			if(ignoreSensorInput == 0){
				if(casingMode == CasingMode.opening){
					casingMode = CasingMode.open;
					notifyDistanceController(false);
				}else if( casingMode == CasingMode.enclosing){
					casingMode = CasingMode.enclosed;
					notifyDistanceController(true);
				}
			}else{
				ignoreSensorInput--;
			}
		}
		return ;
	}

	private void notifyDistanceController(boolean casingAcknowledgement) {
		distanceController.computeAction(-1,casingAcknowledgement);
		return ;
	}

}
