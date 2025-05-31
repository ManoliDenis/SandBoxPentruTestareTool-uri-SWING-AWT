package PaooGame.Tiles;

import java.awt.image.BufferedImage;

public class WallTile extends Tile{

    WallTile(BufferedImage image,int id)
    {
        super(image,id);
    }

    @Override
    public boolean IsSolid ()
    {
        return true;
    }

}
