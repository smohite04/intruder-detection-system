package IntruderDetection.SRC.DataCollectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class ReadDistanceFile implements FileUtil{

   String disFilePath;

    public ReadDistanceFile(String filePath) {
        disFilePath = filePath;
    }

    @Override
    public void readAFile() {
        try {
            File fileObjectDistance =  new File(disFilePath);
            String checkForDistanceDataSensor = "Distance:";
            LinkedList<String> data_values = new LinkedList<>();

            Scanner myReader = new Scanner(fileObjectDistance);
            while(myReader.hasNextLine()) {
                String dis_value = myReader.nextLine();
                if(dis_value.contains(checkForDistanceDataSensor)){
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
    }
}
