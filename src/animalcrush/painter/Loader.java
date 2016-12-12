package animalcrush.painter;

import java.util.HashMap;
import javafx.scene.image.Image;

/**
 * @author pencho
 */

public class Loader {
    static private final HashMap<String, Image> IMAGES = new HashMap<>();
    
    static public Image getImage(String filename){
        Image image = Loader.IMAGES.get(filename);
        if (image != null){
            return image;
        }
        
        image = new Image(Painter.class.getResourceAsStream("/animalcrush/images/" + filename));
        Loader.IMAGES.put(filename, image);
        return image;
    }
}
