package view;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel {
    private Navigator navigator;

    public StartPanel(Navigator navigator) {
        this.navigator = navigator;
        setPanelSize();
        setLayout(null);
        createTitle();
        createStartButton();
        createQuitButton();

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1200, 800);
        setPreferredSize(size);
    }

    protected void paintComponent(Graphics g) {
        setPanelSize();
        super.paintComponent(g);
        int density = 50;
        g.setColor(new Color(14, 125, 125));
        for (int x = 0; x <= getWidth() + getHeight(); x += density) g.drawLine(x, 0, 0, x);
        this.setBackground(Color.BLACK);

    }

    private void createStartButton() {
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> navigator.navigate(Screen.PROFILE_SELECTION));
        startButton.setBounds(500, 500, 200, 50);
        startButton.setBackground(new Color(14, 125, 125));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        startButton.setFocusPainted(false);
        add(startButton);

    }

    private void createQuitButton() {
        JButton quitButton = new JButton("QUIT");

        quitButton.addActionListener(e -> System.exit(0));

        quitButton.setBounds(500, 600, 200, 50);
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        add(quitButton);
    }

    private void createTitle() {
        ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(300, 50, title.getIconWidth(), title.getIconHeight()); // Posiziona il titolo
        add(titleLabel);
    }
}


