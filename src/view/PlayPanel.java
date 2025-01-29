package view;

import model.*;

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

    }

    protected void paintComponent(Graphics g) {
        setPanelSize();
        super.paintComponent(g);
        paintTitle(g);
        paintDealerHand(g);
        paintPlayerHand(g);
        paintPlayerSum(g);

        if (TurnManager.getInstance().getBotCount() > 0) {
            paintBot1Hand(g);
        }
        if (TurnManager.getInstance().getBotCount() > 1) {
            paintBot2Hand(g);
        }


        this.setBackground(new Color(14, 125, 125));
    }

    private void setPanelSize() {
        Dimension size = new Dimension(WindowDimensions.WIDTH, WindowDimensions.HEIGHT);
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
        g.drawImage(hiddenCard, 400, 20,CARD_WIDTH,CARD_HEIGHT, this);
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
            g.drawImage(cardImage,(400+10+CARD_WIDTH/2)+(10+CARD_WIDTH/2)*(i), 20,CARD_WIDTH,CARD_HEIGHT, this);
        }
    }

    private void revealHiddenCard(Graphics g){
        CardModel card = DealerModel.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 400, 20,CARD_WIDTH,CARD_HEIGHT, this);
        paintDealerSum(g);
    }

    private void paintDealerSum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Dealer sum: " + DealerModel.getInstance().getSum(), 400, 220);
    }
    private void paintBot1Sum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Bot1 sum: " + Bot1Model.getInstance().getSum(), 10, 150);
    }
    private void paintBot2Sum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Bot2 sum: " + Bot2Model.getInstance().getSum(), 990, 150);
    }

    private void paintPlayerHand(Graphics g){
        CardModel card = PlayerModel.getInstance().getHand().getFirst();
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 400, 565,CARD_WIDTH,CARD_HEIGHT, this);
        for (int i = 1; i < PlayerModel.getInstance().getHand().size(); i++){
            card = PlayerModel.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            g.drawImage(cardImage,(400+10+CARD_WIDTH/2)+(10+CARD_WIDTH/2)*(i), 565,CARD_WIDTH,CARD_HEIGHT, this);
        }
    }
    private void paintBot1Hand(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CardModel card = Bot1Model.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        int x0 = 40 ;
        int y0 = CARD_HEIGHT;
        g2d.rotate(Math.toRadians(90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);
        g2d.drawImage(cardImage, x0, y0, CARD_WIDTH, CARD_HEIGHT, this);
        g2d.rotate(Math.toRadians(-90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);

        for (int i = 1; i < Bot1Model.getInstance().getHand().size(); i++) {
            card = Bot1Model.getInstance().getHand().get(i);
           cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            int x1 = 40 ;
            int y1 =  80 + CARD_HEIGHT;
            g2d.rotate(Math.toRadians(90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
            g2d.drawImage(cardImage, x1+CARD_WIDTH/2*(i), y1, CARD_WIDTH, CARD_HEIGHT, this);
            g2d.rotate(Math.toRadians(-90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
        }
        paintBot1Sum(g);
    }
    private void paintBot2Hand(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CardModel card = Bot2Model.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        int x0 = 1040 ;
        int y0 = CARD_HEIGHT;
        g2d.rotate(Math.toRadians(90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);
        g2d.drawImage(cardImage, x0, y0, CARD_WIDTH, CARD_HEIGHT, this);
        g2d.rotate(Math.toRadians(-90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);

        for (int i = 1; i < Bot2Model.getInstance().getHand().size(); i++) {
            card = Bot2Model.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            int x1 = 1040 ;
            int y1 =  80 + CARD_HEIGHT;
            g2d.rotate(Math.toRadians(90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
            g2d.drawImage(cardImage, x1+CARD_WIDTH/2*(i), y1, CARD_WIDTH, CARD_HEIGHT, this);
            g2d.rotate(Math.toRadians(-90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
        }
        paintBot2Sum(g);
    }
    private void paintPlayerSum(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Player sum: " + PlayerModel.getInstance().getSum(), 400, 550);
    }
    private void paintTitle(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawImage(new ImageIcon("res/images/blackjacktitle.png").getImage(), 300, 70, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        disableButtons();
    }
}
