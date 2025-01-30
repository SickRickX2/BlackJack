package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bot1Model extends EntityModel {
    private static Bot1Model instance = null;
    private Bot1Model() {
    }

    public static Bot1Model getInstance() {
        if (instance == null) {
            instance = new Bot1Model();
        }
        return instance;
    }
    @Override
    public void hit() {
        Timer timer = new Timer(200, new ActionListener() { // 1 second delay
            @Override
            public void actionPerformed(ActionEvent e) {
                while (sum < 17) {
                    Bot1Model.super.hit();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.BOT1) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;
            }
        }

    }
}
