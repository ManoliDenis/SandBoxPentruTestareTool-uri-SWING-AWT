package PaooGame.NPC;

import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies = new ArrayList<>();


    public void update(int posX,int posY){
        //Update la Inamici
        for(Enemy e : enemies) {

            e.update(posX,posY);//Logica inamicilor
            e.moveEnemy(e.getSpeed(),e.getDirectionX(),e.getDirectionY());//Miscarea lor

            if(GameVariables.DEBUG)
                e.enemyDebug();
        }
    }

    public void render(Graphics g){/// Draw la fiecare inamic
        for(Enemy e : enemies) {
            e.render(g);
        }
    }
    public void remove(Enemy enemy){/// Scoate un inamic din lista
        try{
            enemies.remove(enemy);
        }catch (Exception e){System.out.println("Couldn't find enemy: " + enemy.getName());}
    }

    public void add(Enemy enemy){/// Pune un inamic in lista
        try {
            enemies.add(enemy);
        }catch (Exception e){System.out.println("Couldn't add enemy: "+ enemy.getName());}
    }

}
