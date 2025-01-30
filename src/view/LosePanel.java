package view;

import javax.swing.*;
import java.awt.*;

public class LosePanel extends JPanel {
    public LosePanel(Navigator navigator) {
        JLabel label = new JLabel("You lose!");
        add(label);
        createQuitButton();
        createTitle();

    }
    protected void createQuitButton(){
        JButton quitButton = new JButton("QUIT");

        quitButton.setBounds(500, 600, 200, 50);
        quitButton.setBackground(Color.decode("#CC7A92"));
        quitButton.setForeground(Color.BLACK);
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        add(quitButton);
    }

    protected void createTitle(){
        ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(300, 50, title.getIconWidth(), title.getIconHeight()); // Posiziona il titolo
        add(titleLabel);
    }
}
