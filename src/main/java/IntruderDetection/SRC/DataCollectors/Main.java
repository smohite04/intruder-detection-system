package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.AlarmNotification;
import IntruderDetection.SRC.Controllers.*;
import IntruderDetection.Sensors.CasingSensor;

import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {

        LinkedList<String> pirData = FileReader.readFile("src/main/java/IntruderDetection/SRC/DataCollectors/PIRDataValues.txt");
        LinkedList<String> distanceData = FileReader.readFile("src/main/java/IntruderDetection/SRC/DataCollectors/DistanceValues.txt");


        String filePathing = "Your Image file path";

        AlarmNotification alarmNotification = new AlarmNotification(filePathing);
        AlarmController alarmController = new AlarmController(alarmNotification);

        CasingSensor casingSensor = new CasingSensor();
        CasingController casingController = new CasingController(casingSensor);

        DistanceController distanceController = new DistanceController(alarmController, casingController);
        DistanceDataCollector distanceDataCollector = new DistanceDataCollector(distanceController);

        casingController.registerListener(distanceController);
        casingSensor.registerListener(casingController);

        UserAlertController userAlertController = new UserAlertController();
        MotionDetector motionDetector = new MotionDetector(userAlertController);
        PirDataCollector pirDataCollector = new PirDataCollector(motionDetector);


        Iterator pirIterator = pirData.iterator();
        Iterator distanceIterator = distanceData.iterator();

        int round = 0;
        while (distanceIterator.hasNext() || pirIterator.hasNext()) {
            out.println(String.format("-----------Round %d----------------------", round));
            if (distanceIterator.hasNext()) {
                Float distance = Float.valueOf((String) distanceIterator.next());
                distanceDataCollector.insertData(distance);
            }
            if (pirIterator.hasNext()) {
                Float pirDistance = Float.valueOf((String) pirIterator.next());
                pirDataCollector.insertData(pirDistance <= 10);
            }
            out.println("----------------------------------------");
            round++;
        }


    }

}
