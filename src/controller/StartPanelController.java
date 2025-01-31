package controller;

import view.Navigator;
import view.Screen;

/**
 * The StartPanelController class handles the logic for the StartPanel.
 * It manages the interactions and navigation from the start screen.
 */
public class StartPanelController {
    private Navigator navigator;

    /**
     * Constructs a StartPanelController with the specified navigator.
     *
     * @param navigator the Navigator object used for navigation between screens
     */
    public StartPanelController(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Handles the event when the start button is clicked.
     * Navigates to the profile selection screen.
     */
    public void onStartButtonClicked() {
        navigator.navigate(Screen.PROFILE_SELECTION);
    }

    /**
     * Handles the event when the quit button is clicked.
     * Exits the application.
     */
    public void onQuitButtonClicked() {
        System.exit(0);
    }
}