package view;

import java.util.Observable;

/**
 * The Navigator class is responsible for managing screen navigation in the application.
 * It extends Observable to notify observers about screen changes.
 */
public class Navigator extends Observable {

    /**
     * Navigates to the specified screen.
     * This method sets the changed state and notifies all observers with the new screen.
     *
     * @param screen the Screen to navigate to
     */
    public void navigate(Screen screen) {
        setChanged();
        notifyObservers(screen);
    }
}