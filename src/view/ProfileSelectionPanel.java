package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ProfileSelectionPanel extends JPanel implements Observer {
        private Navigator navigator;


        public ProfileSelectionPanel(Navigator navigator) {
            this.navigator = navigator;


            setPanelSize();
            setLayout(null);
            createTitle();
            //createStartButton();
            createQuitButton();

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
        protected void createStartButton(){
            JButton startButton = new JButton("START");
            //startButton.addActionListener(e -> game.getGameModel().startGame());
            startButton.setBounds(500, 500, 200, 50);
            startButton.setBackground(Color.decode("#CC7A92"));
            startButton.setForeground(Color.BLACK);
            startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
            startButton.setFocusPainted(false);
            add(startButton);

        }
        protected void createQuitButton(){
            JButton quitButton = new JButton("MENU");
            quitButton.addActionListener(e -> navigator.navigate(Screen.START));
            quitButton.setBounds(500, 600, 200, 50);
            quitButton.setBackground(Color.decode("#CC7A92"));
            quitButton.setForeground(Color.BLACK);
            quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
            quitButton.setFocusPainted(false);
            add(quitButton);
        }

        protected void createTitle(){
            JLabel titleLabel = new JLabel("Profile Selection");
           // Posiziona il titolo
            titleLabel.setBounds(470, 100, 300, 50);
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
            titleLabel.setForeground(Color.WHITE);
            add(titleLabel);
        }


    @Override
    public void update(Observable o, Object arg) {

    }
}
