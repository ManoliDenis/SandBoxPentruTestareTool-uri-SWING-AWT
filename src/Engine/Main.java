package Engine;

import Engine.GameVariables.WindowVariables;

public class Main
{
    public static void main(String[] args)
    {
        Game SparkPlug = new Game("Engine", WindowVariables.WIDTH, WindowVariables.HEIGHT);
        SparkPlug.StartGame();
    }
}






















