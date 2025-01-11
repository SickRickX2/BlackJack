package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;


public class StartPanel extends JPanel {
        private Game game;
        public StartPanel(Game game) {
            this.game = game;


            setPanelSize();
            setLayout(null);
            ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
            JLabel titleLabel = new JLabel(title);
            titleLabel.setBounds(300, 50, title.getIconWidth(), title.getIconHeight()); // Posiziona il titolo
            JButton startButton = new JButton("START");
            //startButton.addActionListener(e -> game.startGame());
            add(titleLabel);
            startButton.setBounds(500, 500, 200, 50);
            startButton.setBackground(Color.decode("#CC7A92"));
            startButton.setForeground(Color.BLACK);
            startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
            startButton.setFocusPainted(false);
            add(startButton);

            JButton quitButton = new JButton("QUIT");

            quitButton.setBounds(500, 600, 200, 50);
            quitButton.setBackground(Color.decode("#CC7A92"));
            quitButton.setForeground(Color.BLACK);
            quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
            quitButton.setFocusPainted(false);
            add(quitButton);
        }
        private void setPanelSize() {
            Dimension size = new Dimension(1200,800);
            setPreferredSize(size);
        }
        protected void paintComponent(Graphics g) {
            setPanelSize();
            super.paintComponent(g);
            int density = 50;
            g.setColor(Color.decode("#CC7A92"));
            for (int x = 0; x <= getWidth() + getHeight(); x += density) g.drawLine(x, 0, 0, x);
            this.setBackground(Color.BLACK);

        }
        public Game getGame() {
            return game;
        }
    }


