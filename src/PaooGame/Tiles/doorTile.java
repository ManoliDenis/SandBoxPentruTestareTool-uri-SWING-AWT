package PaooGame.Tiles;

import java.awt.image.BufferedImage;

public class doorTile extends Tile {

    public doorTile(BufferedImage img, int id)
    {
        super(img, id);
    }
    public boolean isSolid()
    {
        return false;
    }
}
