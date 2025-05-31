package PaooGame.Tools.Effects;

import PaooGame.GameVariables.WindowVariables;

import java.awt.*;

public class FadeOut {
    private static int alphaValue = 100;///Alpha value
    private static Color fadeColor = new Color(0,0,0,alphaValue);

    public static void setFadeColor(Color fadeColor) {
        FadeOut.fadeColor = fadeColor;
    }
    public static void FadeOut(Graphics g,int ccdValue,int mcdValue){
        if(alphaValue>=0) {
            alphaValue =255-((255*ccdValue)/mcdValue);
            System.out.println(alphaValue);
            g.setColor(fadeColor);
            g.fillRect(0, 0, WindowVariables.WIDTH, WindowVariables.HEIGHT);
            setFadeColor(new Color(0, 0, 0, alphaValue));
        }
    }

}
