package PaooGame.NPC;

import PaooGame.NPC.Enemies.Enemy;

public abstract class Spawner {
    abstract public Enemy SpawnEnemy(int x, int y,int mapX,int mapY);
    abstract public Enemy SpawnEnemy();
}
