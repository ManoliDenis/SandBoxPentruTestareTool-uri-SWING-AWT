package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class Assets {


    public static BufferedImage up1, down1, left1, right1,up2, down2, left2, right2,up3, down3, left3, right3,up4,down4,left4,right4;
    public static BufferedImage[] upFrames,downFrames,leftFrames,rightFrames,upShoot,downShoot,leftShoot,rightShoot;

    public static BufferedImage purple_floor1,lvl1background,stairs;
    public static BufferedImage upWall,downWall,leftWall,rightWall,upLCWall,downLCWall,upRCWall,downRCWall;
    public static BufferedImage leftDoor,rightDoor,downDoor;

    public static BufferedImage fastRasccal,tankRascal,avgRascal,whiteDemon,ghost,clawDemon,redswordDemon,crazyEye,wingedDemon;
    public static BufferedImage azgareth,azgarethBullet;
    public static BufferedImage vFence,hFence,ice,snow,water,cliff,downCliff,upCliff;
    public static BufferedImage obsidian,lava,redGround,redupWall,reddownWall,redleftWall,redrightWall,redWall,redtxtGround,side3door,front3door,bossroomGround;
    public static BufferedImage bullet;
    public static BufferedImage upAr1,upAr2,downAr1,downAr2,leftAr1,leftAr2,rightAr1,rightAr2;

    public static BufferedImage slimeLeft,slimeRight,slimeLeftjump,slimeRightjump;
    public static BufferedImage [] slimeleft,slimeright;
    public static BufferedImage idleDeath1r,idleDeath2r,idleDeath3r,idleDeathl,attackwindupDeathr,attackDeath1r,deadDeath1,deadDeath2,deadDeath3,deadDeath4,attackwindupDeathl,attackDeath1l;
    public static BufferedImage[] dIdler,dAttackr,dDead,dAttackl,dIdlel;
    public static BufferedImage kamikaze;
    public static void Init() {

        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/CHRSpriteSheet16.png"));
        SpriteSheet lvl1mapSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/lvl1map.png"));
        SpriteSheet Enemies = new SpriteSheet(ImageLoader.LoadImage("/textures/Basic Demons 1x-sheet.png"));
        SpriteSheet SlimeKing = new SpriteSheet(ImageLoader.LoadImage("/textures/slime_king.png"));
        SpriteSheet idleDeath = new SpriteSheet(ImageLoader.LoadImage("/textures/idle.png"));
        SpriteSheet lvl2mapSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/lvl2map.png"));
        SpriteSheet lvl3mapSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/lvl3map.png"));
        SpriteSheet attackDeath = new SpriteSheet(ImageLoader.LoadImage("/textures/attacking.png"));
        SpriteSheet deadDeath = new SpriteSheet(ImageLoader.LoadImage("/textures/death.png"));




        azgarethBullet = ImageLoader.LoadImage("/textures/azgarethbullet.png");

        idleDeath1r = idleDeath.crop(0,0,6*16,6*16);
        idleDeath2r = idleDeath.crop(1,0,6*16,6*16);
        idleDeath3r = idleDeath.crop(2,0,6*16,6*16);
        attackDeath1r = attackDeath.crop(1,0,6*16,6*16);
        attackwindupDeathr = attackDeath.crop(2,0,6*16,6*16);
        attackDeath1l = attackDeath.crop(2,2,6*16,6*16);
        attackwindupDeathl = attackDeath.crop(1,2,6*16,6*16);
        deadDeath1 = deadDeath.crop(0,0,6*16,6*16);
        deadDeath2 = deadDeath.crop(1,0,6*16,6*16);
        deadDeath3 = deadDeath.crop(2,0,6*16,6*16);
        deadDeath4 = deadDeath.crop(9,0,6*16,6*16);
        idleDeathl = idleDeath.crop(4,0,6*16,6*16);
        dIdlel = new BufferedImage[] { idleDeathl };
        dDead = new BufferedImage[]  {deadDeath1,deadDeath2,deadDeath3,deadDeath4};
        dIdler = new BufferedImage[]  {idleDeath1r,idleDeath2r,idleDeath3r};
        dAttackr = new BufferedImage[] {attackwindupDeathr,attackDeath1r,attackwindupDeathr};
        dAttackl = new BufferedImage[] {attackwindupDeathl,attackDeath1l,attackwindupDeathl};


        slimeLeft = SlimeKing.crop(0,1,3*16,4*16);
        slimeRight = SlimeKing.crop(0,0,3*16,4*16);
        slimeLeftjump = SlimeKing.crop(2,3,3*16,4*16);
        slimeRightjump = SlimeKing.crop(2,2,3*16,4*16);

        slimeleft = new BufferedImage [] {slimeLeft, slimeLeftjump, slimeLeft};
        slimeright = new BufferedImage [] {slimeRight,slimeRightjump,slimeRight};


        azgareth = Enemies.crop(1,0);
        kamikaze = Enemies.crop(0,1);


        bullet = ImageLoader.LoadImage("/textures/Bullet.png");
        downAr1 = playerSheet.crop(4,0);
        downAr2 = playerSheet.crop(5,0);
        upAr1 = playerSheet.crop(4,1);
        upAr2 = playerSheet.crop(5,1);
        rightAr1 = playerSheet.crop(4,2);
        rightAr2 = playerSheet.crop(5,2);
        leftAr1 = playerSheet.crop(4,3);
        leftAr2 = playerSheet.crop(5,3);

        upShoot = new BufferedImage[]{upAr1,upAr2};
        downShoot = new BufferedImage[]{downAr1,downAr2};
        leftShoot = new BufferedImage[]{leftAr1,leftAr2};
        rightShoot = new BufferedImage[]{rightAr1,rightAr2};

        bossroomGround = lvl3mapSheet.crop(1,2);
        side3door = lvl3mapSheet.crop(2,2);
        front3door = lvl3mapSheet.crop(3,2);
        obsidian = lvl3mapSheet.crop(0,2);
        lava = lvl3mapSheet.crop(3,0);
        redGround = lvl3mapSheet.crop(1,0);
        redtxtGround = lvl3mapSheet.crop(2,0);
        redupWall = lvl3mapSheet.crop(0,1);
        reddownWall = lvl3mapSheet.crop(1,1);
        redleftWall = lvl3mapSheet.crop(3,1);
        redrightWall = lvl3mapSheet.crop(2,1);
        redWall = lvl3mapSheet.crop(0,0);


        vFence = lvl2mapSheet.crop(0,0);
        hFence = lvl2mapSheet.crop(1,0);
        snow = lvl2mapSheet.crop(2,0);
        ice = lvl2mapSheet.crop(3,0);
        cliff = lvl2mapSheet.crop(0,1);
        upCliff = lvl2mapSheet.crop(1,1);
        downCliff = lvl2mapSheet.crop(2,1);
        water = lvl2mapSheet.crop(3,1);

        redswordDemon = Enemies.crop(0,0);
        crazyEye = Enemies.crop(3,0);
        wingedDemon = Enemies.crop(2,0);

        fastRasccal = Enemies.crop(0,1);
        avgRascal = Enemies.crop(2,1);
        tankRascal = Enemies.crop(3,1);

        whiteDemon = Enemies.crop(1,2);
        clawDemon = Enemies.crop(3,2);
        ghost = Enemies.crop(4,2);




        upWall = lvl1mapSheet.crop(0,0);
        downWall = lvl1mapSheet.crop(0,3);
        rightWall = lvl1mapSheet.crop(0,2);
        leftWall = lvl1mapSheet.crop(0,1);

        upLCWall = lvl1mapSheet.crop(1,2);
        downLCWall = lvl1mapSheet.crop(2,3);
        upRCWall = lvl1mapSheet.crop(1,3);
        downRCWall = lvl1mapSheet.crop(3,3);

        purple_floor1 = lvl1mapSheet.crop(3,0);
        lvl1background = lvl1mapSheet.crop(2,0);
        stairs = lvl1mapSheet.crop(3,2);

        downDoor = lvl1mapSheet.crop(3,1);
        leftDoor = lvl1mapSheet.crop(2,2);
        rightDoor = lvl1mapSheet.crop(2,1);


        up1    = playerSheet.crop(0, 1); // sus
        down1  = playerSheet.crop(0, 0); //  jos
        left1  = playerSheet.crop(0, 3); // stânga
        right1 = playerSheet.crop(0, 2); // dreapta

        up2    = playerSheet.crop(1, 1);
        down2  = playerSheet.crop(1, 0);
        left2  = playerSheet.crop(1, 3);
        right2 = playerSheet.crop(1, 2);

        up3    = playerSheet.crop(2, 1);
        down3  = playerSheet.crop(2, 0);
        left3  = playerSheet.crop(2, 3);
        right3 = playerSheet.crop(2, 2);

        up4    = playerSheet.crop(3, 1);
        down4  = playerSheet.crop(3, 0);
        left4  = playerSheet.crop(3, 3);
        right4 = playerSheet.crop(3, 2);

        upFrames    = new BufferedImage[] { up1, up2, up3 ,up4 };
        downFrames  = new BufferedImage[] { down1, down2, down3,down4 };
        leftFrames  = new BufferedImage[] { left1, left2, left3 ,left4};
        rightFrames = new BufferedImage[] { right1, right2, right3,right4 };



    }
}
