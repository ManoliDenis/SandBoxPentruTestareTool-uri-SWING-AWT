package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;
/// Brief: aceasta clasa, folosita cum trebuie, poate oferi posibilitatea de a face orice actiune odata la {counter} frame-uri
public class WCN {
    public static int counter = 10;//Cu aceasta valoare vom determina numarul de frame-uri la care
                                    //se va incrementa orice sprite din lume

    public static void think(){
        if(counter > 0) { 
            if(GameVariables.WORLD_CAN_ANIMATE)
                GameVariables.WORLD_CAN_ANIMATE = false;
            counter--;
        }
        else{
            counter = 10;
            GameVariables.WORLD_CAN_ANIMATE = true;
        }
     }
}
