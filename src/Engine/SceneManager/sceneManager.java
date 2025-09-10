package Engine.SceneManager;

/// sceneManager manages scripts that inherit the SceneTemplate interface , it's only purpose being the management of scenes( aka levels )
///  sceneManager functions: sceneLoader(int) loads a scene based on an integer {ex: sceneLoader(0) loads the first Scene inside the switch}
///                          sceneUpdate() constantly calls the update() and draw() methods consecutively
///                          sceneGet() returns a SceneTemplate of the currently loaded scene
///                          sceneName(SceneID) returns an integer representing the index of a level (ex: sceneName(SceneID.Menu) returns 0 )
/// Last checked: 10/09/2025 4:37PM

import Engine.GameVariables.GameSceneIndex;
import Engine.Scenes.*;
import Engine.Scenes.Menu;
import Engine.Tiles.TilesCollection;

import java.awt.*;

public class sceneManager {
     private static SceneTemplate Scene; //Interface for an easier way to browse between the scenes
     private static Boolean SceneJL = false; //SceneJL = SceneJustLoaded , a check to see if a scene has been loaded recently(turns false after sceneInit() function ends)
     public static Boolean SceneL = false; //SceneL = SceneLoaded , a check to see if a scene is currently loaded
     public enum SceneID{Menu, Sandbox} //Current index of levels

    //This function's scope is to load a level based on an ID
     public static void sceneLoader(int sceneID) {//Switches a scene based on an integer value called index(not the same as the SceneID)

         if(GameSceneIndex.SceneIndex.contains(sceneID))
             //In order to be able to load more levels you will need to add more cases
             try {
                 if(!SceneL) //If no scene is loaded( scenes must set SceneL's value to false at the end of their termination sequence )
                     switch (sceneID) {
                         case 0:
                             Scene = new Menu();
                             break;
                         case 1:
                             Scene = new Sandbox();
                             break;
                     /* Ex:
                     case 3: Scene = new SceneGenericName0();break;
                     case 4: Scene = new SceneGenericName1();break;
                     case 5 Scene = new SceneGenericName2();break;
                      */
                         default:
                             System.out.println("\n ERROR: NO SUCH SCENE EXISTS!!!\n");
                             break;
                     }
             } catch (Exception e) {
                 System.out.println("\n ERROR:sceneLoader related error \n" +e.fillInStackTrace() + "\n ERROR:sceneLoader related error \n");
                 System.exit(-1);
             } finally {
                 SceneJL = true;
                 SceneL = true;
             }
         else System.out.println("\n ERROR: NO SUCH SCENE EXISTS!!!\n");

     } ///PERFECT


    //This function's scope is to update everything(meaning it continuously calls the update function from within the scene, the only exception being the init())
     public static void sceneUpdate(Graphics g) {//By updating we refer to triggering an initial function (that executes once),
         try {
             if (SceneJL) {

                 TilesCollection.loadTiles(Scene.GetSceneID());
                 try {
                     sceneInit();
                 } catch (Exception e) {
                     System.out.println("\n ERROR:sceneUpdate related error \n" +e.fillInStackTrace() + "\n ERROR:sceneUpdate related error \n");
                     System.exit(-1);
                 } finally {
                     SceneJL = false;
                 }

             } else {
                 sceneLogic(); //Manages what, where, how and if an entity/model should be drawn or not
                 sceneDraw(g); //Draws everything that can be drawn
             }
         }catch (NullPointerException e) {if(Scene != null) {System.out.println("Graphics(g) not initialized");}else if(g != null) System.out.println("Scene not initialized"); else System.out.println("Graphics and Scene are not initialized");}
     } ///PERFECT

     //This function's scope is only to draw
     public static void sceneDraw(Graphics g) {
         Scene.levelDraw(g);
     }///PERFECT

    //This function's scope is only to calculate
     public static void sceneLogic(){
        Scene.levelUpdate();
     }///PERFECT

    //This function's scope is only to be executed once
     public static void sceneInit(){
         Scene.levelInit();
     }///PERFECT

    //This function's scope is only to return the current scene
     public static SceneTemplate sceneGet(){
         return Scene;
     }///PERFECT

    //This function's scope is to only return an integer version of the scene's id
     public static int sceneName(SceneID scene){
         switch (scene){
             case SceneID.Menu -> {return 0;}
             case SceneID.Sandbox -> {return 1;}
             default -> throw new IllegalStateException("Unexpected value: " + scene);
         }
     }///PERFECT

}
