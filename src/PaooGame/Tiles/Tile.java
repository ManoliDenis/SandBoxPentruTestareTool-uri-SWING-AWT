package PaooGame.Tiles;

import java.awt.*;
import Engine.Graphics.AssetsManagement.Assets;

public class Tile {
    private final Assets asset;
    private final int id;
    private final Boolean isSolid;
    private final Boolean isInteractive;
    private Boolean isTriggered;

    //This constructor's scope is to set the status for the tile
    public Tile(Assets asset, int id, Boolean isSolid, Boolean isTriggered, Boolean isInteractive) {
        this.asset = asset;
        this.id = id;
        this.isSolid = isSolid;
        this.isTriggered = isTriggered;
        this.isInteractive = isInteractive;
    }
    //This function's scope is to draw tile starting from position defined by x,y and ending at width and height
    public void Draw(Graphics g, int x, int y,int width,int height) {
        g.drawImage(asset.getTexture(), x, y, width, height, null);
    }

    public Boolean IsSolid() {
        return isSolid;
    }
    public Boolean IsInteractive() {return isInteractive;}
    public int GetId() {
        return id;
    }


    /// Both functions, GetIsTriggered() and SetIsTriggered(), can be use in order to build a logic
    /// around certain areas in the game
    //This function's scope is to return isTriggered status
    public Boolean GetIsTriggered() {return this.isTriggered;}

    //This function's scope is to set isTriggered status
    public void SetIsTriggered(Boolean isTriggered) {
        if(this.isInteractive)
            this.isTriggered = isTriggered;
        else System.out.println("Not interactive");
    }
}

