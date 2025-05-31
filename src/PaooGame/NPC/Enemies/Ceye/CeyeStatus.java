package PaooGame.NPC.Enemies.Ceye;

import PaooGame.NPC.Action;
import PaooGame.NPC.ActionEnum.ActionEnum;
import PaooGame.NPC.Enemies.Ceye.Actions.*;
import PaooGame.NPC.Enemies.Ceye.CeyeObserver;

///Ce vreau sa fac?
///In functie de status vreau ca inamicul sa executa actiune
/// actiunea sa fie executata in update
/// status ia un observer si returneaza in functie de observer un action care va fi executat in update la inamic
/// interface = null;
/// switch(observer){
/// case idle
///     interface = idle
/// case chase
///     interface = chase
///}
/// interface.update()
public class CeyeStatus {
    private ActionEnum oldAction = null;
    private Action action_to_execute;
    private final Action Patrol = new Patrol();
    private final Action Chase = new Chase();
    private final Action Attack = new Attack();
    private final Action IsDead = new IsDead();
    private final Action TakeDamage = new TakeDamage();

    public void status(CeyeObserver observer){
        ActionEnum newAction = observer.getAction();
        if(oldAction != newAction)
            switch (observer.getAction()){
                case Patrol -> {
                    action_to_execute = Patrol;
                    oldAction = ActionEnum.Patrol;
                }
                case Chase -> {
                    action_to_execute = Chase;
                    oldAction = ActionEnum.Chase;
                }
                case ATTACK -> {
                    action_to_execute = Attack;
                    oldAction = ActionEnum.ATTACK;
                }
                case IsDead ->{
                    action_to_execute = IsDead;
                    oldAction = ActionEnum.IsDead;
                }
                case TakingDamage -> {
                    action_to_execute = TakeDamage;
                    oldAction = ActionEnum.TakingDamage;
                }
                default -> {
                    action_to_execute = null;
                    oldAction = null;
                }
            }

    }
    public Action getAction(){
        return action_to_execute;
    }
}
