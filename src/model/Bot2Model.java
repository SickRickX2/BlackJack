package model;

/**
 * The Bot2Model class represents a bot entity in the game.
 * It extends the EntityModel class and implements specific behaviors for the bot.
 */
public class Bot2Model extends EntityModel {
    private static Bot2Model instance = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private Bot2Model() {
    }

    /**
     * The bot's hit behavior. The bot will continue to hit until its sum is at least 17.
     */
    @Override
    public void hit() {
        while (sum < 17) {
            Bot2Model.super.hit();
        }
    }

    /**
     * Returns the singleton instance of the Bot2Model.
     *
     * @return the singleton instance of the Bot2Model
     */
    public static Bot2Model getInstance() {
        if (instance == null) {
            instance = new Bot2Model();
        }
        return instance;
    }

    /**
     * Checks if the bot has busted. If the bot's sum exceeds 21, it sets the busts flag to true and ends the turn.
     */
    @Override
    public void bustsCheck() {
        // Implementation needed
    }
}