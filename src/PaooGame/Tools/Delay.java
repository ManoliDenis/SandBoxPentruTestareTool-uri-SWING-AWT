package PaooGame.Tools;
/// Nefolosit
/// Daca Game este fixat sa ruleze pe 60 de cadre pe secunda inseamna ca toata logica este fortata pe 60 de repetitii
/// pe secunda ceea ce ar induce functionalitatea fDelay-ului ,insa ar depreca functionalitatea nsDelay-ului pentru
/// perioade mult mai mari de timp
/// nici un timer nu functioneaza as perfect as should FOR both rely on something that shan't vary for it affects precision
public class Delay {
    public long END_TIMER = -1;
    public long FRAME_PERIOD = 0;
    public static enum DelayType {nsDelay, fDelay};
    private DelayType delayType;
    Boolean START = false;

    Delay(long Delay, DelayType type) {
        try {
            if (type == DelayType.nsDelay) {
                END_TIMER = System.nanoTime() + Delay;
                delayType = type;
                START = true;
            } else if (type == DelayType.fDelay) {
                FRAME_PERIOD = Delay;
                delayType = type;
                START = true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {System.out.println("Delay type not recognized | Delay will not be executed");}
    }

    public Delay(long Delay) {this.FRAME_PERIOD = Delay; delayType = DelayType.fDelay; START = true;}

    public Delay(){
        this(0);
        System.out.println("Fatal Warning, The delay needs at least one variable in order to work. " +
            "Use setDelay(long) to set the interval for your delay. Optional: you can also set the delay type (default is fDelay).");

    }

    public void setDelay(long Delay) {if(delayType == DelayType.nsDelay) { END_TIMER = System.nanoTime() + Delay;} else { FRAME_PERIOD = Delay; }}
    public void setDelayType(DelayType type) {delayType = type;}///Needs to be protecteded
    public void setStart(boolean start) {START = start;}

    public Boolean sleep() {

        if(START)
            switch (delayType) {
                case nsDelay:
                    return nsDelay();
                case fDelay:
                    return fDelay();
                default:
                    System.out.println("Delay type not recognized | Delay will not be executed");
                    return false;
            }
        return false;
    }
    /// nsDelay -> nanoseconds Delay : Ofera un cooldown pentru anumite operatii in functie de numarul de nanosecunde dorite
    public Boolean nsDelay(){
        if(System.nanoTime() > END_TIMER) {
            START = false;
            return true;
        }
        return false;
    }///Not accurate at all , everything is fixed to 60 cycles/second and because some pieces of hardware have different
    /// capabilities on running the app, the delay will be inaccurate, not recommended to use for a timer

    /// fDelay -> frame Delay: Ofera un cooldown pentru anumite operatii in functie de numarul de frame-uri dorite
    public Boolean fDelay(){
        System.out.println(FRAME_PERIOD + "<<< Frame Period");
        if(FRAME_PERIOD == 0){START=false;return true;}
        FRAME_PERIOD--;
        return false;
    }///Recommended to use, it's not as precise as the nsDelay should theoretically be as it depends entirely on the cycles,
    /// it'll entirely rely on the hardware to give the delay effect. If the hardware gets slower the delay will still respect
    /// the original task but it'll slow down the game even more
}
