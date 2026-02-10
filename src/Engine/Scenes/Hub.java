package Engine.Scenes;

import java.awt.*;
import Engine.GameVariables.WindowVariables;

public class Hub implements SceneTemplate {

    @Override
    public void levelUpdate() {

    }

    @Override
    public void levelInit() {

    }

    @Override
    public void levelDraw(Graphics g) {
        g.drawRect(0, 0,WindowVariables.WIDTH,WindowVariables.HEIGHT);  
        g.fillRect(0, 0, WindowVariables.WIDTH, WindowVariables.HEIGHT);
        g.setColor(Color.BLACK);
    }
}
