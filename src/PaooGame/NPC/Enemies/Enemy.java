package PaooGame.NPC.Enemies;

import PaooGame.Tiles.Tile;

import java.awt.*;

public interface Enemy {
    public String getName();
    public void update(int posx,int posy); ///Poate sa gandeasca, in update
    public void render(Graphics g); ///Poate sa animeze
    public void setPosition(int x, int y); ///Poate sa miste
    public void setDirectionX(int x);
    public void setDirectionY(int y);
    public void updateHealth(int value);///Poate lua damage
    public void enemyDebug();
    public int getSpawnX();
    public int getSpawnY();
    public int getCurrentX();
    public int getCurrentY();
    public int getSpeed();
    public int getDirectionX();
    public int getDirectionY();
    public int getDistanceX();
    public int getDistanceY();
    public void moveEnemy(int movementSpeed,int directionX,int directionY);
    public int getXRelativetoOrigin();
    public int getYRelativetoOrigin();
    public int getMapX();
    public int getMapY();
    public int getRANGE();
    public int getATKRANGE();
    public void entitytakeDamage(int damage);
    public int getHealth();
    public void setTookDamage(boolean tookDamage);
    public void setSpeed(int speed);


}
