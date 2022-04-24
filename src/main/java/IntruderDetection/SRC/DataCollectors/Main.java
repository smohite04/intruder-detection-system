package IntruderDetection.SRC.DataCollectors;

import IntruderDetection.SRC.AlarmNotification;
import IntruderDetection.SRC.Controllers.*;
import IntruderDetection.Sensors.CasingSensor;

import javax.imageio.ImageIO;
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

    public static void main(String[] args) {

        deleteFolder(new File("imageNotifications"));
        LinkedList<String> distanceData = FileReader.readFile("src/main/java/IntruderDetection/SRC/DataCollectors/DistanceValues2.txt");
        List<BufferedImage> images = getImagesFromFile();


        String audioPath = "src/main/java/IntruderDetection/SRC/DataCollectors/TF002.wav";

        AlarmNotification alarmNotification = new AlarmNotification(audioPath);
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


        Iterator distanceIterator = distanceData.iterator();

        int round = 0;
        while (distanceIterator.hasNext()) {
            out.println(String.format("-----------Round %d----------------------", round));
            if (distanceIterator.hasNext()) {
                Float distance = Float.valueOf((String) distanceIterator.next());
                distanceDataCollector.insertData(distance <= 7? distance: null);

                Image image = images.remove(0);
                cameraDataCollector.insertData(distance <= 10? image : null, round);
                pirDataCollector.insertData(distance <= 10);
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

                    // you probably want something more involved here
                    // to display in your UI
                    System.out.println("image: " + f.getName());
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
