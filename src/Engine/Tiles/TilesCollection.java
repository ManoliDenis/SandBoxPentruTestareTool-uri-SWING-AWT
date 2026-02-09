package Engine.Tiles;

import java.util.HashMap;

import Engine.Graphics.AssetsManagement.Assets;


public class TilesCollection {
    private static HashMap<String,Tile> Tiles;
    //This constructor's scope is to initialize the lists
    public TilesCollection() {
        Tiles = new HashMap<String,Tile>();
    }

    public static void newTile(String tileName, Assets assets,int id,boolean isSolid,boolean isTriggered,boolean isInteractive)
    {
        try
        {
            Tile temp = new Tile(assets,id,isSolid,isTriggered,isInteractive);
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
                 

                
            }
            default -> {System.out.println("Invalid scene ID: " + sceneID);}
        }
    }
}
