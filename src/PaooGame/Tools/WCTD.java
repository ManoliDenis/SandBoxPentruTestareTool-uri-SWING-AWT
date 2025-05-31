package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;

public class WCTD {
    public static int counter = 10;//Cu aceasta valoare vom determina numarul de frame-uri la care
    //se va da dreptul de a ataca

    public static void think(){ //Seteaza WCTD pe true daca counterul s-a terminat,astfel luma va putea lua damage o data la 10 frame-uri
        if(counter > 0) {
            if(GameVariables.WORLD_CAN_TAKE_DAMAGE)
                GameVariables.WORLD_CAN_TAKE_DAMAGE = false;
            counter--;
        }
        else{
            counter = 10;
            GameVariables.WORLD_CAN_TAKE_DAMAGE = true;
        }
    }
}
