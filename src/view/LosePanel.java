package view;

import model.Leaderboard;
import model.profiles.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The LosePanel class represents the panel displayed when the player loses the game.
 * It shows a message indicating the loss and displays the leaderboard.
 */
public class LosePanel extends JPanel {

    /**
     * Constructs a LosePanel and initializes its components.
     */
    public LosePanel() {
        setPreferredSize(new Dimension(WindowDimensions.WIDTH, WindowDimensions.HEIGHT));
        setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("You lose!", SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 60));
        label.setForeground(Color.red);
        add(label);
        createTitle();
        leaderboardTitle();
        printLeaderboard();
        setBackground(new Color(102, 0, 153));
    }

    /**
     * Creates and adds the title image to the panel.
     */
    private void createTitle() {
        ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);
    }

    /**
     * Creates and adds the leaderboard title label to the panel.
     */
    private void leaderboardTitle() {
        JLabel leaderboard = new JLabel("Leaderboard", SwingConstants.CENTER);
        leaderboard.setFont(new Font("Tahoma", Font.BOLD, 30));
        leaderboard.setForeground(Color.WHITE);
        add(leaderboard);
    }

    /**
     * Prints the top profiles from the leaderboard on the panel.
     */
    private void printLeaderboard() {
        List<Profile> topProfiles = Leaderboard.getInstance().getTopProfiles(10);
        for (Profile profile : topProfiles) {
            int wins = profile.getWins();
            int matches = profile.getNumberOfPlayedGames();
            JLabel profileLabel = new JLabel(profile.getUsername() + ": " + wins + " wins, " + matches + " matches");
            profileLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            profileLabel.setForeground(Color.WHITE);
            add(profileLabel);
        }
    }
}