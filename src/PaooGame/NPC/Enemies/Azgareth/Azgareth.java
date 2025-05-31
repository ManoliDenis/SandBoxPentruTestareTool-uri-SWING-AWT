package PaooGame.NPC.Enemies.Azgareth;

import PaooGame.Game;
import PaooGame.GameVariables.GameVariables;
import PaooGame.Graphics.Assets;
import PaooGame.NPC.Action;
import PaooGame.NPC.ActionEnum.ActionEnum;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Projectiles.Bullet;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;
import PaooGame.Tiles.Collision.CollisionChecker;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.enemyTile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Azgareth implements Enemy {
    private final String name = "Azgareth";
    private int Health;
    private int directionX = 0,directionY = 0;
    int x,y;
    public int sizeX=64,sizeY=64;
    public int enemyGlobalX,enemyGlobalY;
    public int RANGE = 200;
    public int ATKRANGE = sizeX*20 ;
    int spawnX, spawnY;
    int mapX,mapY,mapDisplacementX,mapDisplacementY;
    int movementSpeed = 1;
    int distanceX,distanceY;
    boolean tookDamage = false;

    AzgarethStatus AzgarethStatus = new AzgarethStatus();
    AzgarethObserver AzgarethObserver = new AzgarethObserver();
    Action action;

    private long lastShootTime    = 0;
    private long lastTeleportTime = 0;
    private static final int SHOOT_INTERVAL_MS    = 100;   // or whatever fire‑rate you like
    private static final int TELEPORT_DELAY_MS    = 1000;//4000;
    private final Random rnd = new Random();

    private final Tile idle_sprite = new enemyTile(Assets.azgareth,35);///To be changed

    public Azgareth(){
        ///Coordonatele mapei initial
        this.mapX = 0;
        this.mapY = 0;
        ///Deplasamentul fata de pozitia mapei
        this.mapDisplacementX = 0;
        this.mapDisplacementY = 0;
        /// Spawn-ul este chiar suma dintre pozitia mapei si deplasament
        this.spawnX = mapX + mapDisplacementX;
        this.spawnY = mapY + mapDisplacementY;
        /// Initial coordonatele curente sunt cele de la spawn
        this.x = spawnX;
        this.y = spawnY;
        this.Health = 500;
    }

    public Azgareth(int x, int y, int mapX, int mapY){
        ///Coordonatele mapei initial
        this.mapX = mapX;
        this.mapY = mapY;
        ///Deplasamentul fata de pozitia mapei
        this.mapDisplacementX = x;
        this.mapDisplacementY = y;
        /// Spawn-ul este chiar suma dintre pozitia mapei si deplasament
        this.spawnX = mapX + mapDisplacementX;
        this.spawnY = mapY + mapDisplacementY;
        /// Initial coordonatele curente sunt cele de la spawn
        this.x = x;
        this.y = y;
        /// Teoretic asta ar trebui sa arate coordonatele cu un displacement fata de origine
        this.Health = 500;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(int mapPositionX, int mapPositionY) {

        long now = System.currentTimeMillis();

        // 1) reposition logic (unchanged)
        this.spawnX       = mapPositionX + mapDisplacementX;
        this.spawnY       = mapPositionY + mapDisplacementY;
        this.x            = spawnX + distanceX;
        this.y            = spawnY + distanceY;
        this.enemyGlobalX = mapDisplacementX + distanceX;
        this.enemyGlobalY = mapDisplacementY + distanceY;

        // 2) decide state
        AzgarethObserver.check(this);
        AzgarethStatus.status(AzgarethObserver);
        ActionEnum state = AzgarethObserver.getAction();

        // 1) continuous shooting while in ATTACK, at a fast rate
        if (state == ActionEnum.ATTACK && now - lastShootTime >= SHOOT_INTERVAL_MS) {
            shootAtPlayer();
            lastShootTime = now;
        }

        // 2) separate teleport every 4 s (doesn't block shooting)
        if (state != ActionEnum.IsDead &&  now - lastTeleportTime >= TELEPORT_DELAY_MS) {
            doTeleport();
            lastTeleportTime = now;
        }



        // 3) still execute your normal behavior (chase/patrol/etc)
        Action behavior = AzgarethStatus.getAction();
        behavior.Action(this);


        if (GameVariables.DEBUG) {
            System.out.println("Azgareth @ [" + enemyGlobalX + "," + enemyGlobalY + "]");
        }
    }
    private void shootAtPlayer() {
        Level_Template lvl = sceneManager.levelReturn();
        int px = lvl.getGlobalPlayerX();
        int py = lvl.getGlobalPlayerY();

        int sx = getXRelativetoOrigin();
        int sy = getYRelativetoOrigin();

        double dx = px - sx, dy = py - sy;
        double len = Math.sqrt(dx*dx + dy*dy);
        dx /= len;  dy /= len;


        BufferedImage bulletImg = Assets.azgarethBullet;



        Bullet b = new Bullet(this,sx, sy,px, py,bulletImg,Game.collisionChecker);

        lvl.getBullets().add(b);
    }

    private void doTeleport() {
        // ±3 tiles
        int tileSize = 64;
        int maxTiles = 3;
        int offX = (rnd.nextInt(maxTiles * 2 + 1) - maxTiles) * tileSize;
        int offY = (rnd.nextInt(maxTiles * 2 + 1) - maxTiles) * tileSize;

        // just change the “distance” from spawn
        this.distanceX = offX;
        this.distanceY = offY;
    }
    @Override
    public void render(Graphics g) {
        if(Health>0)
            if(!tookDamage)
                idle_sprite.Draw(g,x,y,128,128);
            else {
                g.setColor(Color.RED);
                g.fillRect(x+40,y+40,sizeX,sizeY);
                tookDamage = false;
            }
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setDirectionX(int x) {
        this.directionX = x;
    }

    @Override
    public void setDirectionY(int y) {
        this.directionY = y;
    }

    @Override
    public void updateHealth(int value) {
        this.Health += value;
    }

    @Override
    public void enemyDebug() {
        System.out.println(name+ " X:" + x+"  Y:"+y+" ");
    }

    @Override
    public int getSpawnX() {
        return this.spawnX;
    }

    @Override
    public int getSpawnY() {
        return this.spawnY;
    }

    @Override
    public int getCurrentX() {
        return x;
    }

    @Override
    public int getCurrentY() {
        return y;
    }

    @Override
    public int getSpeed() {
        return movementSpeed;
    }

    @Override
    public int getDirectionX() {
        return directionX;
    }

    @Override
    public int getDirectionY() {
        return directionY;
    }

    @Override
    public int getDistanceX() {
        return this.distanceX;
    }

    @Override
    public int getDistanceY() {
        return this.distanceY;
    }

    @Override
    public void moveEnemy(int movementSpeed, int directionX, int directionY) {
        ///System.out.println(movementSpeed + " " + directionX + " " + directionY);
        this.distanceX += movementSpeed * directionX;
        this.distanceY += movementSpeed * directionY;
    }

    @Override
    public int getXRelativetoOrigin() {
        return enemyGlobalX+(sizeX/2);
    }

    @Override
    public int getYRelativetoOrigin() {
        return enemyGlobalY+(sizeY/2);
    }

    @Override
    public int getMapX() {
        return mapX;
    }

    @Override
    public int getMapY() {
        return mapY;
    }

    @Override
    public int getRANGE() {
        return RANGE;
    }

    @Override
    public int getATKRANGE() {
        return ATKRANGE;
    }
    @Override
    public void entitytakeDamage(int damage) {
        this.Health -= GameVariables.playerATKDAMAGE;
    }

    @Override
    public int getHealth() {
        return this.Health;
    }

    @Override
    public void setTookDamage(boolean tookDamage) {
        this.tookDamage = tookDamage;
    }

    @Override
    public void setSpeed(int speed) {
        this.movementSpeed = speed;
    }


}
