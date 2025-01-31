package view;

import controller.StartPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * The StartPanel class represents the initial panel of the application.
 * It is responsible for displaying the start screen with buttons to start or quit the game.
 */
public class StartPanel extends JPanel {
    private Navigator navigator;
    private StartPanelController controller;

    /**
     * Constructs a StartPanel with the specified navigator.
     *
     * @param navigator the Navigator object used for navigation between screens
     */
    public StartPanel(Navigator navigator) {
        this.navigator = navigator;
        this.controller = new StartPanelController(navigator);
        setPanelSize();
        setLayout(null);
        printLogo();
        textLogo();
        createTitle();
        createStartButton();
        createQuitButton();
    }

    /**
     * Sets the size of the panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(1200, 800);
        setPreferredSize(size);
    }

    /**
     * Paints the component with a custom background and grid lines.
     *
     * @param g the Graphics object used for painting
     */
    protected void paintComponent(Graphics g) {
        setPanelSize();
        super.paintComponent(g);
        int density = 50;
        g.setColor(new Color(14, 125, 125));
        for (int x = 0; x <= getWidth() + getHeight(); x += density) g.drawLine(x, 0, 0, x);
        this.setBackground(Color.BLACK);
    }

    /**
     * Creates and adds the start button to the panel.
     */
    private void createStartButton() {
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> controller.onStartButtonClicked());
        startButton.setBounds(500, 500, 200, 50);
        startButton.setBackground(new Color(14, 125, 125));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        startButton.setFocusPainted(false);
        add(startButton);
    }

    /**
     * Creates and adds the quit button to the panel.
     */
    private void createQuitButton() {
        JButton quitButton = new JButton("QUIT");
        quitButton.addActionListener(e -> controller.onQuitButtonClicked());
        quitButton.setBounds(500, 600, 200, 50);
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        add(quitButton);
    }

    /**
     * Prints the logo on the panel.
     */
    private void printLogo() {
        ImageIcon logo = new ImageIcon("res/images/logo.png");
        Image scaledLogo = logo.getImage().getScaledInstance(logo.getIconWidth() / 3, logo.getIconHeight() / 3, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(8, 670, logo.getIconWidth() / 2, logo.getIconHeight() / 2);
        add(logoLabel);
    }

    /**
     * Adds a text label with the author's name to the panel.
     */
    private void textLogo() {
        JLabel logo = new JLabel("by SickRick");
        logo.setBounds(120, 750, 200, 50);
        logo.setFont(new Font("Tahoma", Font.BOLD, 30));
        logo.setForeground(Color.WHITE);
        add(logo);
    }

    /**
     * Creates and adds the title image to the panel.
     */
    private void createTitle() {
        ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(300, 50, title.getIconWidth(), title.getIconHeight());
        add(titleLabel);
    }
}