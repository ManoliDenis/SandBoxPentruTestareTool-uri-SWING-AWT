package PaooGame.NPC.Enemies.Ceye.Actions;
import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;

public class TakeDamage implements Action {
    @Override
    public void Action(Enemy e) {
        System.out.println("I took damage!");
        e.setDirectionX(0);
        e.setDirectionY(0);
        e.setTookDamage(true);
        if(e.getHealth() - GameVariables.playerATKDAMAGE <= 0)
        {GameVariables.SCORE += 10;GameVariables.enemieskilled++;GameVariables.playerATKDAMAGE+=5;}
        e.entitytakeDamage(GameVariables.playerATKDAMAGE);
    }
}
