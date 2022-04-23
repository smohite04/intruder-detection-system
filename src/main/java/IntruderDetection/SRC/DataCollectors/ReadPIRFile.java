package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.Controllers.MotionDetector;
import IntruderDetection.SRC.Controllers.UserAlertController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static IntruderDetection.Constants.MAX_ROUND;
import static java.lang.System.out;


public class ReadPIRFile implements FileUtil{

    String pirFilepath;
    LinkedList<String> pir_values = new LinkedList<>();
    public ReadPIRFile(String filePath) {
        pirFilepath = filePath;
    }

    @Override
    public void readAFile() {
        // Start reading the file
        String  checkForPirDataSensor = "PIR Data Sensor:";
        try {
            File fileObject  = new File(pirFilepath);
            Scanner myReader = new Scanner(fileObject);
            while(myReader.hasNextLine()) {
                String PIR_value = myReader.nextLine();
                if(PIR_value.contains(checkForPirDataSensor)){
                    continue;
                }
                pir_values.add(PIR_value);
            }
            myReader.close();
            out.println(pir_values);

        } catch (FileNotFoundException e) {
            out.println(" An error or exception has occured");
            e.printStackTrace();
        }
        for(int i=0; i< pir_values.size(); i++) {
            out.println("The Round is : " + i);
            out.println("#######################");
            UserAlertController userAlertController = new UserAlertController();
            MotionDetector motionDetector = new MotionDetector(userAlertController);
            PirDataCollector pirDataCollector = new PirDataCollector(motionDetector);
            if(Integer.parseInt(pir_values.get(i)) < 10) {
                pirDataCollector.insertData(true);
            } else {
                pirDataCollector.insertData(false);
            }
        }
    }


}
