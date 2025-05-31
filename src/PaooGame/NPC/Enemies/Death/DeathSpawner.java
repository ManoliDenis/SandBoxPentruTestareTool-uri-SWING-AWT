package PaooGame.NPC.Enemies.Death;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class DeathSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new Death(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new Death();
    }


}
