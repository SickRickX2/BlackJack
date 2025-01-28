package controller;

import controller.gamestates.Gamestate;
import controller.gamestates.PlayingController;
import model.DealerModel;
import model.DeckModel;
import model.GameModel;
import model.PlayerModel;
import model.gamestates.PlayingModel;
import view.GameWindow;
import view.StartPanel;
import view.gamestates.PlayingView;

import java.awt.*;

public class Game implements Runnable {


    private static GameWindow gameWindow;
    protected static StartPanel startPanel;
    private static GameModel gameModel;
    private Thread gameThread;
    private int FPS_SET = 120;
    private final int UPS_SET = 200;

    private PlayingModel playingModel;
    private PlayingController playingController;
    private PlayingView playingView;
    private DeckModel deckModel;
    private DealerModel dealerModel;
    private PlayerModel playerModel;



    public Game() {
        initClasses();
        gameWindow = new GameWindow();

    }
    private void initClasses() {
        playingModel = new PlayingModel();
        playingController = new PlayingController(playingModel);
        playingView = new PlayingView(playingModel);
        deckModel = DeckModel.getInstance();
        dealerModel =  DealerModel.getInstance();
        playerModel = new PlayerModel();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(){
        switch(Gamestate.state){
            case PLAYING:
                playingModel.update();
                playingView.update();
                break;
            case MENU:
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }


    }
    public void render(Graphics g){
        switch(Gamestate.state){
            case PLAYING:
                playingView.draw(g);
                break;
            case MENU:
                break;
            default:
                break;
        }
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        long previousTime = System.nanoTime();


        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while(true) {
            now = System.nanoTime();
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                startPanel.repaint();
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

    public void windowFocusLost() {
        if(Gamestate.state == Gamestate.PLAYING){
            //playingModel.getPlayer().resetDirBooleans();
        }
    }

    public PlayingModel getPlayingModel(){
        return playingModel;
    }
    public PlayingController getPlayingController() {
        return playingController;
    }
    public DealerModel getDealerModel(){
        return dealerModel;
    }


}