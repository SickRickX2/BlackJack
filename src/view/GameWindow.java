package view;

import model.AudioManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * The GameWindow class represents the main window of the BlackJack game.
 * It initializes the game window, sets up the deck panel, and handles screen transitions.
 */
public class GameWindow extends JFrame implements Observer {
    private JPanel deck;
    private PlayPanel playPanel;

    /**
     * Constructs a GameWindow and initializes its components.
     */
    public GameWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initDeck();
        initWindow();
    }

    /**
     * Initializes the main window properties.
     */
    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BlackJack");
        setIcon();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Sets the window icon.
     */
    private void setIcon() {
        Image icon = new ImageIcon("res/images/Jicon.png").getImage();
        try {
            Taskbar.getTaskbar().setIconImage(icon);
        } catch (Exception e) {
            // Handle exception if setting the taskbar icon fails
        }
        setIconImage(icon);
    }

    /**
     * Initializes the deck panel and adds the different game screens to it.
     */
    private void initDeck() {
        Navigator navigator = new Navigator();
        navigator.addObserver(this);
        AudioManager.getInstance().playMenuMusic();
        playPanel = new PlayPanel(navigator);
        deck = new JPanel(new CardLayout()) {
            {
                add(new StartPanel(navigator), Screen.START.name());
                add(new ProfileSelectionPanel(navigator), Screen.PROFILE_SELECTION.name());
                add(playPanel, Screen.PLAY.name());
                add(new WinPanel(navigator), Screen.WIN.name());
                add(new LosePanel(), Screen.LOSE.name());
                add(new TiePanel(), Screen.TIE.name());
            }
        };

        for (Component comp : deck.getComponents()) {
            comp.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    if (comp instanceof LosePanel) {
                        AudioManager.getInstance().playLoseMusic();
                    } else if (comp instanceof WinPanel) {
                        AudioManager.getInstance().playWinMusic();
                    } else if (comp instanceof TiePanel) {
                        AudioManager.getInstance().playTieMusic();
                    } else if (comp instanceof StartPanel) {
                        AudioManager.getInstance().playMenuMusic();
                    } else if (comp instanceof PlayPanel) {
                        AudioManager.getInstance().playGameMusic();
                    }
                }
            });
        }

        add(deck);
    }

    /**
     * Returns the PlayPanel instance.
     *
     * @return the PlayPanel instance
     */
    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    /**
     * Updates the displayed screen based on the observed changes.
     *
     * @param o   the observable object
     * @param arg an argument passed to the notifyObservers method
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Navigator && arg instanceof Screen screen)
            ((CardLayout) deck.getLayout()).show(deck, screen.name());
    }
}