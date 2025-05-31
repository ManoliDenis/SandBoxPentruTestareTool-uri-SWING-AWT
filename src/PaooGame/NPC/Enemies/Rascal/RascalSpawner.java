package PaooGame.NPC.Enemies.Rascal;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class RascalSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new Rascal(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new Rascal();
    }


}
