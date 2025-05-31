package PaooGame.SceneManager;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Scenes.*;

import PaooGame.Scenes.Menu;
import PaooGame.Tiles.Tile;

import java.awt.*;

/// Scene manager -->  Trebuie sa incarce o scena diferita
/// Requirements --> Un mod de a incarca/descarca imaginea randata pe ecran
/// Solution --> Folosim un vector al carui index indica spre ce trebuie randat pe ecran in acel moment
/// Possible Issues --> Teoretic acest lucru ar functiona, insa cum rezolvam functionalitatea?
/// Solution --> Cream cate o clasa pentru fiecare nivel/scena
/// VER 0.4.6 --UP COMMENTARY (D) : sceneManager-ul este doar o metoda mai clean de a simplifica tranzitia de la nivel
/// la nivel folosind o mapa usor de rescris importata din clasele specifice nivelelor
/// la momentul upload-ului mai sunt probleme de performanta legate la strategia de incarcare in memorie a
/// tile-urilor in sensul ca in clasa Tile se afla incarcate toate tile-urile, lucru ce pentru niveluri mai multe
/// poate consuma mai multe resurse afectand overall performanta jocului
/// posibil fix pentru asta ar fi tot un loader asemanator cu levelLoader insa in urmatoarele versiuni
public class sceneManager {
     public enum levels  {Level_1,Level_2,Level_3,Menu};/// 0->lvl1 1->lvl2 2->lvl3 3->menu
     public static Level_Template current_level;
     public levels level_Default = levels.Menu;
     public static Boolean globalPlyState = false;
     public static int[][] getMap(){
         return current_level.getMap();
     }

    public static int posx,posy;
     public static void levelLoader(levels level_index){ /// Incarca nivelul si seteaza setarile initiale
         switch (level_index){
             case Level_1:
                 current_level = new Level_1();
                 globalPlyState=current_level.playerState();
                 current_level.levelInit();
                 break;
             case Level_2:
                 current_level = new Level_2();
                 globalPlyState=current_level.playerState();
                 current_level.levelInit();
                 break;
             case Level_3:
                 current_level = new Level_3();
                 globalPlyState=current_level.playerState();
                 current_level.levelInit();
                 break;
             case Menu:
                 current_level = new Menu();
                 globalPlyState=current_level.playerState();
                 current_level.levelInit();
                 break;
             default:
                 current_level = new Menu();
                 globalPlyState=current_level.playerState();
                 current_level.levelInit();
                 System.out.println("Invalid level index");
                 break;

         }
     }
    public static int getPosX() {
        return posx; }
    public static int getPosY() { return posy; }
    public static void setOffsets(int x, int y) {
        posx = x;
        posy = y;
    }
     public static void sceneStrategyDraw(Graphics g,GameWindow wnd) {/// Deseneaza pe contextul curent mapa
         current_level.levelDraw(g,wnd);
     }
     public static void sceneStrategyLogic(GameWindow wnd){/// Updateaza logica
        current_level.levelUpdate(wnd);
     }

     public static Level_Template levelReturn(){ /// Returneaza nivelul curent
         return current_level;
     }

}
