package controller;

import model.DealerModel;
import model.DeckModel;
import model.PlayerModel;
import view.GameWindow;

public class Game implements Runnable {


    private static GameWindow gameWindow;
    private Thread gameThread;
    private int FPS_SET = 60;
    private final int UPS_SET = 60;



    public Game() {
        initClasses();
        gameWindow = new GameWindow();
        startGameLoop();

    }
    private void initClasses() {
        DeckModel.getInstance();
        DealerModel.getInstance();
        PlayerModel.getInstance();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();


        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while(true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >= 1) {

                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gameWindow.repaint();

                frames++;
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }


}