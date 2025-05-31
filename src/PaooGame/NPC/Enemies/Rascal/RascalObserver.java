package PaooGame.NPC.Enemies.Rascal;

import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.ActionEnum.ActionEnum;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.Projectiles.Bullet;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;

public class RascalObserver {
    ActionEnum action = ActionEnum.Patrol;///Defaul patruleaza
    Level_Template curent_level = sceneManager.levelReturn();
    boolean hit = false;
    public void check(Enemy e){
            /// Verificari returneaza ActionEnum type
            Level_Template curent_level = sceneManager.levelReturn();
            int distance_between_entitiesX =Math.abs(e.getXRelativetoOrigin()-curent_level.getGlobalPlayerX());
            int distance_between_entitiesY =Math.abs(e.getYRelativetoOrigin()-curent_level.getGlobalPlayerY());


            int distance_between_entity_and_bulletX;
            int distance_between_entity_and_bulletY;
            try{
            for(Bullet bullet : curent_level.getBullets()) {
                distance_between_entity_and_bulletX = Math.abs(e.getXRelativetoOrigin()+40 - bullet.getX());
                distance_between_entity_and_bulletY = Math.abs(e.getYRelativetoOrigin()+40 - bullet.getY());
                if(distance_between_entity_and_bulletX < 64 && distance_between_entity_and_bulletY < 64)//colt stanga sus
                {
                    hit = true;
                    bullet.delete();
                }
            }
            }catch (Exception ex){}

            if(GameVariables.DEBUG)
                System.out.println(e.getXRelativetoOrigin()+" "+e.getMapX()+" "+e.getYRelativetoOrigin()+" "+e.getMapY());

            if(e.getHealth()<=0) //Daca e mort nu face nimic
                action = ActionEnum.IsDead;
            else if(hit && GameVariables.WORLD_CAN_TAKE_DAMAGE){ //Daca e lovit si poate lua damage pierde hp
                action = ActionEnum.TakingDamage;
                hit = false;
            }
            else if(distance_between_entitiesX < e.getATKRANGE()  && distance_between_entitiesY < e.getATKRANGE()) //Daca e in range ataca
                action = ActionEnum.ATTACK;
            else if(distance_between_entitiesX < e.getRANGE() && distance_between_entitiesY < e.getRANGE()){ //Daca in range urmareste
                action = ActionEnum.Chase;
            }else action = ActionEnum.Patrol;//Daca e in viata si nu are ce face patruleaza

    }
    public ActionEnum getAction(){
        return this.action;
    }
}
