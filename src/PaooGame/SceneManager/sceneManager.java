package PaooGame.SceneManager;

import PaooGame.Scenes.*;
import PaooGame.Scenes.Menu;
import java.awt.*;

public class sceneManager {
     private static SceneTemplate Scene;
     private static Boolean SceneJL = false;

    //This function's scope is to load a level based on an ID
     public static void sceneLoader(int sceneID) {
         SceneJL = true;
         //In order to be able to load more levels you will need to add more cases
         switch (sceneID){
             case 0: Scene = new Menu(); break;
             case 1: Scene = new Sandbox(); break;
             default: break;
         }
     }
    //This function's scope is to update everything
     public static void sceneUpdate(Graphics g) {
         try {
             if (SceneJL) {
                 sceneInit();
                 SceneJL = false;
             } else {
                 sceneLogic();
                 sceneDraw(g);
             }
         }catch (NullPointerException e) {if(Scene != null) {System.out.println("Graphics(g) not initialized");}else if(g != null) System.out.println("Scene not initialized"); else System.out.println("Graphics and Scene are not initialized");}
     }

     //This function's scope is only to draw
     public static void sceneDraw(Graphics g) {
         Scene.levelDraw(g);
     }
    //This function's scope is only to calculate
     public static void sceneLogic(){
        Scene.levelUpdate();
     }
    //This function's scope is only to be executed once
     public static void sceneInit(){
         Scene.levelInit();
     }
    //This function's scope is only to return the current scene
     public static SceneTemplate sceneGet(){
         return Scene;
     }

}
