package Engine.GameWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindow
{
    private JFrame wndFrame;
    private String wndTitle;
    private int wndWidth;
    private int wndHeight;

    private Canvas canvas;

    public GameWindow(String title, int width, int height) {
        wndTitle    = title;
        wndWidth    = width;
        wndHeight   = height;
        wndFrame    = null;
    }

    public void BuildGameWindow()
    {
        if(wndFrame != null) return;

        // Obține dimensiunea completă a ecranului
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        wndWidth = screenSize.width;
        wndHeight = screenSize.height;

        wndFrame = new JFrame(wndTitle);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        wndFrame.setUndecorated(true);
        wndFrame.setVisible(true);

        // Creează canvas la dimensiunea completă a ecranului
        canvas = new Canvas();
        canvas.setPreferredSize(screenSize);
        canvas.setMaximumSize(screenSize);
        canvas.setMinimumSize(screenSize);

        wndFrame.add(canvas);
        // Nu mai folosim pack() pentru că strică fullscreen-ul
        wndFrame.validate(); // doar validăm layout-ul
    }

    public int GetWndWidth() {
        return wndWidth;
    }

    public int GetWndHeight() {
        return wndHeight;
    }

    public Canvas GetCanvas() {
        return canvas;
    }
}
