package Engine.Graphics.AssetsManagement;

import java.awt.image.BufferedImage;
import javax.sound.sampled.Clip;

import Engine.Graphics.AssetsManagement.AssetTypes.assetTypes;

/* Base level for asset import, currently supported items: AUDIO(.wav), VIDEO(.png,.jpg,.jpeg) */

public class Assets {
    private  BufferedImage texture;
    private  Clip sound;
    private assetTypes type; 
    
    /* Set of overloaded signature constructors */
    public Assets(BufferedImage texture, Clip sound)
    {
        this.texture = texture;
        this.sound = sound;
        this.type = assetTypes.MIXED;
    }

     public Assets(BufferedImage texture)
    {
        this.texture = texture;
        this.type = assetTypes.VIDEO;
    }

     public Assets(Clip sound)
    {
        this.sound = sound;
        this.type = assetTypes.AUDIO;
    }

    public Assets()
    {
        this.type = assetTypes.NONE;
    }

    /* Set of Getters */
    public BufferedImage getTexture()
    {
        return this.texture;
    }

    public Clip getSound()
    {
        return this.sound;
    }

    public assetTypes getType()
    {
        return this.type;
    }
}
