package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;
/// Inchide intrarea la boss astfel ca player-ul sa nu mai iasa niciodata din boss-room
public class CloseBossRoom {
    public static Boolean activable = false;
    public static void closeBossRoom(){
        if(activable) {
            Level_Template cr = sceneManager.levelReturn();
            int mapindex = cr.getlevelindex();
            if (GameVariables.isBossRoom)
                switch (mapindex) {
                    case 1 -> {
                        cr.setMapTile(20, 25, 4);
                    }
                    case 2 -> {
                        cr.setMapTile(10, 34, 16);
                    }
                    case 3 -> {
                        cr.setMapTile(14, 28, 28);
                    }
                    default -> System.out.println("Error in closeBossRoom");
                }
            activable = false;
        }
    }
}
