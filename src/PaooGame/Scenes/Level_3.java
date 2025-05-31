package PaooGame.Scenes;

import PaooGame.Game;
import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Main;
import PaooGame.MenuItems.Buttons;
import PaooGame.NPC.Enemies.Azgareth.AzgarethSpawner;
import PaooGame.NPC.Enemies.Ceye.CeyeSpawner;
import PaooGame.NPC.Enemies.Death.DeathSpawner;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Enemies.Kamikaze.KamikazeSpawner;
import PaooGame.NPC.Enemies.Rascal.Rascal;
import PaooGame.NPC.Enemies.Rascal.RascalSpawner;
import PaooGame.NPC.Enemies.SlimeKing.SlimeKingSpawner;
import PaooGame.NPC.EnemyManager;
import PaooGame.NPC.Spawner;
import PaooGame.PlayerInput.Player;
import PaooGame.PlayerInput.PlayerInput;
import PaooGame.Projectiles.Bullet;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Tiles.Tile;
import PaooGame.Tools.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.awt.*;
/// Info --> ducem pe sablon mod observer
/// Daca schimbam sarcinile dam email cu o zi inainte
/// Diagrame UML pentru documentatie S13-14
import static PaooGame.Game.collisionChecker;
import static PaooGame.Game.playerInput;
import static PaooGame.SceneManager.sceneManager.setOffsets;

public class Level_3 implements Level_Template {
    //////////////////////////////////////////////////////////////////////
    /// DATA & VARIABLES START/////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////
    public static final Color[] INTERACTIBLES = {Color.WHITE, Color.BLACK};

    public static final int[][] map = new int[29][49];

    public static Boolean pauseMenuState = false;
    public static Boolean pauseState = false;
    public static int INTERACTIBLES_INDEX = 0;
    private int posx, posy;
    public Boolean playerShouldAppear = true;
    public int saveIndex = 0;
    public Boolean saveMenu = false;
    public Boolean saveLock = false;
    boolean[] saveBTNStatus = {false,false,false} ;

    private final ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue<>();
    public String levelName = "Level 3";

    public int playerGlobalX,playerGlobalY;
    public int mapPositionX, mapPositionY;
    public int tileX,tileY;

    Buttons Resume, Save, Load, Menu;
    Buttons SaveGame;
    Buttons Save1;
    Buttons Slot1;
    Buttons Save2;
    Buttons Slot2;
    Buttons Save3;
    Buttons Slot3;
    Buttons SaveBack;


    Enemy entity;
    EnemyManager manager;
    Spawner rascal_spawner;
    /// ///////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////
    /// DATA VARIABLES END/////////////////////////////////////////////////


    @Override
    public  void spawnBullet(double startX, double startY, double targetX, double targetY) {
        bullets.add(new Bullet(startX, startY, targetX, targetY,
                Assets.bullet, Game.collisionChecker));
    }

    @Override
    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    /////////////////////////////////MAP READING//////////////////////////////
    public void readMap(String path) {
        try(Scanner input = new Scanner(new File(path))){
            int i=0,j;
            while(input.hasNextLine())
            {   j=0;
                Scanner colReader = new Scanner(input.nextLine());
                while(colReader.hasNextInt()) {
                    this.map[i][j] = colReader.nextInt();
                    j++;
                }
                i++;
                colReader.close();
            }
        }catch(Exception e){System.out.println("Error reading map file: "+ e);}
    }
    ////////////////////////MAP READING END/////////////////////////////////////




    ///////////////////RETURN MAP/////////////////////////////////
    public int[][] getMap() {
        return map;
    }

    ///////////////////RETURN MAP END/////////////////////////////



    /////////////////PLAYER STATE/////////////////////////////
    @Override
    public Boolean playerState() {
        return playerShouldAppear;
    }
    /////////////////PLAYER STATE END////////////////////////////



