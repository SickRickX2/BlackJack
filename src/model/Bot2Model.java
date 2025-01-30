package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bot2Model extends EntityModel {
    private static Bot2Model instance = null;
    private Bot2Model() {
    }
    @Override
    public void hit() {
        Timer timer = new Timer(200, new ActionListener() { // 1 second delay
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sum < 17) {
                    Bot2Model.super.hit();
                } else {
                    ((Timer) e.getSource()).stop(); // Stop the timer when the condition is met
                }
            }
        });
        timer.setRepeats(false); // Ensure the timer repeats
        timer.start();
    }
    public static Bot2Model getInstance() {
        if (instance == null) {
            instance = new Bot2Model();
        }
        return instance;
    }
    @Override
    public void bustsCheck() {

    }
}
