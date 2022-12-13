package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
    private int x;
    private int y;
    private String filename;
    private Boolean visible;

    private BufferedImage img;

    Image(int x, int y, String filename) {
        // this.name = name;
        this.x = x;
        this.y = y;
        this.filename = filename;
        this.visible = false;

        try {
            this.img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("Image failed to load Filename= " + filename);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getFilename() {
        return this.filename;
    }

    public void changeVisibility(Boolean visibility) {
        this.visible = visibility;
    }

    public Boolean getVisibility() {
        return this.visible;
    }

    public BufferedImage getBufferedImage() {
        return this.img;
    }

}
