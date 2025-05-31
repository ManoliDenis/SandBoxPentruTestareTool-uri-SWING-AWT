package PaooGame.Projectiles;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.PlayerInput.Player;
import PaooGame.Scenes.Level_Template;
import PaooGame.Tiles.Collision.CollisionChecker;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Game;
public class Bullet {
    private double x, y;
    private double dx, dy;
    private final double speed = 15.0;
    private BufferedImage img;
    private Rectangle collisionBox;
    private CollisionChecker collisionChecker;
    private boolean shouldDisappear = false;
    private  Enemy owner;
    private Player self;
    public Bullet(Enemy owner,double startX, double startY, double targetX, double targetY,
                  BufferedImage img, CollisionChecker collisionChecker) {

        this.owner = owner;
        this.x = startX;
        this.y = startY;
        this.img = img;
        this.collisionChecker = collisionChecker;


        double dist = Math.hypot(targetX - startX, targetY - startY);
        this.dx = (targetX - startX) / dist * speed;
        this.dy = (targetY - startY) / dist * speed;


        collisionBox = new Rectangle((int)x, (int)y, img.getWidth(), img.getHeight());
    }
    public Bullet(double startX, double startY, double targetX, double targetY,
                  BufferedImage img, CollisionChecker collisionChecker) {


        this.x = startX;
        this.y = startY;
        this.img = img;
        this.collisionChecker = collisionChecker;


        double dist = Math.hypot(targetX - startX, targetY - startY);
        this.dx = (targetX - startX) / dist * speed;
        this.dy = (targetY - startY) / dist * speed;


        collisionBox = new Rectangle((int)x, (int)y, img.getWidth(), img.getHeight());
    }
    public Bullet(Player self,double startX, double startY, double targetX, double targetY,
                  BufferedImage img, CollisionChecker collisionChecker) {

        this.self = self;
        this.x = startX;
        this.y = startY;
        this.img = img;
        this.collisionChecker = collisionChecker;


        double dist = Math.hypot(targetX - startX, targetY - startY);
        this.dx = (targetX - startX) / dist * speed;
        this.dy = (targetY - startY) / dist * speed;


        collisionBox = new Rectangle((int)x, (int)y, img.getWidth(), img.getHeight());
    }
    public Enemy getOwner()
    {
        return owner;
    }
    public Player getSelf()
    {
        return self;
    }
    public boolean update(int mapOffsetX, int mapOffsetY, int[][] map) {
        if(shouldDisappear)return false;
        // calculează poziția urmatoare
        double nextX = x + dx;
        double nextY = y + dy;

        // dimensiuni dreptunghi coliziune
        int w = img.getWidth();
        int h = img.getHeight();

        // verific coliziune cu wall-tile
        int worldX = (int) nextX;
        int worldY = (int) nextY;
        if (collisionChecker.checkTileCollision((int)nextX, (int)nextY, w, h, map)) {
            return false;  // distruge glonțul la impact
        }

        // daca nu a lovit wall, actualizez poziția
        x = nextX;
        y = nextY;
        collisionBox.setLocation((int) x, (int) y);
        if (owner instanceof Enemy) {
            Level_Template lvl = sceneManager.levelReturn();
            Rectangle playerBox = new Rectangle(
                    lvl.getGlobalPlayerX() + Player.solidArea.x,
                    lvl.getGlobalPlayerY() + Player.solidArea.y,
                    Player.solidArea.width,
                    Player.solidArea.height
            );
            if (collisionBox.intersects(playerBox)) {
                GameVariables.HP -= GameVariables.playerTAKEDAMAGE;
                return false;
            }
        }


        if (self instanceof Player) {
            Level_Template lvl = sceneManager.levelReturn();
            Rectangle playerBox = new Rectangle(
                    lvl.getGlobalPlayerX() + Player.solidArea.x,
                    lvl.getGlobalPlayerY() + Player.solidArea.y,
                    Player.solidArea.width,
                    Player.solidArea.height
            );
            if (collisionBox.intersects(playerBox)) {
                return false;
            }
        }

        if(GameVariables.DEBUG)
            System.out.println("coord actuale bullet: " + nextX + " " + nextY);
        return true;  // proiectil activ
    }
    public void draw(Graphics g, int mapOffsetX, int mapOffsetY) {
        if (shouldDisappear) return;
        g.drawImage(img, mapOffsetX + (int)x, mapOffsetY + (int)y, null);
        g.setColor(Color.YELLOW);
        g.drawRect(mapOffsetX + (int)x, mapOffsetY + (int)y, img.getWidth(), img.getHeight());
    }
    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }
    public void delete(){
        this.shouldDisappear = true;
    }
}