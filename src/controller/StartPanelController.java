package controller;

import view.Navigator;
import view.Screen;

public class StartPanelController {
    private Navigator navigator;

    public StartPanelController(Navigator navigator) {
        this.navigator = navigator;
    }

    public void onStartButtonClicked() {
        navigator.navigate(Screen.PROFILE_SELECTION);
    }

    public void onQuitButtonClicked() {
        System.exit(0);
    }
}