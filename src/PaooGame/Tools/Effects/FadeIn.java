package PaooGame.Tools.Effects;

import PaooGame.GameVariables.WindowVariables;

import java.awt.*;
/// FadeIN tool, creeaza efectul de fade in in functie de cooldown-ul dat si timpul curent in timer
/// exemplu: avem nevoie de un cooldown de 10 secunde inseamna ca cooldownmax este de 10 secunde,iar
/// in momentul in care acesta devine 0, adica currentCooldown FadeIn-ul se opreste din procesat
/// Atentie: FadeIn tool-ul functioneaza pe baza unui sistem TIMER-COOLDOWN,folosind in special
/// procentajul de cooldown activ pe timer pentru valoarea opacitatii efectului de fade
public class FadeIn {

    private static int alphaValue = 100;///Alpha value
    private static Color fadeColor = new Color(0,0,0,alphaValue);

    public static void setFadeColor(Color fadeColor) {
        FadeIn.fadeColor = fadeColor;
    }
    public static void Fadein(Graphics g,int ccdValue,int mcdValue){
        if(alphaValue>=0) {
            alphaValue =(255*ccdValue)/mcdValue;
            System.out.println(alphaValue);
            g.setColor(fadeColor);
            g.fillRect(0, 0, WindowVariables.WIDTH, WindowVariables.HEIGHT);
            setFadeColor(new Color(0, 0, 0, alphaValue));
        }
    }
}
