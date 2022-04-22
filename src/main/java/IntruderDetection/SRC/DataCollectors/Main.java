package IntruderDetection.SRC.DataCollectors;
import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {

        ReadPIRFile pirFile = new ReadPIRFile("/Users/skhand17/IdeaProjects/Software_Design_564/intruder-detection-system/src/main/java/IntruderDetection/PIRDataValues.txt");
        ReadDistanceFile distanceFile = new ReadDistanceFile("/Users/skhand17/IdeaProjects/Software_Design_564/intruder-detection-system/src/main/java/IntruderDetection/DistanceValues.txt");
        pirFile.readAFile();
        distanceFile.readAFile();

         // Sending one value each time for both of the linkedlists
        /*
        In first round no distance values will be sent.
        Only three values of PIR will be sent i.e. 10, 9, 8
         */


    }

    }
