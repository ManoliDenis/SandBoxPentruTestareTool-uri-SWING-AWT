package PaooGame.NPC.Enemies.SlimeKing.Actions;
import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Tools.NextLevelDoor;

public class IsDead implements Action {
    @Override
    public void Action(Enemy e) {
       // System.out.println("HELLOOOO IM DEAAAD!");
        if(!GameVariables.isBossDead) {
            GameVariables.isBossDead = true;
            //NextLevelDoor.nextLevelDoor(1);
        }
    }
}
