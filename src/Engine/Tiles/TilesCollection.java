package Engine.Tiles;

import java.util.ArrayList;
import java.util.List;

public class TilesCollection {
    private List<Tile> terrainTiles; //This list can only contain terrain tiles
    private List<Tile> playerTiles; //This list can only contain player tiles
    private List<Tile> entitiesTiles; //This list can only contain entity tiles
    private List<Tile> projectilesTiles; //This list can only contain projectile tiles
    private List<Tile> specialTiles; //This list can only contain special tiles (ex: trap doors,etc)

    //This constructor's scope is to initialize the lists
    public TilesCollection() {
        terrainTiles = new ArrayList<>();
        playerTiles = new ArrayList<>();
        entitiesTiles = new ArrayList<>();
        projectilesTiles = new ArrayList<>();
        specialTiles = new ArrayList<>();
    }


    //Loads only the necessary tiles for each scene
    public static void loadTiles(int sceneID) {
        switch (sceneID) {
            case 0 -> {/* Do nothing */}
            case 1 -> {/* Load SandBox tiles */}
            default -> {System.out.println("Invalid scene ID: " + sceneID);}
        }
    }




}
