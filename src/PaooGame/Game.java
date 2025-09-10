package PaooGame;

import PaooGame.GameVariables.GameSceneIndex;
import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.LevelManager.levelManager;
import PaooGame.SceneManager.sceneManager;
import PaooGame.PlayerInput.PlayerInput;
import PaooGame.GameWindow.GameWindow;
import java.awt.image.BufferStrategy;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.TilesCollection;

import java.awt.*;


public class Game implements Runnable {
    private long oldTranslationTime = System.nanoTime();
    public static PlayerInput playerInput;
    private long currentTranslationTime;
    private Thread gameThread;
    private BufferStrategy bs;
    private boolean runState;
    private Graphics g;


    public Game(String title, int width, int height) {
        WindowVariables.gameWindow = new GameWindow(title, width, height);
        runState = false;
    }

    private void InitGame() {
        if (WindowVariables.gameWindow == null) {
            WindowVariables.gameWindow = new GameWindow("Abyss Covenant", WindowVariables.WIDTH, WindowVariables.HEIGHT);
            System.out.println("Game Constructor wasn't called!(Trying to force start...)");
        }


        WindowVariables.gameWindow.BuildGameWindow();
        Assets.Init();
        GameSceneIndex.GameSceneIndexInit();
        GameVariables.tilesCollection = new TilesCollection();
        playerInput = new PlayerInput();
        WindowVariables.gameWindow.GetCanvas().setFocusable(true);
        WindowVariables.gameWindow.GetCanvas().requestFocus();
        WindowVariables.gameWindow.GetCanvas().addKeyListener(playerInput);
        //sceneManager.sceneLoader(sceneManager.sceneName(sceneManager.SceneID.Menu));
        levelManager.ignition();
    }

    public void run() {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;
        final int framesPerSecond = 60;
        final double timeFrame = (double) 1000000000 / framesPerSecond;

        while (runState) {
            curentTime = System.nanoTime();
            if ((curentTime - oldTime) > timeFrame) {
                UpdateEverything();
                oldTime = curentTime;
            }
        }
    }

    public synchronized void StartGame() {
        if (!runState) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public synchronized void StopGame() {
        if (runState) {
            runState = false;
            try {
                gameThread.join();
            } catch (InterruptedException ex) {
                System.out.println("Game thread was interrupted");
            }
        }
    }

    private void UpdateEverything() {
        bs = WindowVariables.gameWindow.GetCanvas().getBufferStrategy();


        if (bs == null) {
            try {
                WindowVariables.gameWindow.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                System.out.println("Failed to create buffer strategy");
            }
        }


        g = bs.getDrawGraphics();
        g.clearRect(0, 0, WindowVariables.gameWindow.GetWndWidth(), WindowVariables.gameWindow.GetWndHeight());
        sceneManager.sceneUpdate(g);
        bs.show();
        g.dispose();
    }
}
