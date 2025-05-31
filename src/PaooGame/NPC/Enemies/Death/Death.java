package PaooGame.NPC.Enemies.Death;

import PaooGame.Game;
import PaooGame.GameVariables.GameVariables;
import PaooGame.Graphics.Assets;
import PaooGame.NPC.Action;
import PaooGame.NPC.ActionEnum.ActionEnum;
import PaooGame.NPC.Enemies.Death.Actions.Attack;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.enemyTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Death implements Enemy {
    private final String name = "Death";
    private int Health;
    private int directionX = 0,directionY = 0;
    int x,y;
    public int sizeX=64,sizeY=64;
    public int enemyGlobalX,enemyGlobalY;
    public int RANGE = 700;
    public int ATKRANGE = 32 ;
    int spawnX, spawnY;
    int mapX,mapY,mapDisplacementX,mapDisplacementY;
    int movementSpeed = 6;
    int distanceX,distanceY;
    boolean tookDamage = false;

    DeathStatus DeathStatus = new DeathStatus();
    DeathObserver DeathObserver = new DeathObserver();
    Action action;

    private BufferedImage[] idleFramesright    = Assets.dIdler;
    private BufferedImage[] attackFramesright  = Assets.dAttackr;
    private BufferedImage[] deadFrames    = Assets.dDead;
    private BufferedImage[] attackFramesleft  = Assets.dAttackl;
    private BufferedImage[] idleFramesLeft   = Assets.dIdlel;
    private int animFrame   = 0;
    private int animCounter = 0;
    private int animSpeed   = 12;    // ajustează viteza animației

    private int lastDirX = 1;  // -1 pt stanga, 1 pt dreapta
    private ActionEnum currentState = ActionEnum.Patrol;


    public Death(){
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
        this.Health = 100;
    }

    public Death(int x, int y, int mapX, int mapY){
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
        this.Health = 100;
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
        DeathObserver.check(this);
        DeathStatus.status(DeathObserver);
        action = DeathStatus.getAction();
        action.Action(this);


        if (this.directionX != 0) {
            lastDirX = this.directionX;
        }


        ActionEnum newState = DeathObserver.getAction();
        if (newState != currentState) {
            animFrame = 0;
            animCounter = 0;
            currentState = newState;
        }

        if (currentState == ActionEnum.IsDead) {
            // animăm dead indiferent de directionX
            animCounter++;
            if (animCounter >= animSpeed) {
                // opreşte-te la ultimul cadru
                if (animFrame < deadFrames.length - 1) {
                    animFrame++;
                }
                animCounter = 0;
            }
        }
        else if (this.directionX != 0) {
            animCounter++;
            if (animCounter >= animSpeed) {
                BufferedImage[] frames = switch (currentState) {
                    case ATTACK -> (lastDirX < 0 ? attackFramesleft : attackFramesright);
                    case IsDead -> deadFrames;
                    default     -> (lastDirX < 0 ? idleFramesLeft   : idleFramesright);
                };
                animFrame = (animFrame + 1) % frames.length;
                animCounter = 0;
            }
        } else {

            animFrame = 0;
            animCounter = 0;
        }

        if (GameVariables.DEBUG) {
            System.out.println("Death X: " + enemyGlobalX + " Y: " + enemyGlobalY);
        }
    }

    @Override
    public void render(Graphics g) {
        BufferedImage frame;

        if (!tookDamage) {
            // Aici folosim lastDirX în loc de lastDir (–1 stânga, +1 dreapta)
            if (lastDirX > 0) {
                // Spre dreapta: folosim attack sau idle în funcție de stare
                if (currentState == ActionEnum.ATTACK) {
                    frame = attackFramesright[animFrame];
                } else if (currentState == ActionEnum.IsDead) {
                    frame = deadFrames[animFrame];
                } else {
                    frame = idleFramesright[animFrame];
                }
            } else if (lastDirX < 0) {

                if (currentState == ActionEnum.ATTACK) {
                    frame = attackFramesleft[animFrame];
                } else if (currentState == ActionEnum.IsDead) {
                    frame = deadFrames[animFrame];
                } else {
                    frame = idleFramesLeft[animFrame];
                }
            } else {

                frame = idleFramesLeft[0];
            }

            g.drawImage(frame, x, y, 2*200, 2*200, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x + 100, y + 100, 200, 200);
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
