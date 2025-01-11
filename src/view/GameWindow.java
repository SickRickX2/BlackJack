package view;


import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GameWindow extends JFrame implements Observer {
    private JPanel deck;
    private StartPanel startPanel;
    private ProfileSelectionPanel profileSelectionPanel;
    private PlayPanel playPanel;
    private WinPanel winPanel;
    private LosePanel losePanel;


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
        deck = new JPanel(new CardLayout()) {
            {
                add(new StartPanel(navigator), Screen.START.name());
                add(new ProfileSelectionPanel(navigator), Screen.PROFILE_SELECTION.name());
                add(new PlayPanel(navigator), Screen.PLAY.name());
                add(new WinPanel(), Screen.WIN.name());
                add(new LosePanel(), Screen.LOSE.name());
            }
        };

    add(deck);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Navigator && arg instanceof Screen screen)
            ((CardLayout) deck.getLayout()).show(deck, screen.name());
    }
}