package model;

import java.util.Observable;

public class TurnManager extends Observable {
    private static TurnManager instance = null;
    private Turn currentTurn = Turn.PLAYER;
    private int botCount = 0;

    private TurnManager() {
    }

    public static TurnManager getInstance() {
        if (instance == null) {
            instance = new TurnManager();
        }
        return instance;
    }
    public void nextTurn() {

        if (currentTurn == Turn.PLAYER && botCount >= 1)
        {
            currentTurn = Turn.BOT1;
            setChanged();
            notifyObservers();

        }
        else if (currentTurn == Turn.PLAYER && botCount == 0){
            currentTurn = Turn.DEALER;
            setChanged();
            notifyObservers();
        }

        if (currentTurn == Turn.BOT1 && botCount >= 2)
            currentTurn = Turn.BOT2;
        else if (currentTurn == Turn.BOT1 && botCount == 1)
            currentTurn = Turn.DEALER;

        if (currentTurn == Turn.BOT2)
            currentTurn = Turn.DEALER;

        if (currentTurn == Turn.DEALER)
            currentTurn = Turn.END;




    }
    public enum Turn {
        PLAYER, DEALER, BOT1, BOT2, END
    }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

}
