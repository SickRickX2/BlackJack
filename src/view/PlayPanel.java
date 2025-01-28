package view;

import model.CardModel;
import model.DealerModel;
import model.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PlayPanel extends JPanel implements Observer {
    private Navigator navigator;
    private final int CARD_WIDTH = 150*80/100, CARD_HEIGHT = 204*80/100;
    private JButton hitButton;
    private JButton stayButton;

    public PlayPanel(Navigator navigator) {
        this.navigator = navigator;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        createButtons();
        System.out.println("dealer hand size: " + DealerModel.getInstance().getHand().size());
        System.out.println("dealer hand: " + DealerModel.getInstance().getHand());
    }

    protected void paintComponent(Graphics g) {
        setPanelSize();
        super.paintComponent(g);
        paintDealerHand(g);
        paintPlayerHand(g);
        this.setBackground(new Color(14, 14, 125));
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
        stayButton = new JButton("STAY");
        stayButton.addActionListener(e -> PlayerModel.getInstance().stay());
        stayButton.setBackground(Color.WHITE);
        stayButton.setForeground(Color.BLACK);
        stayButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        stayButton.setFocusPainted(false);
        return stayButton;
    }

    protected JButton createHitButton(){
        hitButton = new JButton("HIT");
        hitButton.addActionListener(e -> PlayerModel.getInstance().hit());
        hitButton.setBackground(Color.WHITE);
        hitButton.setForeground(Color.BLACK);
        hitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        hitButton.setFocusPainted(false);
        return hitButton;
    }

    public void disableButtons() {
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
    }

    private void paintHiddenCard(Graphics g){
        Image hiddenCard = new ImageIcon("res/images/cards/BACK.png").getImage();
        g.drawImage(hiddenCard, 255, 20,CARD_WIDTH,CARD_HEIGHT, this);
    }

    private void paintDealerHand(Graphics g){
        if (DealerModel.getInstance().isHidden()){
            paintHiddenCard(g);
        } else {
            revealHiddenCard(g);
        }
        for (int i = 1; i < DealerModel.getInstance().getHand().size(); i++){
            CardModel card = DealerModel.getInstance().getHand().get(i);
            Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            g.drawImage(cardImage,(255+10+CARD_WIDTH/2)+(10+CARD_WIDTH/2)*(i), 20,CARD_WIDTH,CARD_HEIGHT, this);
        }
    }

    private void revealHiddenCard(Graphics g){
        CardModel card = DealerModel.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 255, 20,CARD_WIDTH,CARD_HEIGHT, this);
    }

    private void paintPlayerHand(Graphics g){
        CardModel card = PlayerModel.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 255, 565,CARD_WIDTH,CARD_HEIGHT, this);
        for (int i = 1; i < PlayerModel.getInstance().getHand().size(); i++){
            card = PlayerModel.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            g.drawImage(cardImage,(255+10+CARD_WIDTH/2)+(10+CARD_WIDTH/2)*(i), 565,CARD_WIDTH,CARD_HEIGHT, this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        disableButtons();
        System.out.println("disattivato");
    }
}
