package view;

import controller.ProfileSelectionPanelController;
import model.profiles.ProfileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The ProfileSelectionPanel class represents the panel for selecting user profiles.
 * It provides UI components to navigate through profiles and select the desired one.
 */
public class ProfileSelectionPanel extends JPanel {
    private ProfileSelectionPanelController controller;
    private Navigator navigator;
    private JButton bot0Button;
    private JButton bot1Button;
    private JButton bot2Button;
    private int profileIndex = 0;
    private JLabel profileLabel = new JLabel(ProfileManager.getInstance().getProfile(0).getUsername());
    private JLabel avatarLabel = new JLabel();
    private ArrayList<ImageIcon> avatars;

    /**
     * Constructs a ProfileSelectionPanel with the specified navigator.
     *
     * @param navigator the Navigator object used for navigation between screens
     */
    public ProfileSelectionPanel(Navigator navigator) {
        this.navigator = navigator;
        this.controller = new ProfileSelectionPanelController(navigator);

        setPanelSize();
        setLayout(null);
        initAvatars();
        createTitle();
        initAvatars();
        createPlayButton();
        createQuitButton();
        createBot0Button();
        createBot1Button();
        createBot2Button();
        createRightArrow();
        createLeftArrow();
        cpuTitle();
        printProfile();
    }

    /**
     * Sets the size of the panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(WindowDimensions.WIDTH, WindowDimensions.HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Paints the component with a custom background and grid lines.
     *
     * @param g the Graphics object used for painting
     */
    protected void paintComponent(Graphics g) {
        setPanelSize();
        super.paintComponent(g);
        int density = 50;
        g.setColor(new Color(14, 125, 125));
        for (int x = 0; x <= getWidth() + getHeight(); x += density) g.drawLine(x, 0, 0, x);
        this.setBackground(Color.BLACK);
    }

    /**
     * Creates and adds the play button to the panel.
     */
    private void createPlayButton() {
        JButton startButton = new JButton("PLAY");
        startButton.addActionListener(e -> { controller.onPlayButtonClicked(profileIndex); });
        startButton.setBounds(500, 500, 200, 50);
        startButton.setBackground(new Color(14, 125, 125));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        startButton.setFocusPainted(false);
        add(startButton);
    }

    /**
     * Creates and adds the CPU title label to the panel.
     */
    private void cpuTitle() {
        JLabel cpuLabel = new JLabel("   CPU Number");
        cpuLabel.setBounds(480, 670, 245, 50);
        cpuLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        cpuLabel.setForeground(Color.WHITE);
        cpuLabel.setBackground(Color.GRAY);
        cpuLabel.setOpaque(true);
        add(cpuLabel);
    }

    /**
     * Creates and adds the bot0 button to the panel.
     */
    private void createBot0Button() {
        JButton bot0Button = new JButton("0");
        bot0Button.addActionListener(e -> controller.onCreateBot0ButtonClicked(this, bot0Button));
        bot0Button.setBounds(480, 720, 82, 50);
        bot0Button.setBackground(new Color(14, 125, 125));
        bot0Button.setForeground(Color.BLACK);
        bot0Button.setFont(new Font("Tahoma", Font.BOLD, 30));
        bot0Button.setFocusPainted(false);
        add(bot0Button);
        this.bot0Button = bot0Button;
    }

    /**
     * Creates and adds the bot1 button to the panel.
     */
    private void createBot1Button() {
        JButton bot1Button = new JButton("1");
        bot1Button.addActionListener(e -> controller.onCreateBot1ButtonClicked(this, bot1Button));
        bot1Button.setBounds(561, 720, 82, 50);
        bot1Button.setBackground(new Color(14, 125, 125));
        bot1Button.setForeground(Color.WHITE);
        bot1Button.setFont(new Font("Tahoma", Font.BOLD, 30));
        bot1Button.setFocusPainted(false);
        add(bot1Button);
        this.bot1Button = bot1Button;
    }

