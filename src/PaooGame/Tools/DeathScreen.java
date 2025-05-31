package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;
import PaooGame.GameVariables.WindowVariables;
import PaooGame.SceneManager.sceneManager;

import java.awt.*;
/// Daca HP->ul player-ului scade sub 1 atunci apare un ecran negru cu imaginea you died
public class DeathScreen {
    public static int counter = 120;
    public static void deathScreen(Graphics g){
        if(GameVariables.HP <= 0){
            counter--;
            if(counter>0) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WindowVariables.WIDTH, WindowVariables.HEIGHT);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.setColor(Color.WHITE);
                g.drawString("You Died", WindowVariables.WIDTH / 2 - 100, WindowVariables.HEIGHT / 2);
            }
            else{
                sceneManager.levelLoader(sceneManager.levels.Menu);
                counter = 120;
            }
        }
    }
}
