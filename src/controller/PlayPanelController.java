package controller;

import model.PlayerModel;
import view.Navigator;

/**
 * The PlayPanelController class handles user interactions with the play panel.
 * It interacts with the PlayerModel and Navigator to perform actions based on user input.
 */
public class PlayPanelController {
    private Navigator navigator;

    /**
     * Constructs a PlayPanelController with the specified Navigator.
     *
     * @param navigator the Navigator used for navigation
     */
    public PlayPanelController(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Handles the action when the quit button is clicked.
     * Exits the application.
     */
    public void onQuitButtonClicked() {
        System.exit(0);
    }

    /**
     * Handles the action when the stay button is clicked.
     * Calls the stay method on the PlayerModel.
     */
    public void onStayButtonClicked() {
        PlayerModel.getInstance().stay();
    }

    /**
     * Handles the action when the hit button is clicked.
     * Calls the hit method on the PlayerModel.
     */
    public void onHitButtonClicked() {
        PlayerModel.getInstance().hit();
    }
}