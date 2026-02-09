package Engine.Graphics.AssetsManagement;

import java.util.HashMap;

/* Assets Collection, simpler way to store and find assets */
public class AssetsCollection {

    private static HashMap<String ,Assets> assets;

    public static void Init() 
    { 
        assets = new HashMap<String, Assets>();
    }

    //Adds a new asset in the dictionary based on a given tuple of type(String,BufferedImage)
    public static void newAsset(String assetName,Assets asset)
    {
        try 
        {
            assets.put(assetName, asset);
        }
        catch (Exception e) 
        { 
            e.printStackTrace();
        }
    }

    //Removes an asset based on the given key
    public static void removeAsset(String assetName)
    {
        try
        {
            if(!assets.isEmpty())
                assets.remove(assetName);       
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Replaces an asset based on the given key
    public static void replaceAsset(String assetName,Assets asset)
    {
        try
        {
            assets.replace(assetName, asset);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //Returns an asset's data
    public static Assets getAsset(String assetName)
    {
        try
        {
            return assets.get(assetName);   
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
