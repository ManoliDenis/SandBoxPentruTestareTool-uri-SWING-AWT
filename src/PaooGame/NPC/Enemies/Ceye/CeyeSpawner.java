package PaooGame.NPC.Enemies.Ceye;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class CeyeSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new Ceye(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new Ceye();
    }


}
