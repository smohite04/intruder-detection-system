package IntruderDetection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;


public class ReadPIRFile implements FileUtil{

    String pirFilepath;
    public ReadPIRFile(String filePath) {
        pirFilepath = filePath;
    }

    @Override
    public void readAFile() {
        // Start reading the file
        String  checkForPirDataSensor = "PIR Data Sensor:";
        LinkedList<String> pir_values = new LinkedList<>();

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
    }


}
