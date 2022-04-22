public class CasingController {

	private CasingMode casingMode = CasingMode.open;
	private int ignoreSensorInput = 0;

	private DistanceController distanceController;

	public void handleCasingOfObject(boolean toEnclose, boolean sensorIn) {
		if( toEnclose && casingMode != CasingMode.enlosing && casingMode != CasingMode.enclosed){
			
		}
		if( !toEnclose && casingMode != CasingMode.open && casingMode != CasingMode.opening){

		}
		handleCasingDoneAcknowledgement(sensorIn);
		return ;
	}

	private void handleCasingDoneAcknowledgement(boolean sensorIn) {
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
