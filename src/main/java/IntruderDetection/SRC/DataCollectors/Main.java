package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.NotificationHandlers.AlarmNotification;
import IntruderDetection.SRC.Controllers.*;
import IntruderDetection.Sensors.CasingSensor;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws Exception{

        deleteFolder(new File("imageNotifications"));
        LinkedList<String> distanceData = FileReader.readFile("src/main/java/IntruderDetection/SRC/DataCollectors/DistanceValues1.txt");
        List<BufferedImage> images = getImagesFromFile();

        String audioPath = "src/main/java/IntruderDetection/SRC/DataCollectors/TF001.wav";

        AlarmNotification alarmNotification = AlarmNotification.getOrCreateInstance(audioPath);
        AlarmController alarmController = new AlarmController(alarmNotification);

        CasingSensor casingSensor = new CasingSensor();
        CasingController casingController = new CasingController(casingSensor);

        DistanceController distanceController = new DistanceController(alarmController, casingController);
        DistanceDataCollector distanceDataCollector = new DistanceDataCollector(distanceController);

        casingController.registerListener(distanceController);
        casingSensor.registerListener(casingController);

        UserAlertController userAlertController = new UserAlertController();
        MotionDetector motionDetector = new MotionDetector(userAlertController);
        CameraDataCollector cameraDataCollector = new CameraDataCollector(userAlertController);
        PirDataCollector pirDataCollector = new PirDataCollector(motionDetector);
        SurveillanceDataCollector surveillanceDataCollector = new SurveillanceDataCollector(cameraDataCollector, pirDataCollector);

        Iterator distanceIterator = distanceData.iterator();

        int round = 0;
        while (distanceIterator.hasNext()) {
            out.println(String.format("-----------Round %d----------------------", round));
            if (distanceIterator.hasNext()) {
                Float distance = Float.valueOf((String) distanceIterator.next());
                out.println("Inputs: " +
                        (distance <= 7 ? distance : "Absent") +
                        " | " + (distance <= 10)
                );
                //distanceDataCollector.insertData(distance <= 7? distance : null);

                var thread = new DistanceDataCollectorRunner(distance, distanceDataCollector);
               // distanceDataCollector.insertData(distance <= 7? distance : null);
               thread.start();
                Image image = images.remove(0);
                var imageDetails = new ImageDetails(image, round);
                var thread1 = new SurveyRunner(distance, imageDetails, surveillanceDataCollector);
                // distanceDataCollector.insertData(distance <= 7? distance : null);
                thread1.start();
               // surveillanceDataCollector.getCameraDataCollector().insertData(distance <= 10? imageDetails : null);
                //surveillanceDataCollector.getPirDataCollector().insertData(distance <= 10);
                // wait for threads to end
                try {
                    thread.join();
                    thread1.join();
                } catch ( Exception e) {
                    System.out.println("Interrupted");
                }
            }
            out.println("----------------------------------------");
            round++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<BufferedImage> getImagesFromFile() {
        final File dir = new File("src/main/java/IntruderDetection/SRC/DataCollectors/images");

        // array of supported extensions (use a List if you prefer)
        final String[] EXTENSIONS = new String[]{
                "jpg" // and other formats you need
        };
        // filter to identify images based on their extensions
        final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);
                    bufferedImages.add(img);
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
        return bufferedImages;
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }
}
