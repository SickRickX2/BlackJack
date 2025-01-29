package model;

import java.util.Observable;

public class TurnManager extends Observable {
    private static TurnManager instance = null;
    private Turn currentTurn = Turn.PLAYER;
    private int botCount = 0;

    private TurnManager() {
    }

    public void createBot() {
        switch(botCount) {
            case 1:
                Bot1Model.getInstance();
                break;
            case 2:
                Bot1Model.getInstance();
                Bot2Model.getInstance();
                break;
            default:
                break;

        }
    }

    public static TurnManager getInstance() {
        if (instance == null) {
            instance = new TurnManager();
        }
        return instance;
    }

    public void nextTurn() {
        if (currentTurn == Turn.PLAYER && botCount >= 1) {
            currentTurn = Turn.BOT1;
            setChanged();
            notifyObservers();
            Bot1Model.getInstance().hit();
            System.out.println("Bot1: " + Bot1Model.getInstance().getSum());
        } else if (currentTurn == Turn.PLAYER && botCount == 0) {
            currentTurn = Turn.DEALER;
            setChanged();
            notifyObservers();
            DealerModel.getInstance().hit();
        }

        if (currentTurn == Turn.BOT1 && botCount >= 2) {
            currentTurn = Turn.BOT2;
            Bot2Model.getInstance().hit();
        } else if (currentTurn == Turn.BOT1 && botCount == 1) {
            currentTurn = Turn.DEALER;
            DealerModel.getInstance().hit();
        }

        if (currentTurn == Turn.BOT2) {
            currentTurn = Turn.DEALER;
            DealerModel.getInstance().hit();
        }

        if (currentTurn == Turn.DEALER) {
            currentTurn = Turn.END;
        }
    }

    public Turn getCurrentTurn() {
        return currentTurn;
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
