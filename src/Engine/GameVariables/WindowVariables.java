package Engine.GameVariables;

import Engine.GameWindow.GameWindow;

import java.awt.*;

public class WindowVariables {
    public static final int WIDTH;
    public static final int HEIGHT;
    public static GameWindow gameWindow;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;
    }
}
