package controller;

import model.PlayerModel;
import view.Navigator;

public class PlayPanelController {
    private Navigator navigator;

    public PlayPanelController(Navigator navigator) {
        this.navigator = navigator;
    }

    public void onQuitButtonClicked() {
        System.exit(0);
    }

    public void onStayButtonClicked() {
        PlayerModel.getInstance().stay();
    }

    public void onHitButtonClicked() {
        PlayerModel.getInstance().hit();
    }
}
