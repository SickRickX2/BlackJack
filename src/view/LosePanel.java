package view;

import model.Leaderboard;
import model.profiles.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LosePanel extends JPanel {
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


    private void createTitle() {
        ImageIcon title = new ImageIcon("res/images/blackjacktitle.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);
    }

    private void leaderboardTitle() {
        JLabel leaderboard = new JLabel("Leaderboard", SwingConstants.CENTER);
        leaderboard.setFont(new Font("Tahoma", Font.BOLD, 30));
        leaderboard.setForeground(Color.WHITE);
        add(leaderboard);
    }

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
