package IntruderDetection.SRC.DataCollectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class FileReader {

    public static LinkedList<String> readFile(String filePath){
        LinkedList<String> data_values = new LinkedList<>();
        try {
            File fileObjectDistance = new File(filePath);
            Scanner myReader = new Scanner(fileObjectDistance);
            while (myReader.hasNextLine()) {
                String dis_value = myReader.nextLine();
                data_values.add(dis_value);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            out.println(" An error or exception has occured");
            e.printStackTrace();
        }
        return data_values;
    }
}
