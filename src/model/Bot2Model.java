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
        Timer timer = new Timer(1000, new ActionListener() { // 1 second delay
            @Override
            public void actionPerformed(ActionEvent e) {
                while (sum < 17) {
                    Bot2Model.super.hit();
                }
            }
        });
        timer.setRepeats(false);
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
