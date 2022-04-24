package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.AlarmNotification;
import IntruderDetection.SRC.Contracts.FileUtil;
import IntruderDetection.SRC.Controllers.AlarmController;
import IntruderDetection.SRC.Controllers.CasingController;
import IntruderDetection.SRC.Controllers.DistanceController;
import IntruderDetection.Sensors.CasingSensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class ReadDistanceFile implements FileUtil {

    String disFilePath;
    LinkedList<String> data_values = new LinkedList<>();

    public ReadDistanceFile(String filePath) {
        disFilePath = filePath;
    }

    @Override
    public void readAFile() {
        try {
            File fileObjectDistance = new File(disFilePath);
            String checkForDistanceDataSensor = "Distance:";
            Scanner myReader = new Scanner(fileObjectDistance);
            while (myReader.hasNextLine()) {
                String dis_value = myReader.nextLine();
                if (dis_value.contains(checkForDistanceDataSensor)) {
                    continue;
                }
                data_values.add(dis_value);
            }
            myReader.close();
            out.println(data_values);

        } catch (FileNotFoundException e) {
            out.println(" An error or exception has occured");
            e.printStackTrace();
        }

        String filePathing = "Your Image file path";


        AlarmNotification alarmNotification = new AlarmNotification(filePathing);
        AlarmController alarmController = new AlarmController(alarmNotification);

        CasingSensor casingSensor = new CasingSensor();
        CasingController casingController = new CasingController(casingSensor);

        DistanceController distanceController = new DistanceController(alarmController, casingController);
        DistanceDataCollector distanceDataCollector = new DistanceDataCollector(distanceController);

        casingController.registerListener(distanceController);
        casingSensor.registerListener(casingController);

        for (int i = 0; i < data_values.size(); i++) {
            out.println("The Round is : " + i);
            out.println("####################");
            distanceDataCollector.insertData(Float.parseFloat(data_values.get(i)));

        }

    }
}
