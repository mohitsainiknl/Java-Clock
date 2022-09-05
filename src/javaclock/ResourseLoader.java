import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ResourseLoader {
    static ResourseLoader rl = new ResourseLoader();

    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image =  ImageIO.read(rl.getClass().getResource("images/" + fileName));
        }
        catch (Exception e) {
            try {
                image =  ImageIO.read(new File("res/images/" + fileName));
            } catch (Exception err) {
                System.out.println("Image not found!");
            }
        }
        return image;
    }
}
