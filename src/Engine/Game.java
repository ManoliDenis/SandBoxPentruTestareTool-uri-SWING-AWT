package Engine;

import java.awt.image.BufferStrategy;

import Engine.GameVariables.GameVariables;
import Engine.GameVariables.WindowVariables;
import Engine.GameWindow.GameWindow;
import Engine.Graphics.AssetsManagement.AssetsCollection;
import Engine.PlayerInput.PlayerInput;
import Engine.SceneManager.sceneManager;
import Engine.Tiles.TilesCollection;

import java.awt.*;


public class Game implements Runnable {
    public static PlayerInput playerInput;
    private Thread gameThread;
    private BufferStrategy bs;
    private boolean runState;
    private Graphics g;

    //The Way the game was meant to be started from within a main class
    public Game(String title, int width, int height) {
        WindowVariables.gameWindow = new GameWindow(title, width, height);
        runState = false;
    }

    //Adds an extra check to see if the variables set has been created successfully
    private void InitGame() {
        if (WindowVariables.gameWindow == null) {
            WindowVariables.gameWindow = new GameWindow("Abyss Covenant", WindowVariables.WIDTH, WindowVariables.HEIGHT);
            System.out.println("Game Constructor wasn't called!(Trying to force start...)");
        }


        WindowVariables.gameWindow.BuildGameWindow();
        AssetsCollection.Init();
        GameVariables.tilesCollection = new TilesCollection();
        playerInput = new PlayerInput();
        WindowVariables.gameWindow.GetCanvas().setFocusable(true);
        WindowVariables.gameWindow.GetCanvas().requestFocus();
        WindowVariables.gameWindow.GetCanvas().addKeyListener(playerInput);
        sceneManager.sceneLoader(sceneManager.sceneName(sceneManager.SceneID.Menu));
    }

    public void run() {
        InitGame();
        long oldTime = System.nanoTime();
        long curentTime;
        final int framesPerSecond = 144;
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

    public void render()
    {

        Graphics2D g2d = (Graphics2D) this.g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        double zoomFactor = Math.min(WindowVariables.WIDTH / 640.0, WindowVariables.HEIGHT / 360.0);
        g2d.scale(zoomFactor, zoomFactor);

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
        render();
        sceneManager.sceneUpdate(g);
        bs.show();
        g.dispose();
    }
}
