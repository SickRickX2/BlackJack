package view;

import java.util.Observable;

class Navigator extends Observable {
    void navigate(Screen screen) {
        setChanged();
        notifyObservers(screen);
    }
}

enum Screen {
    START, PROFILE_SELECTION, PLAY, WIN, LOSE, TIE
}