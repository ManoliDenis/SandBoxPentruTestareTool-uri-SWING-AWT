package PaooGame.GameVariables;

import java.awt.*;

public class WindowVariables {
    public static final int WIDTH;
    public static final int HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;
    }
}
