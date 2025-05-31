package PaooGame.Scenes;

import PaooGame.DataBase.DataManagement.HallofFame;
import PaooGame.DataBase.DataManagement.HallofFameDataObject;
import PaooGame.Game;
import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Main;
import PaooGame.MenuItems.Buttons;
import PaooGame.PlayerInput.PlayerInput;
import PaooGame.Projectiles.Bullet;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Tiles.Tile;
import PaooGame.Tools.Effects.FadeIn;
import PaooGame.Tools.Effects.FadeOut;
import PaooGame.Tools.Timer;

import java.awt.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Menu implements Level_Template {
    public Boolean playerShouldAppear = false;
    private static final int[][] map = {};
    private static final int[][] HUD = {};
    private int posx,posy;
    private static final Color[] INTERACTIBLES= {Color.WHITE,Color.BLACK};
    private static int INTERACTIBLES_INDEX=3;
    public static int saveIndex = 0;
    public Boolean justLoaded = false;
    public Boolean waitingInput = false;
    public Boolean LevelLoadMenu = false;
    public Boolean MainMenu = false;
    public Boolean OptionsMenu = false;
    public Boolean HallofFame = false;
    public Boolean saveLock = false;
    boolean[] saveBTNStatus = {false,false,false} ;

    ///Database
    ArrayList<HallofFameDataObject> hofEntries;
    /// Database

    ///Buttons
    Buttons play;
    Buttons Load;
    Buttons Options;
    Buttons quit;
    Buttons HallofFameButton;
    Buttons LoadGame;
    Buttons DeleteSave;
    Buttons Save1;
    Buttons Slot1;
    Buttons Save2;
    Buttons Slot2;
    Buttons Save3;
    Buttons Slot3;
    Buttons LoadBack;
    Buttons OptionsBack;
    Buttons HallofFameBack;
    ///Buttons END
    public int [][] getMap() {
        return map;
    }


    private void menu_logic() { /// Logica meniului si functionalitatea individuala a optiunilor

        if(MainMenu) {
            /// BLOCK TIMER
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY
            if (PlayerInput.isEnterPressed()) {  /// Pentru selectia optiunii

                switch (INTERACTIBLES_INDEX) {  /// Daca e selectata
                    case 1:
                        LevelLoadMenu = false;
                        MainMenu = false;
                        OptionsMenu = false;
                        HallofFame = true;
                        break;
                    case 3:///Incarca nivelul 1
                        sceneManager.levelLoader(sceneManager.levels.Level_1);
                        break;
                    case 2:
                        LevelLoadMenu = true;
                        MainMenu = false;
                        OptionsMenu = false;
                        HallofFame = false;
                        break;
//                    case 1:
//                        OptionsMenu = true;
//                        MainMenu = false;
//                        LevelLoadMenu = false;
//                        HallofFame = false;
//                        break;

                    case 0:///Iese din program
                        System.out.println("END");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not yet implemented: " + INTERACTIBLES_INDEX + " is not yet implemented in Menu.java");
                        break;
                }
            }

            /// BLOCK TIMER
            try {
                Thread.sleep(65);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY

            if (PlayerInput.isUpPressed()) { /// Pentru a da switch la optiuni spre directia sus
                INTERACTIBLES_INDEX++;
                if (INTERACTIBLES_INDEX > 4)
                    INTERACTIBLES_INDEX = 0;
                waitingInput = false;
            }
            if (PlayerInput.isDownPressed()) { /// Pentru a da switch la optiuni spre directia jos
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 4;
                waitingInput = false;
            }


        }
        else if(OptionsMenu){
            /// BLOCK TIMER
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY
            if (PlayerInput.isEnterPressed()) {  /// Pentru selectia optiunii
                switch (INTERACTIBLES_INDEX) {  /// Daca e selectata
                    case 0:
                        OptionsMenu = false;
                        MainMenu = true;
                        LevelLoadMenu = false;
                        HallofFame = false;
                        break;
                    default:
                        System.out.println("Not yet implemented: " + INTERACTIBLES_INDEX + " is not yet implemented in Menu.java");
                        break;
                }
            }

            /// BLOCK TIMER
            try {
                Thread.sleep(65);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY

            if (PlayerInput.isUpPressed()) { /// Pentru a da switch la optiuni spre directia sus
                INTERACTIBLES_INDEX++;
                if (INTERACTIBLES_INDEX > 0)
                    INTERACTIBLES_INDEX = 0;
                waitingInput = false;
            }
            if (PlayerInput.isDownPressed()) { /// Pentru a da switch la optiuni spre directia jos
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 0;
                waitingInput = false;
            }


        }else if(LevelLoadMenu){
            /// BLOCK TIMER
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY
            saveBTNStatus = GameVariables.SAVE_MODULE.getUsedSlots();
            if (PlayerInput.isEnterPressed()) {  /// Pentru selectia optiunii
                System.out.println("saveLock,btns[0],btns[1],btns[2] :" + saveLock + ", " + saveBTNStatus[0] + ", " + saveBTNStatus[1] + ", " + saveBTNStatus[2]  );
                switch (INTERACTIBLES_INDEX) {  /// Daca e selectata
                    case 3 ->{if(!saveLock && saveBTNStatus[0] == true){saveLock=true;saveIndex=1;System.out.println("Locking on save nr. " + saveIndex);}}/// Save1
                    case 4 ->{if(!saveLock && saveBTNStatus[1] == true){saveLock=true;saveIndex=2;System.out.println("Locking on save nr. " + saveIndex);}}/// Save2
                    case 5 ->{if(!saveLock && saveBTNStatus[2] == true){saveLock=true;saveIndex=3;System.out.println("Locking on save nr. " + saveIndex);}}/// Save3
                    case 2 ->{if(saveLock) {System.out.println("Loading");GameVariables.LOADED = true;GameVariables.playerSaves = GameVariables.SAVE_MODULE.Load(saveIndex);sceneManager.levelLoader(GameVariables.saveDataLevelTranslator(GameVariables.playerSaves.currentLevel));}}/// Load
                    case 1 ->{if(saveLock) {System.out.println("Deleting");GameVariables.SAVE_MODULE.removeSlot(saveIndex);saveLock = false;}}/// Delete
                    case 0 ->{LevelLoadMenu = false; MainMenu = true; OptionsMenu = false;saveLock = false;saveIndex=0; } /// Back
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
                waitingInput = false;
            }
            if (PlayerInput.isDownPressed()) { /// Pentru a da switch la optiuni spre directia jos
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 5;
                waitingInput = false;
            }


        }else if(HallofFame){
            /// BLOCK TIMER
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY
            if (PlayerInput.isEnterPressed()) {  /// Pentru selectia optiunii
                switch (INTERACTIBLES_INDEX) {  /// Daca e selectata
                    case 0:
                        OptionsMenu = false;
                        MainMenu = true;
                        LevelLoadMenu = false;
                        HallofFame = false;
                        break;
                    default:
                        System.out.println("Not yet implemented: " + INTERACTIBLES_INDEX + " is not yet implemented in Menu.java");
                        break;
                }
            }

            /// BLOCK TIMER
            try {
                Thread.sleep(65);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
            /// BLOCK DELAY

            if (PlayerInput.isUpPressed()) { /// Pentru a da switch la optiuni spre directia sus
                INTERACTIBLES_INDEX++;
                if (INTERACTIBLES_INDEX > 0)
                    INTERACTIBLES_INDEX = 0;
                waitingInput = false;
            }
            if (PlayerInput.isDownPressed()) { /// Pentru a da switch la optiuni spre directia jos
                INTERACTIBLES_INDEX--;
                if (INTERACTIBLES_INDEX < 0)
                    INTERACTIBLES_INDEX = 0;
                waitingInput = false;
            }


        }else System.out.println("Something went wrong in Menu.java");

    }


    @Override
    public void levelDraw(Graphics g, GameWindow wnd) {
       ///Backgroundul negru
        g.setColor(new Color(0, 15, 43));
        g.fillRect(0, 0, WindowVariables.WIDTH, WindowVariables.HEIGHT);

        /// MenuDraw Method
        if(MainMenu)
            menuDraw(g);
        else if(OptionsMenu)
            optionDraw(g);
        else if(LevelLoadMenu)    
            levelLoadDraw(g);
        else if(HallofFame)
            HallofFameDraw(g);
        else System.out.println("Something went wrong in Menu.java");
    }

    @Override
    public int getGlobalPlayerX() {
        return 0;
    }

    @Override
    public int getGlobalPlayerY() {
        return 0;
    }

    @Override
    public void setMapTile(int x, int y, int tile) {
        System.out.println("Nah I'd WIN");
    }

    @Override
    public int getlevelindex() {
        return 0;
    }

    @Override
    public void spawnBullet(double startX, double startY, double targetX, double targetY) {

    }

    @Override
    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return null;
    }

    public void HallofFameDraw(Graphics g){
        /// Draw data from table

        g.setFont(new Font("Arial", Font.PLAIN, 42));
        g.setColor(Color.WHITE);

        /// L-am facut sa centreze in functie de 12 Caractere, scorul maxim e 3 caractere, id maxim e 1 caracter avem . si : 2 caractere,3 space chars
        /// deci in final numele paote avea 4caractere
        int startX = WindowVariables.WIDTH/2 -g.getFontMetrics(new Font("Arial", Font.PLAIN, 42)).stringWidth("Hall OF Fame")/2;
        int startY = WindowVariables.HEIGHT/4;


        int lineHeight = g.getFontMetrics().getHeight() + 4;
        for(int i = 0; i < hofEntries.size(); i++){
            HallofFameDataObject e = hofEntries.get(i);
            String text = String.format("%d. %s : %d", i+1, e.name, e.score);
            g.drawString(text, startX, startY + i * lineHeight);
        }


        HallofFameBack.ButtonRender(g);
    }

    public void menuDraw(Graphics g){
        play.ButtonRender(g);
        Load.ButtonRender(g);
        //Options.ButtonRender(g);
        quit.ButtonRender(g);
        HallofFameButton.ButtonRender(g);
    }

    public void optionDraw(Graphics g){
        OptionsBack.ButtonRender(g);
    }
    public void levelLoadDraw(Graphics g){
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
        LoadGame.ButtonRender(g);
        DeleteSave.ButtonRender(g);
        LoadBack.ButtonRender(g);
    }

    @Override
    public void levelUpdate(GameWindow wnd) {
        /// Cooldown pentru comenzi
        //System.out.println("Index:" + INTERACTIBLES_INDEX);
        menu_logic();
        if(MainMenu)
            switch (INTERACTIBLES_INDEX){
                case 1:
                    HallofFameButton.setButtonSelected(true);
                    play.setButtonSelected(false);
                    quit.setButtonSelected(false);
                    Load.setButtonSelected(false);
                    //Options.setButtonSelected(false);
                    break;
                case 3:
                    play.setButtonSelected(true);
                    quit.setButtonSelected(false);
                    Load.setButtonSelected(false);
                    //Options.setButtonSelected(false);
                    HallofFameButton.setButtonSelected(false);
                    break;
                case 2:
                    Load.setButtonSelected(true);
                    play.setButtonSelected(false);
                    quit.setButtonSelected(false);
                    //Options.setButtonSelected(false);
                    HallofFameButton.setButtonSelected(false);
                    break;
//                case 1:
//                    Options.setButtonSelected(true);
//                    play.setButtonSelected(false);
//                    Load.setButtonSelected(false);
//                    quit.setButtonSelected(false);
//                    HallofFameButton.setButtonSelected(false);
//                    break;
                case 0:
                    quit.setButtonSelected(true);
                    play.setButtonSelected(false);
                    Load.setButtonSelected(false);
                    //Options.setButtonSelected(false);
                    HallofFameButton.setButtonSelected(false);
                    break;
                default:
                    System.out.println("Something went wrong");
                    INTERACTIBLES_INDEX = 3;
                    break;
            }
        else if (OptionsMenu)
            switch (INTERACTIBLES_INDEX){
                case 0:
                    OptionsBack.setButtonSelected(true);
                    break;
                default:
                    System.out.println("Something went wrong");
                    INTERACTIBLES_INDEX = 0;
                    break;
            }

        else if (LevelLoadMenu) {
            switch (INTERACTIBLES_INDEX) {
                case 5:

                    if(saveLock && saveIndex ==1)
                        Save1.setButtonSelected(true);
                    else
                        Save1.setButtonSelected(false);

                    if(saveLock && saveIndex ==2)
                        Save2.setButtonSelected(true);
                    else
                        Save2.setButtonSelected(false);


                    Save3.setButtonSelected(true);
                    LoadGame.setButtonSelected(false);
                    DeleteSave.setButtonSelected(false);
                    LoadBack.setButtonSelected(false);
                    break;
                case 4:
                    Save2.setButtonSelected(true);
                    if(saveLock && saveIndex ==1)
                        Save1.setButtonSelected(true);
                    else
                        Save1.setButtonSelected(false);
                    if(saveLock && saveIndex ==3)
                        Save3.setButtonSelected(true);
                    else
                        Save3.setButtonSelected(false);

                    LoadGame.setButtonSelected(false);
                    DeleteSave.setButtonSelected(false);
                    LoadBack.setButtonSelected(false);
                    break;
                case 3:
                    if(saveLock && saveIndex ==3)
                        Save3.setButtonSelected(true);
                    else
                        Save3.setButtonSelected(false);
                    if(saveLock && saveIndex ==2)
                        Save2.setButtonSelected(true);
                    else
                        Save2.setButtonSelected(false);

                    Save1.setButtonSelected(true);

                    LoadGame.setButtonSelected(false);
                    DeleteSave.setButtonSelected(false);
                    LoadBack.setButtonSelected(false);
                    break;
                case 2:
                    if(saveLock && saveIndex ==1)
                        Save1.setButtonSelected(true);
                    else
                        Save1.setButtonSelected(false);
                    if(saveLock && saveIndex ==2)
                        Save2.setButtonSelected(true);
                    else
                        Save2.setButtonSelected(false);
                    if(saveLock && saveIndex ==3)
                        Save3.setButtonSelected(true);
                    else
                        Save3.setButtonSelected(false);

                    LoadGame.setButtonSelected(true);
                    DeleteSave.setButtonSelected(false);
                    LoadBack.setButtonSelected(false);
                    break;
                case 1:
                    DeleteSave.setButtonSelected(true);
                    if(saveLock && saveIndex ==1)
                        Save1.setButtonSelected(true);
                    else
                        Save1.setButtonSelected(false);
                    if(saveLock && saveIndex ==2)
                        Save2.setButtonSelected(true);
                    else
                        Save2.setButtonSelected(false);
                    if(saveLock && saveIndex ==3)
                        Save3.setButtonSelected(true);
                    else
                        Save3.setButtonSelected(false);
                    LoadGame.setButtonSelected(false);
                    LoadBack.setButtonSelected(false);
                    break;
                case 0:
                    LoadBack.setButtonSelected(true);
                    if(saveLock && saveIndex ==1)
                        Save1.setButtonSelected(true);
                    else
                        Save1.setButtonSelected(false);
                    if(saveLock && saveIndex ==2)
                        Save2.setButtonSelected(true);
                    else
                        Save2.setButtonSelected(false);
                    if(saveLock && saveIndex ==3)
                        Save3.setButtonSelected(true);
                    else
                        Save3.setButtonSelected(false);
                    LoadGame.setButtonSelected(false);
                    DeleteSave.setButtonSelected(false);
                    break;
                default:
                    System.out.println("Something went wrong");
                    INTERACTIBLES_INDEX = 5;
                    break;
            }
        }else if(HallofFame){
            switch (INTERACTIBLES_INDEX){
                case 0:
                    HallofFameBack.setButtonSelected(true);
                    break;
                default:
                    System.out.println("Something went wrong");
                    INTERACTIBLES_INDEX = 0;
                    break;
            }
        }
        else System.out.println("Something went wrong in Menu.java");

        /// Desenarea textului si design-ul animatiei de selected option
    }

    public void setJustLoaded(boolean justLoaded) { /// Setter pentru justLoaded
        this.justLoaded = justLoaded;
    }

    public void levelInit(){ /// Level init, se intampla o singura data la inceputul nivelului
        /// BLOCK TIMER
        try {
            Thread.sleep(65);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        }
        /// BLOCK DELAY

        String levelName = "Menu";
        System.out.println("Level "+ levelName +" Init");
        justLoaded = true;

        /// After testing, desired width is aprox 20% of WindowsVariables.WIDTH and for height about 6% of WindowsVariables.HEIGHT si distanta intre butoane de
        /// aproximativ 4% of WindowsVariables.HEIGHT
        /// Main Menu
        play = new Buttons("Play", (20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100),false,true);
        Load = new Buttons("Load",WindowVariables.HEIGHT/2 + (4*WindowVariables.HEIGHT/100) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        //Options = new Buttons("Options",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*27/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        quit = new Buttons("Quit",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*44/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        HallofFameButton = new Buttons("Score",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*27/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        /// Load
        /// Load Menu Buttons


        Save3 = new Buttons("Save File 3", WindowVariables.HEIGHT/2 - ((5*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot3 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((5*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);

        Save2 = new Buttons("Save File 2", WindowVariables.HEIGHT/2 - ((4*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot2 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((4*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);

        Save1 = new Buttons("Save File 1", WindowVariables.HEIGHT/2 - ((3*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        Slot1 = new Buttons("Empty Slot", WindowVariables.HEIGHT/2 - ((3*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);


        LoadGame = new Buttons("Load", WindowVariables.HEIGHT/2 - ((0*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        DeleteSave = new Buttons("Delete", WindowVariables.HEIGHT/2 - ((-1*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        LoadBack = new Buttons("Back", WindowVariables.HEIGHT/2 - ((-2*WindowVariables.HEIGHT/100)*6), (20*WindowVariables.WIDTH/100), (6*WindowVariables.HEIGHT/100), false, true);
        /// Options
       // OptionsBack = new Buttons("Back",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*44/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);;

        /// Hall of fame
        HallofFameBack = new Buttons("Back",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*97/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        //HallofFameBack = new Buttons("Back",WindowVariables.HEIGHT/2 + ((4*WindowVariables.HEIGHT/100)*44/10) ,(20*WindowVariables.WIDTH/100),(6*WindowVariables.HEIGHT/100) , false,true);
        System.out.println("Buttons initialized");
        hofEntries = GameVariables.HALLOFFAME_MODULE.getTopPlayers();///Ia lista cu playerii HOF
        MainMenu = true;

    }

    public Boolean playerState(){   /// Player-ul exista (for some reason) dar nu este afisat -> Getter
        return playerShouldAppear;
    }
    public int getPosx() {
        return posx;
    }
    public int getPosy(){
        return posy;
    }

}
