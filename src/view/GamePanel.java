package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
        private Game game;
        public GamePanel(Game game) {
            this.game = game;


            setPanelSize();
            setLayout(new BorderLayout());
            ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
            JLabel titleLabel = new JLabel(title);
            add(titleLabel, BorderLayout.NORTH);
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


