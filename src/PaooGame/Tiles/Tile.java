package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Graphics.*;
import PaooGame.PlayerInput.Player;

public class Tile {
    private static final int NO_TILES = 100;
    public static Tile[] tiles = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/


    public static Tile playerTile = new Player(10);
    public static Tile[][] map_tiles = new Tile[10][10];
    public static Tile purple_floor_tile =  new FloorTile(Assets.purple_floor1,0);

    public static Tile upWall_tile =  new WallTile(Assets.upWall,1);
    public static Tile downWall_tile =  new WallTile(Assets.downWall,2);
    public static Tile leftWall_tile =  new WallTile(Assets.leftWall,3);
    public static Tile rightWall_tile =  new WallTile(Assets.rightWall,4);

    public static Tile upRCWall_tile =  new WallTile(Assets.upRCWall,5);
    public static Tile downRCWall_tile =  new WallTile(Assets.downRCWall,6);
    public static Tile upLCWall_tile =  new WallTile(Assets.upLCWall,7);
    public static Tile downLCWall_tile =  new WallTile(Assets.downLCWall,11);
    public static Tile Ldoor_tile = new doorTile(Assets.leftDoor,9);
    public static Tile Rdoor_tile = new doorTile(Assets.rightDoor,10);
    public static Tile downDoor_tile = new doorTile(Assets.downDoor,12);
    public static Tile stairs_tile = new doorTile(Assets.stairs,14);
    public static Tile lvl1background_tile = new FloorTile(Assets.lvl1background,8);

    public static Tile snow = new FloorTile(Assets.snow,15);
    public static Tile water = new WallTile(Assets.water,16);
    public static Tile ice = new FloorTile(Assets.ice,17);
    public static Tile cliff = new WallTile(Assets.cliff,18);
    public static Tile downCliff = new doorTile(Assets.downCliff,19);
    public static Tile upCliff = new WallTile(Assets.upCliff,20);
    public static Tile hFence = new WallTile(Assets.hFence,21);
    public static Tile vFence = new WallTile(Assets.vFence,22);

    public static Tile obsidian = new WallTile(Assets.obsidian,23);
    public static Tile lava = new FloorTile(Assets.lava,24);
    public static Tile redGround = new FloorTile(Assets.redGround,25);
    public static Tile redtxtGround = new FloorTile(Assets.redtxtGround,26);
    public static Tile redupWall = new WallTile(Assets.redupWall,27);
    public static Tile reddownWall = new WallTile(Assets.reddownWall,28);
    public static Tile redleftWall = new WallTile(Assets.redleftWall,29);
    public static Tile redrightWall = new WallTile(Assets.redrightWall,30);
    public static Tile redWall = new WallTile(Assets.redWall,31);
    public static Tile side3door = new doorTile(Assets.side3door,32);
    public static Tile front3door = new doorTile(Assets.front3door,33);
    public static Tile bossroomGround = new FloorTile(Assets.bossroomGround,34);

    public static final int  TILE_WIDTH=  64;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                       /*!< Inaltimea unei dale.*/
    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/



    public Tile(BufferedImage image, int id) {
        img = image;
        this.id = id;
        tiles[id] = this;
    }

    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null); ///se ocupa cu resize la img
    }

    public void Draw(Graphics g, int x, int y,int width,int height) {
        g.drawImage(img, x, y, width, height, null); ///se ocupa cu resize la img
    }

    public boolean IsSolid() {
        return false;
    }
    public int GetId() {
        return id;
    }


}

