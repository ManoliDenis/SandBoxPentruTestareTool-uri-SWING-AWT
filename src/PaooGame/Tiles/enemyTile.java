package PaooGame.Tiles;

import java.awt.image.BufferedImage;

public class enemyTile extends Tile{

            public enemyTile(BufferedImage img, int id)
            {
                super(img, id);
            }
            @Override
            public boolean IsSolid()
            {
                return false;
            }
}
