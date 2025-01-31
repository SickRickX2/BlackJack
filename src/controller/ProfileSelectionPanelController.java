package controller;

import model.TurnManager;
import model.profiles.ProfileManager;
import view.Navigator;
import view.ProfileSelectionPanel;
import view.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * The ProfileSelectionPanelController class handles the logic for the ProfileSelectionPanel.
 * It manages the interactions and navigation related to profile selection.
 */
public class ProfileSelectionPanelController {
    private Navigator navigator;

    /**
     * Constructs a ProfileSelectionPanelController with the specified navigator.
     *
     * @param navigator the Navigator object used for navigation between screens
     */
    public ProfileSelectionPanelController(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Handles the event when the play button is clicked.
     * Selects the profile and navigates to the play screen.
     *
     * @param profileIndex the index of the selected profile
     */
    public void onPlayButtonClicked(int profileIndex) {
        ProfileManager.getInstance().selectProfile(profileIndex);
        navigator.navigate(Screen.PLAY);
        TurnManager.getInstance().createBot();
    }

    /**
     * Handles the event when the bot0 button is clicked.
     * Resets the bot buttons and sets the bot count to 0.
     *
     * @param profileSelectionPanel the ProfileSelectionPanel instance
     * @param bot0Button the button for selecting 0 bots
     */
    public void onCreateBot0ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot0Button) {
        profileSelectionPanel.resetBotButtons();
        bot0Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(0);
    }

    /**
     * Handles the event when the bot1 button is clicked.
     * Resets the bot buttons and sets the bot count to 1.
     *
     * @param profileSelectionPanel the ProfileSelectionPanel instance
     * @param bot1Button the button for selecting 1 bot
     */
    public void onCreateBot1ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot1Button) {
        profileSelectionPanel.resetBotButtons();
        bot1Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(1);
    }

    /**
     * Handles the event when the bot2 button is clicked.
     * Resets the bot buttons and sets the bot count to 2.
     *
     * @param profileSelectionPanel the ProfileSelectionPanel instance
     * @param bot2Button the button for selecting 2 bots
     */
    public void onCreateBot2ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot2Button) {
        profileSelectionPanel.resetBotButtons();
        bot2Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(2);
    }
}