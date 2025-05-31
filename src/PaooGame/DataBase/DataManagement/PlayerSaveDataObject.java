package PaooGame.DataBase.DataManagement;

public class PlayerSaveDataObject {
    public int mapPositionX;
    public int mapPositionY;
    public int currentLevel;
    public int currentScore;
    public int hp;
    public PlayerSaveDataObject(int mapPositionX, int mapPositionY, int currentLevel, int currentScore, int hp) {
        this.mapPositionX = mapPositionX;
        this.mapPositionY = mapPositionY;
        this.currentLevel = currentLevel;
        this.currentScore = currentScore;
        this.hp = hp;
    }
}
