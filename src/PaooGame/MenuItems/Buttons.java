package PaooGame.MenuItems;

import PaooGame.GameVariables.WindowVariables;

import java.awt.*;

public class Buttons {
    String buttonText;
    int buttonX;
    int buttonY;
    int width;
    int height;
    FontMetrics metrics;
    int textWidth;
    int textHeight;
    int textX;
    int textY;
    Boolean buttonSelected;
    Font font = new Font("Arial", Font.BOLD, 40);

    /// Default Constructor
    public Buttons() {
        this.buttonText = "";
        this.buttonX = 0;
        this.buttonY = 0;
        this.width = 0;
        this.height = 0;
        this.buttonSelected = false;
    }

    /// Datele pentru buton sunt selectate manuale
    public Buttons(String buttonText, int buttonX, int buttonY,int width,int height, Boolean buttonSelected) {
        this.buttonText = buttonText;
        this.buttonX = buttonX;
        this.buttonY = buttonY;
        this.buttonSelected = buttonSelected;
    }

    /// Centreaza pe ambele axe
    public Buttons(String buttonText, int width,int height, Boolean buttonSelected,Boolean Centered) {
       this.buttonText = buttonText;
       this.buttonX = Centered?WindowVariables.WIDTH/2-width/2:0;
       this.buttonY = Centered?WindowVariables.HEIGHT/2-height/2:0;
       this.buttonSelected = buttonSelected;
       this.width = width;
       this.height = height;
    }

    /// Daca LRCentered e true --> Buton Centrat pe X
    /// Daca LRCentered e false --> Buton Centrat pe Y
    public Buttons(String buttonText,int buttonPos, int width,int height, Boolean buttonSelected,Boolean LRCentered) {
        this.buttonText = buttonText;
        this.buttonX = LRCentered?WindowVariables.WIDTH/2-width/2:buttonPos;
        this.buttonY = !LRCentered?WindowVariables.HEIGHT/2-height/2:buttonPos;
        this.buttonSelected = buttonSelected;
        this.width = width;
        this.height = height;
    }

    public void ButtonRender(Graphics g){
        g.setFont(font);
        metrics = g.getFontMetrics(font);
        textWidth = metrics.stringWidth(buttonText);
        textHeight = metrics.getHeight();
        textX = buttonX + (width - textWidth) / 2;
        textY = buttonY + ((height - textHeight) / 2) + metrics.getAscent();

        if (buttonSelected) {
            g.setColor(Color.WHITE);
            g.fillRect(buttonX, buttonY, width, height);
            g.setColor(Color.BLACK);
            g.drawString(buttonText, textX, textY);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(buttonX, buttonY, width, height);
            g.setColor(Color.WHITE);
            g.drawString(buttonText, textX, textY);
        }
    }
    public void setButtonSelected(Boolean buttonSelected) {
        this.buttonSelected = buttonSelected;
    }

}
