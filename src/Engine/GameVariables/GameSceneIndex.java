package Engine.GameVariables;

import java.util.HashSet;
import java.util.Set;

public class GameSceneIndex {
    public static Set<Integer> SceneIndex = new HashSet<Integer>();
    public static void GameSceneIndexInit(){
        SceneIndex.add(0);// <- MENU
        SceneIndex.add(1);// <- SANDBOX
    }
}
