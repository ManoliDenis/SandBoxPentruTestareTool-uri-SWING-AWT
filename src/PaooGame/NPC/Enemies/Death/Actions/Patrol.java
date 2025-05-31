package PaooGame.NPC.Enemies.Death.Actions;

import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;

public class Patrol implements Action {
    private boolean patrol = false;
    private int targetX;
    private int targetY;

    @Override
    public void Action(Enemy e) {
        if(GameVariables.DEBUG)
            System.out.println("patrol");
        e.setSpeed(1);
        if (patrol) {
            if (targetX != 0 || targetY != 0) {
                int oldX = targetX;
                int oldY = targetY;

                if (targetX != 0) {
                    int dirX = Integer.compare(targetX, 0);
                    e.setDirectionX(dirX);
                    e.setDirectionY(0);
                    targetX -= dirX * e.getSpeed();

                    // Stop dacă semnul s-a schimbat (am trecut peste țintă)
                    if (Integer.signum(oldX) != Integer.signum(targetX)) {
                        targetX = 0;
                    }
                } else if (targetY != 0) {
                    int dirY = Integer.compare(targetY, 0);
                    e.setDirectionY(dirY);
                    e.setDirectionX(0);
                    targetY -= dirY * e.getSpeed();

                    if (Integer.signum(oldY) != Integer.signum(targetY)) {
                        targetY = 0;
                    }
                }
            }

            if (targetX == 0 && targetY == 0) {
                if (GameVariables.DEBUG)
                    System.out.println("Patrol: target reached");
                e.setDirectionX(0);
                e.setDirectionY(0);
                patrol = false;
            }
        }
        else {

            int maxDist = 64;
            targetX = (Math.random() < 0.5 ? -1 : 1) * (16 + (int)(Math.random() * (maxDist - 16)));
            targetY = (Math.random() < 0.5 ? -1 : 1) * (16 + (int)(Math.random() * (maxDist - 16)));
            patrol = true;
        }
    }

}
