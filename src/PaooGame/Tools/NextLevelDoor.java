package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;

/// Deblocheaza tile-ul de trecere la nivelul urmator daca ai batut boss-ul
public class NextLevelDoor {
    public static Boolean activable = false;
    public static void nextLevelDoor(int mapindex){
        //System.out.println("nextLevelDoor-> activable:" + activable + " isBossDead:" + GameVariables.isBossDead + " mapindex:" + mapindex);
        if(activable && GameVariables.isBossDead) {
            Level_Template cr = sceneManager.levelReturn();
            //int mapindex = cr.getlevelindex();
            System.out.println("mapindex:"+mapindex);
                switch (mapindex) {
                    case 1 -> {
                        cr.setMapTile(10, 23, 0);
                        cr.setMapTile(11, 23, 0);

                    }
                    case 2 -> {

                        cr.setMapTile(18, 44, 15);
                        cr.setMapTile(18, 43, 15);
                        cr.setMapTile(19, 43, 15);
                    }
                    case 3 -> {

                        cr.setMapTile(15, 47, 34);
                    }
                    default -> System.out.println("Error in NextBossRoom");
                }
            activable = false;

        }
    }
}
