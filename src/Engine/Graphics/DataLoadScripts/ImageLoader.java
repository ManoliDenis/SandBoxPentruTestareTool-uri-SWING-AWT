package Engine.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageLoader
{

    public static BufferedImage LoadImage(String path)
    {
        try
        {
            File file = new File(path);
            if(!file.exists())
            {
                System.out.println("PATH ITEM :"+path + "CANNOT BE FOUND!");
                return null;
            }
            return ImageIO.read(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }}
