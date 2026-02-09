package Engine.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage       spriteSheet;              
    private static final int    tileWidth   = 16;   
    private static final int    tileHeight  = 16;   

    /* Constructor: Loads the SpriteSheet */
    public SpriteSheet(BufferedImage buffImg)
    {
        spriteSheet = buffImg;
    }

    /* crop for a fixed-size tilemap */
    public BufferedImage crop(int x, int y)
    {
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    /* crop for a variable-size tilemap */
    public BufferedImage crop(int x, int y,int w,int h)
    {
        return spriteSheet.getSubimage(x * w, y * h, w, h);
    }
}
