package model;

/**
 * The Bot1Model class represents a bot entity in the game.
 * It extends the EntityModel class and implements specific behaviors for the bot.
 */
public class Bot1Model extends EntityModel {
    private static Bot1Model instance = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private Bot1Model() {
    }

    /**
     * Returns the singleton instance of the Bot1Model.
     *
     * @return the singleton instance of the Bot1Model
     */
    public static Bot1Model getInstance() {
        if (instance == null) {
            instance = new Bot1Model();
        }
        return instance;
    }

    /**
     * The bot's hit behavior. The bot will continue to hit until its sum is at least 17.
     */
    @Override
    public void hit() {
        while (sum < 17) {
            Bot1Model.super.hit();
        }
    }

    /**
     * Checks if the bot has busted. If the bot's sum exceeds 21, it sets the busts flag to true and ends the turn.
     */
    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.BOT1) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;
            }
        }
    }
}