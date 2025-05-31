package PaooGame.Tools;

import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;
import PaooGame.Tiles.Tile;

public class EnemyTilePos {
    public static boolean canMoveThere(int x,int y){//Folosit pentru prezicerea tipului tila
        try{
            Level_Template cl = sceneManager.levelReturn();
            /// brief: imparte pozitia actuala pe matricea de tile la marimea unei tile exemplu si calculeaza
            /// astfel pozitia blocului din matrice pe care ne aflam
            int tileX = x / Tile.TILE_WIDTH;
            int tileY = y / Tile.TILE_HEIGHT;
            int id = cl.getMap()[tileX][tileY];
            //System.out.println(id);
            return Tile.tiles[id].IsSolid();

        }catch (Exception e){
            System.out.println("Error in canMoveThere");
            e.printStackTrace();
            return false;
         }

    }
}
