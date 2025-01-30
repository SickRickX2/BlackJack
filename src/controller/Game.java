package controller;

import model.DealerModel;
import model.DeckModel;
import model.PlayerModel;
import model.TurnManager;
import model.profiles.ProfileManager;
import view.GameWindow;

public class Game implements Runnable {


    private static GameWindow gameWindow;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;


    public Game() {

        gameWindow = new GameWindow();
        initClasses();
        startGameLoop();

    }

    private void initClasses() {
        ProfileManager.getInstance();
        TurnManager.getInstance();
        DeckModel.getInstance();
        DealerModel.getInstance();
        PlayerModel.getInstance();

        TurnManager.getInstance().addObserver(gameWindow.getPlayPanel());
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

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {

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

                frames = 0;
                updates = 0;
            }
        }

    }


}