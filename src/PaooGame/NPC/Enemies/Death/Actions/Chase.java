package PaooGame.NPC.Enemies.Death.Actions;
import PaooGame.GameVariables.GameVariables;
import PaooGame.NPC.Action;
import PaooGame.NPC.Enemies.Enemy;
import PaooGame.SceneManager.sceneManager;
import PaooGame.Scenes.Level_Template;
/// Clasa face exact ce trebuie insa am descoperit cateva probleme
/// TODO: Fixat centrul inamicului in functie de sprite deoarece patratul inspre care se duce incepe din coltul din
/// TODO: stanga sus!
/// FIX: Am adaugat un size pentru fiecare inamic astfel incat pozitia lui sa nu mai pointeze spre coltul din stanga sus
/// ci pe centrul lui, x+sizex/2 y+sizey/2
public class Chase implements Action {
    private int targetX;
    private int targetY;
    private int missileX;
    private int missileY;
    private int tarsileX;
    private int tarsileY;
    private final Level_Template c_level = sceneManager.levelReturn();
    private Boolean chase = true;

    @Override
    public void Action(Enemy e) {
        //Resetam directiile pentru a nu se misca initial ->efectul de schimbare stare
        e.setDirectionX(0);
        e.setDirectionY(0);
        e.setSpeed(8);
        if(GameVariables.DEBUG)
            System.out.println("Chase Action");



        //Pozitia player-ului
        targetX = c_level.getGlobalPlayerX();
        targetY = c_level.getGlobalPlayerY();

        //Pozitia inamicului
        missileX = e.getXRelativetoOrigin();
        missileY = e.getYRelativetoOrigin();

        //Distanta dintre player-inamic
        tarsileX = Math.abs(targetX - missileX);
        tarsileY = Math.abs(targetY - missileY);

        if(GameVariables.DEBUG)
            System.out.println("tarsileX: " + tarsileX + " tarsileY: " + tarsileY + " ATKRANGE: " + e.getATKRANGE() + " RANGE: " + e.getRANGE());

        if (tarsileX >= e.getATKRANGE() && tarsileY <= e.getRANGE()) {
            int dirX = Integer.compare(targetX, e.getXRelativetoOrigin());
            e.setDirectionX(dirX);
            e.setDirectionY(0);
            tarsileX -= dirX * e.getSpeed();
        } else if (tarsileY >= e.getATKRANGE() && tarsileY <= e.getRANGE()) {
            int dirY = Integer.compare(targetY, e.getYRelativetoOrigin());
            e.setDirectionY(    dirY);
            e.setDirectionX(0);
            tarsileY -= dirY * e.getSpeed();
        } else {
            if(GameVariables.DEBUG)
                System.out.println("Chase: target reached");
            e.setDirectionX(0);
            e.setDirectionY(0);
        }
    }
}
