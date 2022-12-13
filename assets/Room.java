package assets;

import javax.swing.JPanel;
import assets.Question;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Set;
import assets.Image;
import util.HelperGraphic;
import java.util.SortedMap;
import java.util.TreeMap;
import assets.Suspect;

public class Room extends JPanel {

    private SortedMap<String, Image> images;
    protected GameLogic mGameLogic;

    public Room(GameLogic mGameLogic) {
        this.mGameLogic = mGameLogic;
        this.images = new TreeMap<String, Image>();
    }

    public void addImage(String name, int x, int y, String filename) {
        this.images.put(name, new Image(x, y, filename));

    }

    public void addSuspect(String name, int x, int y, String filename, String intro, String q1, String[] alibi) {
        this.images.put(name, new Suspect(x, y, filename, intro, q1, alibi));
    }

    public void addSuspect(String name, int x, int y, String filename, String intro) {
        this.images.put(name, new Suspect(x, y, filename, intro));
    }

    // get an image given its name
    public Image getImage(String name) {
        Image img = this.images.get(name);
        if (img == null) {
            System.out.println("Unknown Image when calling getImage " + name);
        }
        return img;
    }

    public Suspect getSuspect(String name) {
        if (this.images.get(name) == null) {
            System.out.println("Unknown Image when calling getImage " + name);
        }
        if (this.images.get(name) instanceof Suspect) {
            return (Suspect) this.images.get(name);
        } else {
            System.out.println("SUSPECT " + name + "does not exist");
            return null;
        }
    }

    public void changeVisibility(String name, Boolean change) {
        getImage(name).changeVisibility(change);
    }

    public Boolean getVisibility(String name) {
        return getImage(name).getVisibility();
    }

    // displays an image given image name
    public void displayImage(Graphics g, String name) {
        Image img = getImage(name);
        if (img.getBufferedImage() == null) {
            System.out.println("Unknown Image " + img.getFilename());
        } else
            g.drawImage(img.getBufferedImage(), img.getX(), img.getY(), null);
    }

    // renders whole scene
    public void renderScene(Graphics g) {
        Set entrySet = images.entrySet();

        // Obtaining an iterator for the entry set
        Iterator it = entrySet.iterator();

        // Iterate through HashMap entries(Key-Value pairs)
        while (it.hasNext()) {
            Map.Entry current = (Map.Entry) it.next();
            Image img = (Image) current.getValue();
            String name = (String) current.getKey();
            if (img.getVisibility()) {
                displayImage(g, name);
            }
        }

    }
}
