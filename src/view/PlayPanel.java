package view;

import controller.PlayPanelController;
import model.CardModel;
import model.Bot1Model;
import model.Bot2Model;
import model.DealerModel;
import model.PlayerModel;
import model.TurnManager;
import model.profiles.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * The PlayPanel class represents the panel where the game is played.
 * It handles the display of the game elements and user interactions.
 */
public class PlayPanel extends JPanel implements Observer {
    private PlayPanelController controller;
    private Navigator navigator;
    private final int CARD_WIDTH = 150 * 80 / 100, CARD_HEIGHT = 204 * 80 / 100;
    private JButton hitButton;
    private JButton stayButton;

    /**
     * Constructs a PlayPanel with the specified navigator.
     *
     * @param navigator the Navigator object used for navigation between screens
     */
    public PlayPanel(Navigator navigator) {
        this.controller = new PlayPanelController(navigator);
        this.navigator = navigator;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        createButtons();
        TurnManager.getInstance().addObserver(this);
    }

    /**
     * Paints the component with the game elements.
     *
     * @param g the Graphics object used for painting
     */
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

    /**
     * Sets the size of the panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(WindowDimensions.WIDTH, WindowDimensions.HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Creates and adds the buttons to the panel.
     */
    private void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(createHitButton());
        buttonPanel.add(createStayButton());
        buttonPanel.add(createQuitButton());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates and returns the quit button.
     *
     * @return the quit button
     */
    private JButton createQuitButton() {
        JButton quitButton = new JButton("QUIT");
        quitButton.addActionListener(e -> controller.onQuitButtonClicked());
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        return quitButton;
    }

    /**
     * Creates and returns the stay button.
     *
     * @return the stay button
     */
    private JButton createStayButton() {
        stayButton = new JButton("STAY");
        stayButton.addActionListener(e -> controller.onStayButtonClicked());
        stayButton.setBackground(Color.WHITE);
        stayButton.setForeground(Color.BLACK);
        stayButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        stayButton.setFocusPainted(false);
        return stayButton;
    }

    /**
     * Creates and returns the hit button.
     *
     * @return the hit button
     */
    private JButton createHitButton() {
        hitButton = new JButton("HIT");
        hitButton.addActionListener(e -> controller.onHitButtonClicked());
        hitButton.setBackground(Color.WHITE);
        hitButton.setForeground(Color.BLACK);
        hitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        hitButton.setFocusPainted(false);
        return hitButton;
    }

    /**
     * Disables the hit and stay buttons.
     */
    public void disableButtons() {
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
    }

    /**
     * Paints the hidden card of the dealer.
     *
     * @param g the Graphics object used for painting
     */
    private void paintHiddenCard(Graphics g) {
        Image hiddenCard = new ImageIcon("res/images/cards/BACK.png").getImage();
        g.drawImage(hiddenCard, 400, 20, CARD_WIDTH, CARD_HEIGHT, this);
    }

    /**
     * Paints the dealer's hand.
     *
     * @param g the Graphics object used for painting
     */
    private void paintDealerHand(Graphics g) {
        paintCPUAvatar(g, 345, 185);
        if (DealerModel.getInstance().isHidden()) {
            paintHiddenCard(g);
        } else {
            revealHiddenCard(g);
        }
        for (int i = 1; i < DealerModel.getInstance().getHand().size(); i++) {
            CardModel card = DealerModel.getInstance().getHand().get(i);
            Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            g.drawImage(cardImage, (400 + 10 + CARD_WIDTH / 2) + (10 + CARD_WIDTH / 2) * (i), 20, CARD_WIDTH, CARD_HEIGHT, this);
        }
    }

