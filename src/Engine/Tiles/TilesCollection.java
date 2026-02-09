package Engine.Tiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;


public class TilesCollection {
    private static HashMap<String,Tile> Tiles;
    //This constructor's scope is to initialize the lists
    public TilesCollection() {
        Tiles = new HashMap<String,Tile>();
    }

    private static void newTile(String tileName, BufferedImage tileTexture,int id,boolean isSolid,boolean isTriggered,boolean isInteractive)
    {
        try
        {
            Tile temp = new Tile(tileTexture,id,isSolid,isTriggered,isInteractive);
            Tiles.put(tileName, temp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void loadTiles(int sceneID) { 
        switch (sceneID >= 0) {
            case true -> { 
                 //TODO:THINK OF A WAY TO LOAD TILES

                
            }
            default -> {System.out.println("Invalid scene ID: " + sceneID);}
        }
    }
}
