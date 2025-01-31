package controller;

import model.TurnManager;
import model.profiles.ProfileManager;
import view.Navigator;
import view.ProfileSelectionPanel;
import view.Screen;

import javax.swing.*;
import java.awt.*;

public class ProfileSelectionPanelController {
    private Navigator navigator;

    public ProfileSelectionPanelController(Navigator navigator) {
        this.navigator = navigator;
    }


    public void onPlayButtonClicked(int profileIndex) {
        ProfileManager.getInstance().selectProfile(profileIndex);
        navigator.navigate(Screen.PLAY);
        TurnManager.getInstance().createBot();
    }

    public void onCreateBot0ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot0Button) {
        profileSelectionPanel.resetBotButtons();
        bot0Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(0);
    }

    public void onCreateBot1ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot1Button) {
        profileSelectionPanel.resetBotButtons();
        bot1Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(1);
    }

    public void onCreateBot2ButtonClicked(ProfileSelectionPanel profileSelectionPanel, JButton bot2Button) {
        profileSelectionPanel.resetBotButtons();
        bot2Button.setForeground(Color.BLACK);
        TurnManager.getInstance().setBotCount(2);
    }
}
