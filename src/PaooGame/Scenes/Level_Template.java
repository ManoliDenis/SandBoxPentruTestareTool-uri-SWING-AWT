package PaooGame.Scenes;

import PaooGame.GameWindow.GameWindow;
import PaooGame.PlayerInput.Player;
import PaooGame.Projectiles.Bullet;

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

///Interfata pentru scene
public interface Level_Template {
    public void levelUpdate(GameWindow wnd); ///Exact ca la update, se repeta pe tot parcursul nivelului curent
    public void levelInit();
    public Boolean playerState();
    public int[][] getMap();
    public void levelDraw(Graphics g, GameWindow wnd);
    public int getGlobalPlayerX();
    public int getGlobalPlayerY();
    public void setMapTile(int x, int y, int tile);
    public int getlevelindex();
    void spawnBullet(double startX, double startY, double targetX, double targetY);
    ConcurrentLinkedQueue<Bullet> getBullets();





}
