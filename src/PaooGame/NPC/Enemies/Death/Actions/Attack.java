package PaooGame.NPC.Enemies.Death.Actions;
import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;

public class Attack implements Action {
    @Override
    public void Action(Enemy e) {
        //Resetam directiile pentru a nu se misca initial ->efectul de schimbare stare
        e.setDirectionX(0);
        e.setDirectionY(0);
        if(GameVariables.WORLD_CAN_TAKE_DAMAGE)
            GameVariables.HP -= 33;
        if(GameVariables.DEBUG)
            System.out.println("Attack");
    }
}
