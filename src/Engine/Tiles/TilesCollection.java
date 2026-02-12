package Engine.Tiles;

import Engine.Graphics.AssetsManagement.Assets;
import java.util.HashMap;


public class TilesCollection {
    private static HashMap<String,Tile> Tiles;
    //This constructor's scope is to initialize the lists
    public TilesCollection() {
        Tiles = new HashMap<String,Tile>();
    }

    public static void newTile(String tileName, Assets asset,int id,boolean isSolid,boolean isTriggered,boolean isInteractive)
    {
        try
        {
            Tile temp = new Tile(asset,id,isSolid,isTriggered,isInteractive);
            Tiles.put(tileName, temp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void loadTiles(int sceneID) { 
        switch (sceneID) {
            case 1 -> { 
                 //TODO:THINK OF A WAY TO LOAD TILES
                
            }
            default -> {System.out.println("Invalid scene ID: " + sceneID);}
        }
    }
}
