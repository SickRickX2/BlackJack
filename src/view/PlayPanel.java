package view;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class PlayPanel extends JPanel implements Observer {
    private Navigator navigator;

    public PlayPanel(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
