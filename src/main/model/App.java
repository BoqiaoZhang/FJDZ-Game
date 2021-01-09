package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//处理图片的工具类
public class App {

    public static BufferedImage getImg(String path) {
        //App.class finds the path of our App class
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        } catch (IOException e1) {
            System.out.println("Can NOT find the image!");;
            return null;
        }
    }
}
