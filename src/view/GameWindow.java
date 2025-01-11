package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(StartPanel startPanel) {

        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = new ImageIcon("res/images/Jicon.png").getImage();
        try{
            Taskbar.getTaskbar().setIconImage(icon);
        }catch (Exception e){}
        jframe.setIconImage(icon);
        jframe.add(startPanel);
        jframe.setTitle("BlackJack");
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setVisible(true);
       jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                startPanel.getGame().windowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }
}