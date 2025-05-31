package PaooGame.NPC.Enemies.Death;

import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.ActionEnum.ActionEnum;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Projectiles.Bullet;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;
import PaooGame.Tools.CloseBossRoom;
import PaooGame.Tools.IsPresent;

public class DeathObserver {
    ActionEnum action = ActionEnum.Patrol;///Defaul patruleaza
    Level_Template curent_level = sceneManager.levelReturn();
    boolean hit = false;
    boolean isinroom = false;
    public void check(Enemy e){
        /// Verificari returneaza ActionEnum type
        Level_Template curent_level = sceneManager.levelReturn();
        int distance_between_entitiesX =Math.abs(e.getXRelativetoOrigin()-curent_level.getGlobalPlayerX());
        int distance_between_entitiesY =Math.abs(e.getYRelativetoOrigin()-curent_level.getGlobalPlayerY());


        int distance_between_entity_and_bulletX;
        int distance_between_entity_and_bulletY;
        try {
            for (Bullet bullet : curent_level.getBullets()) {
                distance_between_entity_and_bulletX = Math.abs(e.getXRelativetoOrigin() + 200 - bullet.getX());
                distance_between_entity_and_bulletY = Math.abs(e.getYRelativetoOrigin() + 200 - bullet.getY());
                if (distance_between_entity_and_bulletX < 52 && distance_between_entity_and_bulletY < 52)//colt stanga sus
                {
                    hit = true;
                    bullet.delete();
                }
            }
        }catch (Exception ex){}
        isinroom = IsPresent.isPresent(238,1837,1650,3012,curent_level.getGlobalPlayerX(),curent_level.getGlobalPlayerY());
        if(GameVariables.DEBUG)
            System.out.println(e.getXRelativetoOrigin()+" "+e.getMapX()+" "+e.getYRelativetoOrigin()+" "+e.getMapY());
        if(isinroom)
        {GameVariables.isBossRoom= true; CloseBossRoom.closeBossRoom();}
        if(e.getHealth()<=0) //Daca e mort nu face nimic
            action = ActionEnum.IsDead;
        else if(hit && GameVariables.WORLD_CAN_TAKE_DAMAGE){ //Daca e lovit si poate lua damage pierde hp
            action = ActionEnum.TakingDamage;
            hit = false;
        }
        else if(isinroom && distance_between_entitiesX < e.getATKRANGE()  && distance_between_entitiesY < e.getATKRANGE()) //Daca e in range ataca
            action = ActionEnum.ATTACK;
        else if(isinroom && distance_between_entitiesX < e.getRANGE() && distance_between_entitiesY < e.getRANGE()){ //Daca in range urmareste
            action = ActionEnum.Chase;
        }else action = ActionEnum.Patrol;//Daca e in viata si nu are ce face patruleaza

    }
    public ActionEnum getAction(){
        return this.action;
    }
}
