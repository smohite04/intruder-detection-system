package IntruderDetection.SRC.DataCollectors;

import java.awt.*;

public class ImageDetails {
    private Image image;
    private int round;

    public ImageDetails(Image image, int round){

        this.image = image;
        this.round = round;
    }

    public Image getImage() {
        return image;
    }

    public int getRound() {
        return round;
    }
}
