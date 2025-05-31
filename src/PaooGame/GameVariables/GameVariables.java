package PaooGame.GameVariables;

import PaooGame.DataBase.DataManagement.HallofFame;
import PaooGame.DataBase.DataManagement.PlayerSaveDataObject;
import PaooGame.DataBase.DataManagement.PlayerSaves;
import PaooGame.SceneManager.sceneManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class GameVariables {
    public static Boolean DEBUG = false;
    public static PlayerSaves SAVE_MODULE = null;
    public static HallofFame HALLOFFAME_MODULE = null;
    public static int SCORE = 0;
    public static Boolean WORLD_CAN_ANIMATE = false;
    public static Boolean WORLD_CAN_TAKE_DAMAGE = false;
    public static String PLAYER_NAME = "";
    public static Boolean LOADED = false;
    public static PlayerSaveDataObject playerSaves = null;
    public static int HP;
    public static int MAX_HP = 100;
    public static int playerATKDAMAGE = 10;
    public static Boolean continuedlevel = false;
    public static Boolean isBossRoom = false;
    public static Boolean isBossDead = false;
    public static final int playerTAKEDAMAGE =10;
    public static int enemieskilled = 0;

    public static sceneManager.levels saveDataLevelTranslator(int lvl){
        switch (lvl){
            case 1 -> {return sceneManager.levels.Level_1;}
            case 2 -> {return sceneManager.levels.Level_2;}
            case 3 -> {return sceneManager.levels.Level_3;}
            default ->{System.out.println("Level not found");return null;}
        }
    }
    Set<Integer> s = new HashSet<>();

}
