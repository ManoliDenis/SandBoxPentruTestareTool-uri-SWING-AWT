package PaooGame.NPC.Enemies.SlimeKing.Actions;

import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Tools.EnemyTilePos;

public class Patrol implements Action {
    private boolean patrol = false;
    private boolean returning = false;

    private int offsetX, offsetY;     // distanța de parcurs (dus)
    private int remainingX, remainingY;

    @Override
    public void Action(Enemy e) {
        if (GameVariables.DEBUG)
            System.out.println("patrol");

        e.setSpeed(1);

        if (patrol) {
            int oldX = remainingX;
            int oldY = remainingY;

            if (remainingX != 0) {
                int dirX = Integer.compare(remainingX, 0);
                boolean valid = EnemyTilePos.canMoveThere(
                        e.getXRelativetoOrigin() + dirX * e.getSpeed(),
                        e.getYRelativetoOrigin()
                );

                if (!valid) {
                    e.setDirectionX(dirX);
                    e.setDirectionY(0);
                    remainingX -= dirX * e.getSpeed();
                    if (Integer.signum(oldX) != Integer.signum(remainingX))
                        remainingX = 0;
                } else {
                    e.setDirectionX(0);
                    e.setDirectionY(0);
                    patrol = false;
                }

            } else if (remainingY != 0) {
                int dirY = Integer.compare(remainingY, 0);
                boolean valid = EnemyTilePos.canMoveThere(
                        e.getXRelativetoOrigin(),
                        e.getYRelativetoOrigin() + dirY * e.getSpeed()
                );

                if (!valid) {
                    e.setDirectionY(dirY);
                    e.setDirectionX(0);
                    remainingY -= dirY * e.getSpeed();
                    if (Integer.signum(oldY) != Integer.signum(remainingY))
                        remainingY = 0;
                } else {
                    e.setDirectionX(0);
                    e.setDirectionY(0);
                    patrol = false;
                }
            }

            if (remainingX == 0 && remainingY == 0) {
                if (!returning) {
                    returning = true;
                    remainingX = -offsetX;
                    remainingY = -offsetY;
                } else {
                    e.setDirectionX(0);
                    e.setDirectionY(0);
                    patrol = false;
                    returning = false;
                }
            }

        } else {
            int maxDist = 64;
            offsetX = (Math.random() < 0.5 ? -1 : 1) * (16 + (int)(Math.random() * (maxDist - 16)));
            offsetY = (Math.random() < 0.5 ? -1 : 1) * (16 + (int)(Math.random() * (maxDist - 16)));
            remainingX = offsetX;
            remainingY = offsetY;
            patrol = true;
            returning = false;
        }
    }
}
