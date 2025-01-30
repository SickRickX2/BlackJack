package view;


import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer {
    private JPanel deck;
    private PlayPanel playPanel;

    public GameWindow() {


        initDeck();
        initWindow();

    }

    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BlackJack");
        setIcon();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setIcon() {
        Image icon = new ImageIcon("res/images/Jicon.png").getImage();
        try {
            Taskbar.getTaskbar().setIconImage(icon);
        } catch (Exception e) {
        }
        setIconImage(icon);
    }

    private void initDeck() {
        Navigator navigator = new Navigator();
        navigator.addObserver(this);
        playPanel = new PlayPanel(navigator);
        deck = new JPanel(new CardLayout()) {
            {
                add(new StartPanel(navigator), Screen.START.name());
                add(new ProfileSelectionPanel(navigator), Screen.PROFILE_SELECTION.name());
                add(playPanel, Screen.PLAY.name());
                add(new WinPanel(navigator), Screen.WIN.name());
                add(new LosePanel(), Screen.LOSE.name());
                add(new TiePanel(navigator), Screen.TIE.name());
            }
        };

        add(deck);
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Navigator && arg instanceof Screen screen)
            ((CardLayout) deck.getLayout()).show(deck, screen.name());
    }
}