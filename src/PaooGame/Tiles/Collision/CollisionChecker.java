package PaooGame.Tiles.Collision;

import PaooGame.GameWindow.GameWindow;
import PaooGame.SceneManager.sceneManager;
import PaooGame.PlayerInput.Player;
import PaooGame.Tiles.Tile;
import java.awt.Rectangle;

public class CollisionChecker {

    // am declarat static pentru motive de optimizare
    // astfel nu trebuie sa creez cate un nou rectangle pentru fiecare check, doar il setez pe cel curent la coordonatele respective ale unui tile
    static Rectangle tempTileRect = new Rectangle(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

    public boolean checkCollision(int newPosX, int newPosY, GameWindow wnd) {
        int[][] map = sceneManager.getMap(); /// extrage mapa curenta din sceneManager

        Player player = (Player) Tile.playerTile;
        int playerScreenX = wnd.GetWndWidth() / 2;
        int playerScreenY = wnd.GetWndHeight() / 2;
        Rectangle playerCollisionBox = new Rectangle(playerScreenX + player.solidArea.x, playerScreenY + player.solidArea.y, player.solidArea.width, player.solidArea.height);

        // IMPORTANT: daca un tile e solid
        //se creează DINAMIC un Rectangle care reprezintă zona de coliziune a unui tile solid
        //in functie de actuala pozitie a playerului pe harta, se verifica daca exista o coliziune cu tileul
        //(pe scurt se intersecteaza collisionBoxul playerului cu cel creat temporar al tile-ului pentru a preveni playerul din a trece peste tile)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int tileId = map[i][j];
                Tile tile = Tile.tiles[tileId];
                if (tile.IsSolid()) {
                    tempTileRect.setBounds(newPosX + i * Tile.TILE_WIDTH, newPosY + j * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                    if (playerCollisionBox.intersects(tempTileRect)) {
                        return true;
                    }
                }
            }
        }
        return false;

        // am folosit Axis-Aligned Bounding Box (AABB) pentru detectarea coliziunilor
    }

    public boolean checkTileCollision(int worldX, int worldY, int width, int height, int[][] map) {
        int tileW = Tile.TILE_WIDTH;
        int tileH = Tile.TILE_HEIGHT;

        // Colturile dreptunghiului
        int leftCol   = worldX / tileW;
        int rightCol  = (worldX + width)  / tileW;
        int topRow    = worldY / tileH;
        int bottomRow = (worldY + height) / tileH;


        for (int col = leftCol; col <= rightCol; col++) {
            for (int row = topRow; row <= bottomRow; row++) {

                if (col < 0 || row < 0 || col >= map.length || row >= map[0].length)
                    continue;
                Tile tile = Tile.tiles[ map[col][row] ];
                if (tile != null && tile.IsSolid()) {
                    return true;
                }
            }
        }
        return false;
    }

}