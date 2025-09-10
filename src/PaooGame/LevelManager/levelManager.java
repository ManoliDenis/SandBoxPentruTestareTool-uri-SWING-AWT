package PaooGame.LevelManager;

import PaooGame.SceneManager.sceneManager;

/// Level manager
/// 1.Manages levels, switches consecutively between levels
/// 2.Works like an interface between sceneManager and Game
/// 3.Works automatically like a shift being only pushed by scenes
public class levelManager {
    public static void ignition(){
        sceneManager.sceneLoader(0);
    }
    public static void switchGear(int sceneIndex){
        sceneManager.sceneLoader(sceneIndex);
    }

}
