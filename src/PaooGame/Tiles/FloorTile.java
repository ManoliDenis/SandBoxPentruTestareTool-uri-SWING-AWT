package PaooGame.Tiles;

import java.awt.image.BufferedImage;

public class FloorTile extends Tile
{
    public FloorTile(BufferedImage image, int id)
    {
        super(image, id);
    }
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}
