package model;

/**
 * The PlayerModel class represents the player entity in the game.
 * It extends the EntityModel class and implements specific behaviors for the player.
 */
public class PlayerModel extends EntityModel {
    private static PlayerModel instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Checks for blackjack after drawing the initial cards.
     */
    private PlayerModel() {
        blackjackCheck();
    }

    /**
     * Returns the singleton instance of the PlayerModel.
     *
     * @return the singleton instance of the PlayerModel
     */
    public static PlayerModel getInstance() {
        if (instance == null) {
            instance = new PlayerModel();
        }
        return instance;
    }

    /**
     * Checks if the player has busted. If the player's sum exceeds 21, it sets the busts flag to true and ends the turn.
     */
    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.PLAYER) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;
            }
        }
    }
}