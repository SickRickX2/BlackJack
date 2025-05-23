package model;

import model.profiles.ProfileManager;

import java.util.Observable;

/**
 * The TurnManager class manages the turns in the game.
 * It follows the singleton pattern to ensure only one instance exists.
 * It extends Observable to notify observers of turn changes.
 */
public class TurnManager extends Observable {
    private static TurnManager instance = null;
    private Turn currentTurn = Turn.PLAYER;
    private int botCount = 0;

    /**
     * Private constructor to prevent instantiation.
     */
    private TurnManager() {
    }

    /**
     * Creates bots based on the current bot count.
     * Initializes the appropriate bot models.
     */
    public void createBot() {
        switch (botCount) {
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

    /**
     * Returns the singleton instance of the TurnManager.
     *
     * @return the singleton instance of the TurnManager
     */
    public static TurnManager getInstance() {
        if (instance == null) {
            instance = new TurnManager();
        }
        return instance;
    }

    /**
     * Advances to the next turn based on the current turn and bot count.
     * Notifies observers of turn changes and game results.
     */
    public void nextTurn() {
        if (currentTurn == Turn.PLAYER && botCount >= 1) {
            currentTurn = Turn.BOT1;
            setChanged();
            notifyObservers();
            Bot1Model.getInstance().hit();
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
        if (currentTurn == Turn.END) {
            String result = checkGameResult();
            setChanged();
            notifyObservers(result);
        }
    }

    /**
     * Returns the current turn.
     *
     * @return the current turn
     */
    public Turn getCurrentTurn() {
        return currentTurn;
    }

    /**
     * The Turn enum represents the possible turns in the game.
     */
    public enum Turn {
        PLAYER, DEALER, BOT1, BOT2, END
    }

    /**
     * Returns the current bot count.
     *
     * @return the current bot count
     */
    public int getBotCount() {
        return botCount;
    }

    /**
     * Sets the bot count.
     *
     * @param botCount the new bot count
     */
    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

    /**
     * Checks the game result based on the sums and blackjack status of the player and dealer.
     * Updates the profile manager with the game result.
     *
     * @return the game result as a string
     */
    private String checkGameResult() {
        int playerSum = PlayerModel.getInstance().getSum();
        int dealerSum = DealerModel.getInstance().getSum();
        boolean playerBlackjack = PlayerModel.getInstance().blackjack;
        boolean dealerBlackjack = DealerModel.getInstance().blackjack;

        if (playerBlackjack && dealerBlackjack) {
            ProfileManager.getInstance().gameCounter();
            return "TIE";
        } else if (playerBlackjack && !dealerBlackjack) {
            ProfileManager.getInstance().winCounter();
            ProfileManager.getInstance().gameCounter();
            return "WIN";
        } else if (dealerBlackjack && !playerBlackjack) {
            ProfileManager.getInstance().gameCounter();
            return "LOSE";
        } else if (playerSum > 21) {
            ProfileManager.getInstance().gameCounter();
            return "LOSE";
        } else if (dealerSum > 21 && playerSum <= 21) {
            ProfileManager.getInstance().winCounter();
            ProfileManager.getInstance().gameCounter();
            return "WIN";
        } else if (playerSum == 21 && dealerSum == 21) {
            ProfileManager.getInstance().gameCounter();
            return "TIE";
        } else if (playerSum > dealerSum && playerSum <= 21) {
            ProfileManager.getInstance().winCounter();
            ProfileManager.getInstance().gameCounter();
            return "WIN";
        } else if (playerSum < dealerSum && dealerSum <= 21) {
            ProfileManager.getInstance().gameCounter();
            return "LOSE";
        } else {
            ProfileManager.getInstance().gameCounter();
            return "TIE";
        }
    }
}