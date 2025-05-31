package PaooGame.NPC.Enemies.Kamikaze;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class KamikazeSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new Kamikaze(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new Kamikaze();
    }


}
