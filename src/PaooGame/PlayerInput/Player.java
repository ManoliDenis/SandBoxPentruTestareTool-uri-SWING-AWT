package PaooGame.PlayerInput;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Tile {

    public static String direction = "down";

    public static boolean isMoving = false;


    private int frameIndex = 0;
    private long lastFrameTime = System.currentTimeMillis();
    public static Rectangle solidArea;
    BufferedImage currentImage;
    public static boolean isShooting = false;
    private int shootFrameIndex = 0;
    private long lastShootFrameTime = System.currentTimeMillis();
    private final int shootFrameDelay = 150; // ms între cadre

    public Player(int id) {

        super(Assets.down1, id);
        solidArea = new Rectangle(20, 20, Tile.TILE_WIDTH - 40, Tile.TILE_HEIGHT - 25); // initializez collision boxul
    }



    @Override
    public void Draw(Graphics g, int x, int y) {
        BufferedImage[] frames;
        BufferedImage currentImage;
        long now = System.currentTimeMillis();

        if (isShooting) {
            // 1. Aleg array-ul de shoot
            switch (direction) {
                case "up":    frames = Assets.upShoot;    break;
                case "down":  frames = Assets.downShoot;  break;
                case "left":  frames = Assets.leftShoot;  break;
                case "right": frames = Assets.rightShoot; break;
                default:      frames = Assets.downShoot;  break;
            }

            // 2. Avansez shootFrameIndex şi opresc animaţia
            if (now - lastShootFrameTime > shootFrameDelay) {
                shootFrameIndex++;
                lastShootFrameTime = now;
                if (shootFrameIndex >= frames.length) {
                    shootFrameIndex = 0;
                    isShooting = false;
                }
            }

            // 3. Desenez cadrul curent de shoot
            currentImage = frames[shootFrameIndex];

        } else {
            // 4. Aleg array-ul de mers/idle
            switch (direction) {
                case "up":    frames = Assets.upFrames;    break;
                case "down":  frames = Assets.downFrames;  break;
                case "left":  frames = Assets.leftFrames;  break;
                case "right": frames = Assets.rightFrames; break;
                default:      frames = Assets.downFrames;  break;
            }

            // 5. Avansez frameIndex pentru mers
            // delay în milisecunde între cadre
            int frameDelay = 200;
            if (isMoving && now - lastFrameTime > frameDelay) {
                frameIndex = (frameIndex + 1) % frames.length;
                lastFrameTime = now;
            } else if (!isMoving) {
                frameIndex = 0;
            }

            // 6. Desenez cadrul curent de mers/idle
            currentImage = frames[frameIndex];
        }

        // 7. Desenez sprite-ul final
        g.drawImage(currentImage, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
    }

    public Rectangle getCollisionBox() {
        return solidArea;
    }
}
