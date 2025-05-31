package PaooGame;

import PaooGame.GameVariables.WindowVariables;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;

import PaooGame.PlayerInput.Player;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Tiles.Collision.CollisionChecker;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import PaooGame.PlayerInput.PlayerInput;

public class Game implements Runnable {
    /// Needed Vars
    public static GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;


    private long oldTranslationTime = System.nanoTime();
    private long currentTranslationTime;
    public static PlayerInput playerInput;
    public static CollisionChecker collisionChecker;


    private Graphics g;
    /// Update manages the calculation of indexes for positions
    private void Update() {
        //verific separat coliziunile pe axe ca sa nu se blocheze miscarea pe diagonala cand ating un zid
        sceneManager.sceneStrategyLogic(wnd);
    }

    public Game(String title, int width, int height) {
        wnd = new GameWindow(title, width, height);
        runState = false;
    }
    private void registerMouseListener() {
        wnd.GetCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();
                int centerX = wnd.GetWndWidth()  / 2;
                int centerY = wnd.GetWndHeight() / 2;

                double startX = -sceneManager.getPosX() + centerX;
                double startY = -sceneManager.getPosY() + centerY;

                double targetX = startX + (mx - centerX);
                double targetY = startY + (my - centerY);

                // genereaza bullet
                (sceneManager.current_level).spawnBullet(startX, startY, targetX, targetY);

                // porneste animatia de shoot
                Player.isShooting = true;
            }
        });
    }
    /// Init game sets: the window,loads assets,player input,canvas focus, creates a collision checker and loads the Menu
    /// Init game sets: the window,loads assets,player input,canvas focus, creates a collision checker and loads the Menu
    private void InitGame() {
        if (wnd == null) {
            wnd = new GameWindow("Abyss Covenant", WindowVariables.WIDTH, WindowVariables.HEIGHT);
            System.out.println("Game Constructor wasn't called!(Trying to force start...)");
        }
        wnd.BuildGameWindow();
        Assets.Init(); ///Needs to be changed in the future(imports too many things for no reason)

        playerInput = new PlayerInput(); ///new player input
        registerMouseListener();
        wnd.GetCanvas().setFocusable(true);///Canvas is focusable
        wnd.GetCanvas().requestFocus(); ///Set focus on canvas
        wnd.GetCanvas().addKeyListener(playerInput);///Adding the keylistener
        sceneManager.levelLoader(sceneManager.levels.Level_3); ///Sets the first level to be rendered which in this case is the Menu
    }
    /// Inits everything it needs, calculates the fps, and based on runstate it updates the logic and graphics
    public void run() {
        InitGame();///Prepares everything
        long oldTime = System.nanoTime(); ///old time for the difference
        long curentTime;///current time
        final int framesPerSecond = 60; ///Locked on 60 FPS
        final double timeFrame = 1000000000 / framesPerSecond; ///the time/frame

        while (runState) { /// While should run
            curentTime = System.nanoTime(); ///gets the current time
            if ((curentTime - oldTime) > timeFrame) { /// checks if the difference is > than time frame
                Update(); ///Updates logic
                Draw(); ///Draws
                oldTime = curentTime; ///Sets the oldtime
            }
        }
    }
    /// Like a car if it aint on we turn it on a single thread
    public synchronized void StartGame() {
        if (!runState) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    /// Like a car, if it's on turn it off
    public synchronized void StopGame() {
        if (runState) {
            runState = false;
            try {
                gameThread.join();///Comes back to the main thread
            } catch (InterruptedException ex) {
                System.out.println("Game thread was interrupted");
                ///ex.printStackTrace();
            }
        }
    }
    /// Sets what-how-where to draw
    private void Draw() {
        bs = wnd.GetCanvas().getBufferStrategy();
        if (bs == null) {
            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                System.out.println("Failed to create buffer strategy");
                ///e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        sceneManager.sceneStrategyDraw(g,wnd);///Manages what to draw

        bs.show();
        g.dispose();
    }
}
