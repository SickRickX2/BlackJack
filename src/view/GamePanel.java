package view;

import controller.Game;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;



    public class GamePanel extends JPanel {
        private Game game;
        public GamePanel(Game game) {
            this.game = game;


            setPanelSize();
        }



        private void setPanelSize() {
            Dimension size = new Dimension(1200,800);
            setPreferredSize(size);
        }

        public void updateGame() {

        }
        public void paintComponent(Graphics g) {
            setPanelSize();
            super.paintComponent(g);
            game.render(g);
        }
        public Game getGame() {
            return game;
        }
    }


