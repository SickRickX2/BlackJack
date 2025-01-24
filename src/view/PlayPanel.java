package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PlayPanel extends JPanel implements Observer {
    private Navigator navigator;

    public PlayPanel(Navigator navigator) {
        this.navigator = navigator;
        setLayout(new BorderLayout());
        createPlayground();
        createButtons();
    }

    private void createPlayground() {
        JPanel playground = new JPanel();
        playground.setBackground(new Color(100, 30, 22));
        add(playground, BorderLayout.CENTER);
    }

    protected void paintComponent(Graphics g) {

        setPanelSize();
        super.paintComponent(g);
        int density = 50;
        g.setColor(Color.decode("#CC7A92"));
        for (int x = 0; x <= getWidth() + getHeight(); x += density) g.drawLine(x, 0, 0, x);
        this.setBackground(Color.BLACK);

    }
    private void setPanelSize() {
        Dimension size = new Dimension(1200,800);
        setPreferredSize(size);
    }
    protected void createButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(createHitButton());
        buttonPanel.add(createStayButton());
        buttonPanel.add(createQuitButton());
        add(buttonPanel, BorderLayout.SOUTH);

    }



    protected JButton createQuitButton(){
        JButton quitButton = new JButton("QUIT");


        quitButton.addActionListener(e -> System.exit(0));

        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        return quitButton;
    }
    protected JButton createStayButton(){
        JButton stayButton = new JButton("STAY");


        //stayButton.addActionListener(e -> System.exit(0));

        stayButton.setBackground(Color.decode("#CC7A92"));
        stayButton.setForeground(Color.BLACK);
        stayButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        stayButton.setFocusPainted(false);
        //add(stayButton, BorderLayout.SOUTH);
        return stayButton;
    }

    protected JButton createHitButton(){
        JButton hitButton = new JButton("HIT");


        //stayButton.addActionListener(e -> System.exit(0));

        hitButton.setBackground(Color.decode("#CC7A92"));
        hitButton.setForeground(Color.BLACK);
        hitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        hitButton.setFocusPainted(false);
        //add(stayButton, BorderLayout.SOUTH);
        return hitButton;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

