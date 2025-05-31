package PaooGame.Tools;

import PaooGame.GameVariables.GameVariables;

import java.awt.*;

public class DrawHUD {
    /// Deseneaza HUD-UL destul de previzibil
    public static void drawHUD(Graphics g){
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.WHITE);
            g.drawString( "HP:" + GameVariables.HP, 10, 48);
            g.drawString("SCORE: " + GameVariables.SCORE, 10, 96);
            g.drawString("KILLCOUNT: " + GameVariables.enemieskilled, 10, 96+48);

    }
}
