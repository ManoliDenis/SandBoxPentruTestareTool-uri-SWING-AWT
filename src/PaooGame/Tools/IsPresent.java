package PaooGame.Tools;

public class IsPresent { /// Functie ce returneaza true daca pozitia entitatii se afla in aria zonei
    public static Boolean isPresent(int left_upper_corner_x,int left_upper_corner_y,int right_lower_corner_x,int right_lower_corner_y,int entity_x, int entity_y){
        return (left_upper_corner_x <= entity_x && entity_x <= right_lower_corner_x && left_upper_corner_y <= entity_y && entity_y <= right_lower_corner_y);
    }
}
