package PaooGame.NPC.Enemies.Azgareth;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class AzgarethSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new Azgareth(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new Azgareth();
    }


}
