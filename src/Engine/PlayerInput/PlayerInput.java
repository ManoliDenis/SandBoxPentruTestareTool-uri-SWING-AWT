package Engine.PlayerInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput implements KeyListener {
    public static Boolean GlobalInputLock = false; ///Folosit pentru blocarea input-ului de la tastatura atunci cand este necesar
    public static void setGlobalInputLock(Boolean globalInputLock) {GlobalInputLock = globalInputLock;}///Functia ce seteaza starea inputului

    /// Variable ce indica starea cheii apasate de la tastatura
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private static boolean enterPressed = false;
    private static boolean escPressed = false;

    /// Ceva variable de miscare adaugate de stefan, ar fi fain daca ar mai comenta si el
    private double speedX = 0, speedY = 0;
    /// Settere scrise de stefan, din nou, ar fi fain daca ar mai comenta si el
    public double getSpeedX() {
        return speedX;
    }
    public double getSpeedY() {
        return speedY;
    }

    /// Settere pentru starea cheilor
    public static boolean isLeftPressed() {
        return leftPressed;
    }
    public static boolean isRightPressed() {
        return rightPressed;
    }
    public static boolean isUpPressed() {
        return upPressed;
    }
    public static boolean isDownPressed() {
        return downPressed;
    }
    public static boolean isEnterPressed() {
        return enterPressed;
    }
    public static boolean isEscPressed() {
        return escPressed;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyTyped " + e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(GlobalInputLock) return;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (key == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_W) {
            upPressed = true;
        } else if (key == KeyEvent.VK_S) {
            downPressed = true;
        } else if (key == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (key == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(GlobalInputLock) return;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        } else if (key == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_W) {
            upPressed = false;
        } else if (key == KeyEvent.VK_S) {
            downPressed = false;
        } else if (key == KeyEvent.VK_ENTER) {
            enterPressed = false;
        } else if (key == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
    }

}
