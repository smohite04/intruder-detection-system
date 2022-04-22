package com.software.design;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

import static com.software.design.ConstantClass.MAX_ROUND;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hi  My name is Shreyansh Khandelwal");

        // Start reading the file
          String  checkForPirDataSensor = "PIR Data Sensor:";
          LinkedList<String> pir_values = new LinkedList<String>();

         try {
             File fileObject  = new File("/Users/skhand17/IdeaProjects/Software_Design_564/src/DataValues.txt");
             Scanner myReader = new Scanner(fileObject);
             while(myReader.hasNextLine()) {
                 String PIR_value = myReader.nextLine();
                 if(PIR_value.contains(checkForPirDataSensor)){
                     continue;
                 }
                 pir_values.add(PIR_value);
             }
             myReader.close();
             System.out.println(pir_values);

         } catch (FileNotFoundException e) {
             System.out.println(" An error or exception has occured");
             e.printStackTrace();
         }

         try {
             File fileObjectDistance =  new File("/Users/skhand17/IdeaProjects/Software_Design_564/src/DistanceValues.txt");
             String checkForDistanceDataSensor = "Distance:";
             LinkedList<String> data_values = new LinkedList<String>();

             Scanner myReader = new Scanner(fileObjectDistance);
             while(myReader.hasNextLine()) {
                 String dis_value = myReader.nextLine();
                 if(dis_value.contains(checkForDistanceDataSensor)){
                     continue;
                 }
                 data_values.add(dis_value);
             }
             myReader.close();
             System.out.println(data_values);

         } catch (FileNotFoundException e) {
             System.out.println(" An error or exception has occured");
             e.printStackTrace();
         }

         // Sending one value each time for both of the linkedlists
        /*
        In first round no distance values will be sent.
        Only three values of PIR will be sent i.e. 10, 9, 8
         */

        int round=0;

           // MotionDetector motion = new MotionDetector();

        }

    }
}
