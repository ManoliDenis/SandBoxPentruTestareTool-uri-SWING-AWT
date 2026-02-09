package Engine.GameVariables;

import java.awt.*;

import Engine.GameWindow.GameWindow;

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
