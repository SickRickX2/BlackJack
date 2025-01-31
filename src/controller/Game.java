package controller;

import model.DealerModel;
import model.DeckModel;
import model.PlayerModel;
import model.TurnManager;
import model.profiles.ProfileManager;
import view.GameWindow;

/**
 * The Game class represents the main game loop and initialization for the Blackjack game.
 * It implements the Runnable interface to run the game loop in a separate thread.
 */
public class Game implements Runnable {

    private static GameWindow gameWindow;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;

    /**
     * Constructs a new Game instance.
     * Initializes the game window, game components, and starts the game loop.
     */
    public Game() {
        gameWindow = new GameWindow();
        initClasses();
        startGameLoop();
    }

    /**
     * Initializes the game components and sets up the necessary observers.
     */
    private void initClasses() {
        ProfileManager.getInstance();
        TurnManager.getInstance();
        DeckModel.getInstance();
        DealerModel.getInstance();
        PlayerModel.getInstance();

        TurnManager.getInstance().addObserver(gameWindow.getPlayPanel());
    }

    /**
     * Starts the game loop in a new thread.
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop, which updates and repaints the game at a fixed rate.
     * This method is called when the thread is started.
     */
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