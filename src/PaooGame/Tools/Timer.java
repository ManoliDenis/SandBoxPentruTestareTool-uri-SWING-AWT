package PaooGame.Tools;

import PaooGame.Tools.Effects.FadeOut;
//Nefolosit
/// Simple timer, de recomandat sa se foloseasca alaturi de un Boolean de decizie
/// Usage example:
/// if(justLoaded && Timer.TimerStatus()) {
///             justLoaded = false;
///             Timer.CooldownReset();
///         }
///         else if(justLoaded) Timer.TimePasses();
/// In acest exemplu porneste cat timp bool-ul justLoaded este true, in momentul in care devine fals,
/// Timerul se opreste din calculat,
public class Timer {
    public static int COOLDOWNMAX = 10;
    public static int COOLDOWNCURR = COOLDOWNMAX;

    public static Boolean TimerStatus() { /// True --> it's done  False --> Clock's ticking
        if (COOLDOWNCURR <= 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void TimePasses(){
        if(Timer.COOLDOWNCURR > 0)
            Timer.COOLDOWNCURR--;
    }
    public static void CooldownReset() {
        COOLDOWNCURR = COOLDOWNMAX;
    }

    public static void CurrentTimer(){
        System.out.println(COOLDOWNCURR);
    }

    public static void setCooldown(int cooldown) {
        Timer.COOLDOWNCURR = cooldown;
        Timer.COOLDOWNMAX = cooldown;
    }
}
