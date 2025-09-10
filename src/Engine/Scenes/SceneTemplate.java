package Engine.Scenes;

import java.awt.*;



public interface SceneTemplate {
    public int GetSceneID(); //This one returns and integer representing the index of the scene
    public void levelUpdate(); //This one's called every frame for updating everything related to the logic of the scene
    public void levelInit(); //This one's called only once at the beginning of the scene
    public void levelDraw(Graphics g); //This one's called every frame for Drawing
}
