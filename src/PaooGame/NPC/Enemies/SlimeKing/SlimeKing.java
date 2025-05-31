package PaooGame.NPC.Enemies.SlimeKing;

import PaooGame.Game;
import PaooGame.GameVariables.GameVariables;
import PaooGame.Graphics.Assets;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.enemyTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SlimeKing implements Enemy {
    private final String name = "SlimeKing";
    private int Health;
    private int directionX = 0,directionY = 0;
    int x,y;
    public int sizeX=64,sizeY=64;
    public int enemyGlobalX,enemyGlobalY;
    public int RANGE = 400;
    public int ATKRANGE = sizeX ;
    int spawnX, spawnY;
    int mapX,mapY,mapDisplacementX,mapDisplacementY;
    int movementSpeed = 4;
    int distanceX,distanceY;
    boolean tookDamage = false;

    private float velX = 0, velY = 0;
    private final float GRAVITY = 0.5f;
    private final float JUMP_STRENGTH = -12f;
    private boolean onGround = true;
    private BufferedImage[] leftFrames  = Assets.slimeleft;
    private BufferedImage[] rightFrames = Assets.slimeright;
    private int animFrame   = 0;
    private int animCounter = 0;
    private int animSpeed   = 10;  // animation speed
    private int lastDir     = 1;    // 1 = spre dreapta, -1 = spre stanga




    SlimeKingStatus SlimeKingStatus = new SlimeKingStatus();
    SlimeKingObserver SlimeKingObserver = new SlimeKingObserver();
    Action action;



    public SlimeKing(){
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
        this.Health = 310;
    }

    public SlimeKing(int x, int y, int mapX, int mapY){
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
        this.Health = 310;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(int mapPositionX,int mapPositionY) {
        //Pozitia locului de spawn al inamicului fata de coltul din stanga al mapei
        this.spawnX = mapPositionX + mapDisplacementX;
        this.spawnY = mapPositionY + mapDisplacementY;

        //Pozitia actuala a inamicului in functie de spawn-ul player-ului si pozitia sa actuala dupa deplasare
        this.x = spawnX + distanceX;
        this.y = spawnY + distanceY;

        //Pozitia inamicului in functie de deplasament si distanta
        this.enemyGlobalX = mapDisplacementX + distanceX;
        this.enemyGlobalY = mapDisplacementY + distanceY;

        //Dupa calculul setului de valori pentru pozitii ale inamicului apelam actiunile
        SlimeKingObserver.check(this); //Verifica distantele dintre inamic si player urmand sa actioneze pe baza rezultatelor
        SlimeKingStatus.status(SlimeKingObserver);//Statusul primeste observatorul apoi
        action = SlimeKingStatus.getAction();//in functie de ceea ce se decide
        action.Action(this);//se actioneaza


        if (this.directionX != 0) {
            lastDir = this.directionX;            // actualizează ultima direcție
        }
        if (lastDir > 0 || lastDir < 0) {         // dacă avem direcție
            animCounter++;
            if (animCounter >= animSpeed) {
                animFrame = (animFrame + 1) %
                        (lastDir > 0 ? rightFrames.length : leftFrames.length);
                animCounter = 0;
            }
        } else {
            // când nu se mișcă pe orizontală
            animFrame = 0;
            animCounter = 0;
        }

        if(GameVariables.DEBUG)
            System.out.println("X: " + enemyGlobalX+ " Y: " + enemyGlobalY);
    }

    @Override
    public void render(Graphics g) {
        BufferedImage frame;
        if(Health>0)
        if(!tookDamage) {
            if (lastDir > 0) {
                frame = rightFrames[animFrame];
            } else if (lastDir < 0) {
                frame = leftFrames[animFrame];
            } else {
                frame = leftFrames[0];  // cadru „idle” spre stânga implicit
            }
            g.drawImage(frame, x, y,300,300, null);

        }        else {
            g.setColor(Color.RED);
            g.fillRect(x+150,y+150,sizeX,sizeY);
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
