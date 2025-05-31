package PaooGame.NPC.Enemies.SlimeKing;

import PaooGame.NPC.Enemies.Enemy;
import PaooGame.NPC.Spawner;

public class SlimeKingSpawner extends Spawner {
    @Override
    public Enemy SpawnEnemy(int x, int y,int mapX,int mapY) {
        return new SlimeKing(x, y, mapX,mapY);
    }
    @Override
    public Enemy SpawnEnemy() {
        return new SlimeKing();
    }


}
