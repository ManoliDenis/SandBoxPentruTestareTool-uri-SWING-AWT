package Engine.GameVariables;

import Engine.Tiles.TilesCollection;

public class GameVariables {
    public static String PATH = System.getProperty("user.dir");
    public static String TEXTURES_PATH = (System.getProperty("user.dir")+"\\res\\textures\\");
    public static Boolean DEBUG = false;
    public static TilesCollection tilesCollection;
}
