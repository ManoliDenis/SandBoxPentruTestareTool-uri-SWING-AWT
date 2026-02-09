package Engine.Graphics.DataLoadScripts;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class SoundLoader
{

    public static Clip LoadSound(String path)
    {
        try
        {
            File file = new File(path);
            if(!file.exists())
            {
                System.out.println("PATH ITEM :"+path + "  CANNOT BE FOUND!");
                return null;
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audioInput);

            return clip;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