    /**
     * Creates and adds the bot2 button to the panel.
     */
    private void createBot2Button() {
        JButton bot2Button = new JButton("2");
        bot2Button.addActionListener(e -> controller.onCreateBot2ButtonClicked(this, bot2Button));
        bot2Button.setBounds(641, 720, 83, 50);
        bot2Button.setBackground(new Color(14, 125, 125));
        bot2Button.setForeground(Color.WHITE);
        bot2Button.setFont(new Font("Tahoma", Font.BOLD, 30));
        bot2Button.setFocusPainted(false);
        add(bot2Button);
        this.bot2Button = bot2Button;
    }

    /**
     * Resets the foreground color of bot buttons to white.
     */
    public void resetBotButtons() {
        bot0Button.setForeground(Color.WHITE);
        bot1Button.setForeground(Color.WHITE);
        bot2Button.setForeground(Color.WHITE);
    }

    /**
     * Creates and adds the quit button to the panel.
     */
    private void createQuitButton() {
        JButton quitButton = new JButton("MENU");
        quitButton.addActionListener(e -> navigator.navigate(Screen.START));
        quitButton.setBounds(500, 600, 200, 50);
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(new Color(14, 125, 125));
        quitButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        quitButton.setFocusPainted(false);
        add(quitButton);
    }

    /**
     * Creates and adds the title label to the panel.
     */
    private void createTitle() {
        JLabel titleLabel = new JLabel("Profile Selection");
        titleLabel.setBounds(470, 100, 300, 50);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);
    }

    /**
     * Creates and adds the right arrow button to the panel.
     */
    private void createRightArrow() {
        JButton rightArrow = new JButton(">");
        rightArrow.addActionListener(e -> {
            profileIndex++;
            if (profileIndex >= ProfileManager.getInstance().getProfilesSize()) {
                profileIndex = 0;
            }
            printProfile();
        });
        rightArrow.setBounds(800, 400, 80, 50);
        rightArrow.setFont(new Font("Tahoma", Font.BOLD, 30));
        rightArrow.setForeground(new Color(14, 125, 125));
        rightArrow.setBackground(Color.WHITE);
        rightArrow.setFocusPainted(false);
        add(rightArrow);
    }

    /**
     * Creates and adds the left arrow button to the panel.
     */
    private void createLeftArrow() {
        JButton leftArrow = new JButton("<");
        leftArrow.addActionListener(e -> {
            profileIndex--;
            if (profileIndex < 0) {
                profileIndex = ProfileManager.getInstance().getProfilesSize() - 1;
            }
            printProfile();
        });
        leftArrow.setBounds(320, 400, 80, 50);
        leftArrow.setFont(new Font("Tahoma", Font.BOLD, 30));
        leftArrow.setForeground(new Color(14, 125, 125));
        leftArrow.setBackground(Color.WHITE);
        leftArrow.setFocusPainted(false);
        add(leftArrow);
    }

    /**
     * Prints the current profile's username and avatar on the panel.
     */
    private void printProfile() {
        profileLabel.setText(ProfileManager.getInstance().getProfile(profileIndex).getUsername());
        profileLabel.setBounds(550, 250, 300, 50);
        profileLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        profileLabel.setForeground(Color.WHITE);
        add(profileLabel);

        ImageIcon avatarIm = new ImageIcon("res/images/AVATARS/" + ProfileManager.getInstance().getProfile(profileIndex).getAvatarID() + ".png");
        avatarLabel.setIcon(avatarIm);
        avatarLabel.setBounds(535, 300, avatarIm.getIconWidth(), avatarIm.getIconHeight());
        add(avatarLabel);
    }

    /**
     * Initializes the list of avatar images.
     */
    private void initAvatars() {
        avatars = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            avatars.add(new ImageIcon("res/images/AVATARS" + i + ".png"));
        }
    }
}