package PaooGame;

import PaooGame.GameVariables.WindowVariables;
import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("Abyss Covenant", WindowVariables.WIDTH, WindowVariables.HEIGHT);
        paooGame.StartGame();
    }
}






