    /**
     * Reveals the hidden card of the dealer.
     *
     * @param g the Graphics object used for painting
     */
    private void revealHiddenCard(Graphics g) {
        CardModel card = DealerModel.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 400, 20, CARD_WIDTH, CARD_HEIGHT, this);
        paintDealerSum(g);
    }

    /**
     * Paints the dealer's sum.
     *
     * @param g the Graphics object used for painting
     */
    private void paintDealerSum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Dealer's sum: " + DealerModel.getInstance().getSum(), 400, 220);
    }

    /**
     * Paints the sum of Bot1.
     *
     * @param g the Graphics object used for painting
     */
    private void paintBot1Sum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Bot1's sum: " + Bot1Model.getInstance().getSum(), 10, 150);
        paintCPUAvatar(g, 10, 65);
    }

    /**
     * Paints the sum of Bot2.
     *
     * @param g the Graphics object used for painting
     */
    private void paintBot2Sum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString("Bot2's sum: " + Bot2Model.getInstance().getSum(), 970, 150);
        paintCPUAvatar(g, 970, 65);
    }

    /**
     * Paints the avatar of a CPU player.
     *
     * @param g the Graphics object used for painting
     * @param x the x-coordinate of the avatar
     * @param y the y-coordinate of the avatar
     */
    private void paintCPUAvatar(Graphics g, int x, int y) {
        Image avatar = new ImageIcon("res/images/AVATARS/6.png").getImage();
        g.drawImage(avatar, x, y, 60, 60, this);
    }

    /**
     * Paints the player's hand.
     *
     * @param g the Graphics object used for painting
     */
    private void paintPlayerHand(Graphics g) {
        CardModel card = PlayerModel.getInstance().getHand().getFirst();
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        g.drawImage(cardImage, 400, 565, CARD_WIDTH, CARD_HEIGHT, this);
        for (int i = 1; i < PlayerModel.getInstance().getHand().size(); i++) {
            card = PlayerModel.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            g.drawImage(cardImage, (400 + 10 + CARD_WIDTH / 2) + (10 + CARD_WIDTH / 2) * (i), 565, CARD_WIDTH, CARD_HEIGHT, this);
        }
        Image avatar = new ImageIcon("res/images/AVATARS/" + ProfileManager.getInstance().getSelectedProfile().getAvatarID() + ".png").getImage();
        g.drawImage(avatar, 340, 500, 60, 60, this);
    }

    /**
     * Paints the hand of Bot1.
     *
     * @param g the Graphics object used for painting
     */
    private void paintBot1Hand(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CardModel card = Bot1Model.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        int x0 = 40;
        int y0 = CARD_HEIGHT;
        g2d.rotate(Math.toRadians(90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);
        g2d.drawImage(cardImage, x0, y0, CARD_WIDTH, CARD_HEIGHT, this);
        g2d.rotate(Math.toRadians(-90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);

        for (int i = 1; i < Bot1Model.getInstance().getHand().size(); i++) {
            card = Bot1Model.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            int x1 = 40;
            int y1 = 80 + CARD_HEIGHT;
            g2d.rotate(Math.toRadians(90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
            g2d.drawImage(cardImage, x1 + CARD_WIDTH / 2 * (i), y1, CARD_WIDTH, CARD_HEIGHT, this);
            g2d.rotate(Math.toRadians(-90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
        }
        paintBot1Sum(g);
    }

    /**
     * Paints the hand of Bot2.
     *
     * @param g the Graphics object used for painting
     */
    private void paintBot2Hand(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        CardModel card = Bot2Model.getInstance().getHand().get(0);
        Image cardImage = new ImageIcon((card.getCardImagePath())).getImage();
        int x0 = 1040;
        int y0 = CARD_HEIGHT;
        g2d.rotate(Math.toRadians(90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);
        g2d.drawImage(cardImage, x0, y0, CARD_WIDTH, CARD_HEIGHT, this);
        g2d.rotate(Math.toRadians(-90), x0 + CARD_WIDTH / 2, y0 + CARD_HEIGHT / 2);

        for (int i = 1; i < Bot2Model.getInstance().getHand().size(); i++) {
            card = Bot2Model.getInstance().getHand().get(i);
            cardImage = new ImageIcon((card.getCardImagePath())).getImage();
            int x1 = 1040;
            int y1 = 80 + CARD_HEIGHT;
            g2d.rotate(Math.toRadians(90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
            g2d.drawImage(cardImage, x1 + CARD_WIDTH / 2 * (i), y1, CARD_WIDTH, CARD_HEIGHT, this);
            g2d.rotate(Math.toRadians(-90), x1 + CARD_WIDTH / 2, y1 + CARD_HEIGHT / 2);
        }
        paintBot2Sum(g);
    }

    /**
     * Paints the player's sum.
     *
     * @param g the Graphics object used for painting
     */
    private void paintPlayerSum(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawString(ProfileManager.getInstance().getSelectedProfile().getUsername() + "'s sum:" + PlayerModel.getInstance().getSum(), 400, 550);
    }

    /**
     * Paints the title of the game.
     *
     * @param g the Graphics object used for painting
     */
    private void paintTitle(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 30));
        g.drawImage(new ImageIcon("res/images/blackjacktitle.png").getImage(), 300, 70, this);
    }

    /**
     * Switches to the result panel based on the game outcome.
     *
     * @param win  true if the player won
     * @param tie  true if the game is a tie
     * @param lose true if the player lost
     */
    private void switchToResultPanel(boolean win, boolean tie, boolean lose) {
        int delay = 4000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (win) navigator.navigate(Screen.WIN);
                if (tie) navigator.navigate(Screen.TIE);
                if (lose) navigator.navigate(Screen.LOSE);
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Updates the panel based on the observed changes.
     *
     * @param o   the observable object
     * @param arg an argument passed to the notifyObservers method
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String result) {
            switch (result) {
                case "WIN":
                    switchToResultPanel(true, false, false);
                    break;
                case "TIE":
                    switchToResultPanel(false, true, false);
                    break;
                case "LOSE":
                    switchToResultPanel(false, false, true);
                    break;
            }
        } else {
            disableButtons();
        }
    }
}