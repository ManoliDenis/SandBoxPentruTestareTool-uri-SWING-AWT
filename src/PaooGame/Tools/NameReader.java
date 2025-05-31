package PaooGame.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Implementat de AI
public class NameReader {
    private String name = null;

    public NameReader() {
        // Creare fereastră
        Frame frame = new Frame("Enter your name");
        frame.setSize(300, 120);
        frame.setLayout(new FlowLayout());

        // TextBox + buton
        TextField nameField = new TextField(20);
        Button okButton = new Button("OK");

        // Adăugare componente
        frame.add(new Label("Name:"));
        frame.add(nameField);
        frame.add(okButton);

        // Comportament buton
        okButton.addActionListener(e -> {
            name = nameField.getText();
            frame.setVisible(false);
            frame.dispose();
        });

        // Închidere fereastră
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });

        // Afișare
        frame.setVisible(true);
    }

    public String getName() {
        return name;
    }
}