    /////////////////////LEVEL INIT/////////////////////////////
    public void levelInit() {
        System.out.println("Level " + levelName + " Init");
        GameVariables.isBossRoom = false;
        GameVariables.isBossDead = false;
        CloseBossRoom.activable = true;
        NextLevelDoor.activable = true;

        /////////////////////////////////////////////////MAP LOGIC///////////////////////////////////////////////////////////////////
        readMap("src/PaooGame/Scenes/Level3.txt");
        /////////////////////////////////////////////////MAP LOGIC END///////////////////////////////////////////////////////////////
        if(GameVariables.continuedlevel)
        {    mapPositionX = -310;
            mapPositionY = 150;

            GameVariables.continuedlevel = false;
            GameVariables.HP = 100;
            System.out.println("Saved");
        } else
        if(GameVariables.LOADED) {
            GameVariables.LOADED = false;
            mapPositionX = GameVariables.playerSaves.mapPositionX;
            mapPositionY = GameVariables.playerSaves.mapPositionY;
            GameVariables.SCORE  = GameVariables.playerSaves.currentScore;
            GameVariables.HP = GameVariables.playerSaves.hp;
            GameVariables.playerSaves = null;
            System.out.println("Loaded from file");

        } else {
        /////////////////////////////PLAYER LOGIC///////////////////////////////
        //Punem mapa pe pozitie echibalent cu iluzia de spawnpoint player-ul stand pe aceeasi coordonata tot timpul
        mapPositionX = -300;
        mapPositionY = 150;

        GameVariables.SCORE = 0;
        GameVariables.HP = 100;
        ///////////////////////////PLAYER LOGIC END//////////////////////////
    }
        GameVariables.HP+=50;
        GameVariables.playerATKDAMAGE = 10;
        //////////////////////////////ENEMY LOGIC///////////////////////////////
        rascal_spawner = new RascalSpawner(); ///Spawner de rascali(poate spawna doar rascali)
        entity = rascal_spawner.SpawnEnemy(1068,150,mapPositionX,mapPositionY);///1600,2600);///Entity e inamicul care devine rascal (1600,2600)
        manager = new EnemyManager();///1800 2600
        manager.add(new RascalSpawner().SpawnEnemy(1500,1150,mapPositionX,mapPositionY));
        manager.add(new RascalSpawner().SpawnEnemy(260,1060,mapPositionX,mapPositionY));
        manager.add(new RascalSpawner().SpawnEnemy(900,1300,mapPositionX,mapPositionY));
        manager.add(new DeathSpawner().SpawnEnemy(900,2400,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(905,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(906,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(907,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(905,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(906,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(907,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(905,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(906,1300,mapPositionX,mapPositionY));
        manager.add(new KamikazeSpawner().SpawnEnemy(907,1300,mapPositionX,mapPositionY));
        //////////////////////////ENEMY LOGIC END////////////////////////////


        //////////////////////////////////////////////////MENU LOGIC///////////////////////////////////////////////////////////////////////
        //Main
        Resume = new Buttons("Resume", WindowVariables.HEIGHT/2 + (4*WindowVariables.HEIGHT/100) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);//(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100),false,true);
        Save = new Buttons("Save",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*27/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        Menu = new Buttons("Quit",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*44/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        //Save
        Save3 = new Buttons("Save File 3", WindowVariables.HEIGHT/2 - ((5*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot3 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((5*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);

        Save2 = new Buttons("Save File 2", WindowVariables.HEIGHT/2 - ((4*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot2 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((4*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);

        Save1 = new Buttons("Save File 1", WindowVariables.HEIGHT/2 - ((3*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot1 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((3*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);

        SaveGame = new Buttons("Save", WindowVariables.HEIGHT/2 - ((0*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        SaveBack = new Buttons("Back", WindowVariables.HEIGHT/2 - ((-2*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        /// Options

        INTERACTIBLES_INDEX = 3;
        /////////////////////////////////////////////////MENU LOGIC END///////////////////////////////////////////////////////////////////
    }
    //////////////////////////////////////////////////LEVEL INIT END////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////MAP LEVEL UPDATE///////////////////////////////////////////////////////////////////
    @Override
    public void levelUpdate(GameWindow wnd) {
        System.out.println("X: " + playerGlobalX + "Y: " + playerGlobalY);
        /////////////////////////////////////////////////DEBUG AREA///////////////////////////////////////////////////////////////////
        if(GameVariables.DEBUG)
            System.out.println("I'm updating");
        /////////////////////////////////////////////////DEBUG AREA END///////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////GAMEPLAY///////////////////////////////////////////////////////////////////
        if(!pauseState) {
            if (PlayerInput.isEscPressed()) {
                pauseState = true;

                /// BLOCK TIMER
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("Thread sleep error");
                }
                /// BLOCK DELAY


            }

            playerPosUpdate();
            collisionupdate(wnd);
            calculateTilePost();

            if(map[tileX][tileY] == 14)//Daca ajungem pe tila aia atunci ea ne transporta la nivelul urmator
            {
                NameReader menu = new NameReader();

                // Așteaptă până când utilizatorul apasă OK
                while (menu.getName() == null) {
                    try {
                        Thread.sleep(100); // mic delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                GameVariables.PLAYER_NAME = menu.getName();
                GameVariables.HALLOFFAME_MODULE.addPlayer(GameVariables.PLAYER_NAME,GameVariables.SCORE);
                sceneManager.levelLoader(sceneManager.levels.Menu);
                return;
            }

            WCTD.think();
            manager.update(mapPositionX,mapPositionY);
            NextLevelDoor.nextLevelDoor(3);
        }/////////////////////////////////////////////////GAMEPLAY END///////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////MENU LOGIC///////////////////////////////////////////////////////////////////
        else{
            System.out.println("Index:" + INTERACTIBLES_INDEX);
            pauseMenuLogic();
            if(saveMenu)
                levelSaveLogicMenu();
            if(PlayerInput.isEscPressed())
                pauseState = false;
        }
        /////////////////////////////////////////////////MENU LOGIC END///////////////////////////////////////////////////////////////////
        int[][] map = getMap();  // harta curenta
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            if (!b.update(sceneManager.getPosX(), sceneManager.getPosY(), map)) {
                it.remove();
            }
        }
    }
    /////////////////////////////////////////////////MAP LEVEL UPDATE END///////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////LEVEL DRAW START///////////////////////////////////////////////////////////////////
    public void levelDraw(Graphics g, GameWindow wnd) {
        if(!pauseState) {
            sceneManager.globalPlyState = true;
            WCN.think();//Pentru a anima toate entitatile ce necesita animatie odata la 10 frame-uri
            if(GameVariables.DEBUG)
                System.out.println(GameVariables.WORLD_CAN_ANIMATE + "Animating");
            /// Background
            g.setColor(new Color(37, 19, 26));
            g.fillRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

            /// Tiles pentru map
            this.setMapTile(18, 44, 34);//Patch
            this.setMapTile(18, 43, 34);//Patch
            this.setMapTile(19, 43, 34);//Patch
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    int tileId = map[i][j];
                    if (Tile.tiles[tileId] != null)
                        Tile.tiles[tileId].Draw(g, mapPositionX + i * Tile.TILE_WIDTH, mapPositionY + j * Tile.TILE_HEIGHT);
                    else
                        System.out.println("Tile " + tileId + " is null");
                }
            }

            /// Player
            if (playerShouldAppear) {
                Tile.playerTile.Draw(g, WindowVariables.WIDTH / 2, WindowVariables.HEIGHT / 2);
            }

            /// Enemy
            manager.render(g);
            DrawHUD.drawHUD(g);
        }else
        {
            sceneManager.globalPlyState = false;
            pauseMenu(g, wnd);
        }

        for (Bullet b : bullets) {
            b.draw(g, mapPositionX, mapPositionY); // desenez bullets pt player
        }
        DeathScreen.deathScreen(g);
    }

    @Override
    public int getGlobalPlayerX() {
        return playerGlobalX;
    }

    @Override
    public int getGlobalPlayerY() {
        return playerGlobalY;
    }

    @Override
    public void setMapTile(int x, int y, int tile) {
        this.map[x][y] = tile;
    }

    @Override
    public int getlevelindex() {
        return 3;
    }
    /////////////////////////////////////////////////LEVEL DRAW END///////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////PAUSE MENU LOGIC///////////////////////////////////////////////////////////////////
    public void pauseMenu(Graphics g, GameWindow wnd) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        if(!saveMenu) {
            Resume.ButtonRender(g);
            Save.ButtonRender(g);
            Menu.ButtonRender(g);
        } else{
            levelSaveDraw(g);
        }
    }
    /////////////////////////////////////////////////PAUSE MENU LOGIC END///////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////MENU LOGIC///////////////////////////////////////////////////////////////////
    public void pauseMenuLogic() {
        if(!saveMenu) {
            if (PlayerInput.isEnterPressed()) {
                switch (INTERACTIBLES_INDEX) {
                    case 2:
                        pauseState = false;
                        saveMenu = false;
                        break;
                    case 1:
                        saveMenu = true;
                        break;
                    case 0:
                        pauseState = false;
                        sceneManager.levelLoader(sceneManager.levels.Menu);
                        break;
                }
            }

            /// BLOCK TIMER
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY


            if (PlayerInput.isUpPressed()) {
                INTERACTIBLES_INDEX++;
                if (INTERACTIBLES_INDEX > 2)
                    INTERACTIBLES_INDEX = 0;
            }

            if (PlayerInput.isDownPressed()) {
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 2;
            }
            switch (INTERACTIBLES_INDEX) {
                case 2:
                    Resume.setButtonSelected(true);
                    Save.setButtonSelected(false);
                    Menu.setButtonSelected(false);
                    break;
                case 1:
                    Save.setButtonSelected(true);
                    Resume.setButtonSelected(false);
                    Menu.setButtonSelected(false);
                    break;
                case 0:
                    Menu.setButtonSelected(true);
                    Resume.setButtonSelected(false);
                    Save.setButtonSelected(false);
                    break;
                default:
                    //System.out.println("Something went wrong");
                    break;
            }
        } else {
            /// BLOCK TIMER
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY
            if (PlayerInput.isEnterPressed()) {  /// Pentru selectia optiunii
                switch (INTERACTIBLES_INDEX) {  /// Daca e selectata
                    case 2 ->{if(!saveLock){saveLock=true;saveIndex=1;}else{saveLock=false;saveIndex=0;}}/// Save1
                    case 3 ->{if(!saveLock){saveLock=true;saveIndex=2;}else{saveLock=false;saveIndex=0;}}/// Save2
                    case 4 ->{if(!saveLock){saveLock=true;saveIndex=3;}else{saveLock=false;saveIndex=0;}}/// Save3
                    case 1 ->{if(saveLock) {System.out.println("Saved");GameVariables.SAVE_MODULE.Save(saveIndex,GameVariables.PLAYER_NAME,mapPositionX,mapPositionY,3,GameVariables.HP,GameVariables.SCORE);saveLock=false;}}/// Load
                    case 0 ->{saveMenu = false;saveLock = false;saveIndex=0; } /// Back
                }///Pe scurt in functie de Interactibles index selecteaza butonul daca e save ii da lock si salveaza indexu
            }///In caz ca inca e savelock si apas unul dintre load sau delete in functie de setare face load sau delete

            /// BLOCK TIMER
            try {
                Thread.sleep(65);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY

            if (PlayerInput.isUpPressed()) { /// Pentru a da switch la optiuni spre directia sus
                INTERACTIBLES_INDEX++;
                if (INTERACTIBLES_INDEX > 5)
                    INTERACTIBLES_INDEX = 0;

            }
            if (PlayerInput.isDownPressed()) { /// Pentru a da switch la optiuni spre directia jos
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 5;

            }


        }

    }
    /////////////////////////////////////////////////MENU LOGIC END///////////////////////////////////////////////////////////////////

    public void levelSaveLogicMenu(){
        if(saveLock)
            if(saveIndex==1) Save1.setButtonSelected(true);
            else if(saveIndex==2) Save2.setButtonSelected(true);
            else if(saveIndex==3) Save3.setButtonSelected(true);
        switch (INTERACTIBLES_INDEX) {
            case 4:
                if(saveLock && saveIndex==1) {
                    Save1.setButtonSelected(true);
                    Slot1.setButtonSelected(true);
                }else{
                    Save1.setButtonSelected(false);
                    Slot1.setButtonSelected(false);
                }
                if(saveLock && saveIndex==2) {
                    Save2.setButtonSelected(true);
                    Slot2.setButtonSelected(true);
                }else{
                    Save2.setButtonSelected(false);
                    Slot2.setButtonSelected(false);
                }


                Slot3.setButtonSelected(true);
                Save3.setButtonSelected(true);
                SaveGame.setButtonSelected(false);
                SaveBack.setButtonSelected(false);
                break;
            case 3:
                if(saveLock && saveIndex==1) {
                    Save1.setButtonSelected(true);
                    Slot1.setButtonSelected(true);
                }else{
                    Save1.setButtonSelected(false);
                    Slot1.setButtonSelected(false);
                }
                if(saveLock && saveIndex==3) {
                    Save3.setButtonSelected(true);
                    Slot3.setButtonSelected(true);
                }else{
                    Save3.setButtonSelected(false);
                    Slot3.setButtonSelected(false);
                }

                Save2.setButtonSelected(true);
                Slot2.setButtonSelected(true);
                SaveGame.setButtonSelected(false);
                SaveBack.setButtonSelected(false);
                break;
            case 2:
                if(saveLock && saveIndex==2) {
                    Save2.setButtonSelected(true);
                    Slot2.setButtonSelected(true);
                }else{
                    Save2.setButtonSelected(false);
                    Slot2.setButtonSelected(false);
                }
                if(saveLock && saveIndex==3) {
                    Save3.setButtonSelected(true);
                    Slot3.setButtonSelected(true);
                }else{
                    Save3.setButtonSelected(false);
                    Slot3.setButtonSelected(false);
                }
                Save1.setButtonSelected(true);
                Slot1.setButtonSelected(true);
                SaveGame.setButtonSelected(false);
                SaveBack.setButtonSelected(false);
                break;
            case 1:
                if(saveLock && saveIndex==1) {
                    Save1.setButtonSelected(true);
                    Slot1.setButtonSelected(true);
                }else{
                    Save1.setButtonSelected(false);
                    Slot1.setButtonSelected(false);
                }
                if(saveLock && saveIndex==2) {
                    Save2.setButtonSelected(true);
                    Slot2.setButtonSelected(true);
                }else{
                    Save2.setButtonSelected(false);
                    Slot2.setButtonSelected(false);
                }
                if(saveLock && saveIndex==3) {
                    Save3.setButtonSelected(true);
                    Slot3.setButtonSelected(true);
                }else{
                    Save3.setButtonSelected(false);
                    Slot3.setButtonSelected(false);
                }
                SaveGame.setButtonSelected(true);
                SaveBack.setButtonSelected(false);
                break;
            case 0:
                if(saveLock && saveIndex==1) {
                    Save1.setButtonSelected(true);
                    Slot1.setButtonSelected(true);
                }else{
                    Save1.setButtonSelected(false);
                    Slot1.setButtonSelected(false);
                }
                if(saveLock && saveIndex==2) {
                    Save2.setButtonSelected(true);
                    Slot2.setButtonSelected(true);
                }else{
                    Save2.setButtonSelected(false);
                    Slot2.setButtonSelected(false);
                }
                if(saveLock && saveIndex==3) {
                    Save3.setButtonSelected(true);
                    Slot3.setButtonSelected(true);
                }else{
                    Save3.setButtonSelected(false);
                    Slot3.setButtonSelected(false);
                }

                SaveBack.setButtonSelected(true);
                SaveGame.setButtonSelected(false);
                break;
            default:
                //System.out.println("Something went wrong");
                INTERACTIBLES_INDEX = 5;
                break;
        }
    }

    /////////////////////////////////////////////////PLAYER POS UPDATE///////////////////////////////////////////////////////////////////
    public void playerPosUpdate(){
        playerGlobalX = -mapPositionX + WindowVariables.WIDTH/2;
        playerGlobalY = -mapPositionY + WindowVariables.HEIGHT/2;

        if(GameVariables.DEBUG)
            System.out.println("Player global x: " + playerGlobalX + " Player global y: " + playerGlobalY);
    }
    /////////////////////////////////////////////////PLAYER POS UPDATE END///////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////COLLISIONUPDATE LOGIC///////////////////////////////////////////////////////////////////
    public void collisionupdate( GameWindow wnd){
        if(sceneManager.globalPlyState) {
            int newmapPositionX = mapPositionX + (int) playerInput.getSpeedX();
            int newmapPositionY = mapPositionY + (int) playerInput.getSpeedY();
            if (!collisionChecker.checkCollision(newmapPositionX, mapPositionY, wnd)) {
                mapPositionX = newmapPositionX;
            }
            if (!collisionChecker.checkCollision(mapPositionX, newmapPositionY, wnd)) {
                mapPositionY = newmapPositionY;
            }
        }
        setOffsets(mapPositionX, mapPositionY);
    }
    /////////////////////////////////////////////////COLLISIONUPDATE LOGIC END///////////////////////////////////////////////////////////////////

    public void calculateTilePost(){
        this.tileX = this.playerGlobalX / Tile.TILE_WIDTH;
        this.tileY = this.playerGlobalY / Tile.TILE_HEIGHT;
        if(!    GameVariables.DEBUG)
            System.out.println("Index tilaX: " + tileX + " tilaY: " + tileY +" id: " + map[tileX][tileY]);
    }

    public void levelSaveDraw(Graphics g){
        saveBTNStatus = GameVariables.SAVE_MODULE.getUsedSlots();
        if ((saveBTNStatus[0] == true)) {
            Save1.ButtonRender(g);
        } else {
            Slot1.ButtonRender(g);
        }
        if ((saveBTNStatus[1] == true)) {
            Save2.ButtonRender(g);
        } else {
            Slot2.ButtonRender(g);
        }
        if ((saveBTNStatus[2] == true)) {
            Save3.ButtonRender(g);
        } else {
            Slot3.ButtonRender(g);
        }
        SaveGame.ButtonRender(g);
        SaveBack.ButtonRender(g);
    }
}

