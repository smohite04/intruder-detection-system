package IntruderDetection.SRC.DataCollectors;

public class Main {

    public static void main(String[] args) {

        ReadPIRFile pirFile = new ReadPIRFile("src/main/java/IntruderDetection/SRC/DataCollectors/PIRDataValues.txt");
        ReadDistanceFile distanceFile = new ReadDistanceFile("src/main/java/IntruderDetection/SRC/DataCollectors/DistanceValues.txt");
        pirFile.readAFile();
        distanceFile.readAFile();

        // Sending one value each time for both of the linkedlists
        /*
        In first round no distance values will be sent.
        Only three values of PIR will be sent i.e. 10, 9, 8
         */


    }

}
