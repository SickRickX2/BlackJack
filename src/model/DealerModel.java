package model;

/**
 * The DealerModel class represents the dealer entity in the game.
 * It extends the EntityModel class and implements specific behaviors for the dealer.
 */
public class DealerModel extends EntityModel {
    private static DealerModel instance = null;
    private boolean hidden = true;

    /**
     * Private constructor to prevent instantiation.
     */
    private DealerModel() {
    }

    /**
     * Returns the singleton instance of the DealerModel.
     *
     * @return the singleton instance of the DealerModel
     */
    public static DealerModel getInstance() {
        if (instance == null) {
            instance = new DealerModel();
        }
        return instance;
    }

    /**
     * Checks if the dealer's hand is hidden.
     * The dealer's hand is revealed if it is the dealer's turn or the game has ended.
     *
     * @return true if the dealer's hand is hidden, false otherwise
     */
    public boolean isHidden() {
        if (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.DEALER || TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.END) {
            hidden = false;
        }
        return hidden;
    }

    /**
     * The dealer's hit behavior. The dealer will hit based on the player's and bots' sums.
     * The dealer stops hitting if the player's or bots' sums are less than the dealer's sum or if they bust.
     */
    @Override
    public void hit() {
        if (TurnManager.getInstance().getBotCount() == 0) {
            if (PlayerModel.getInstance().getSum() < sum) {
                endTurn();
            } else if (PlayerModel.getInstance().busts) {
                endTurn();
            } else while (PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) {
                DealerModel.super.hit();
            }
        } else if (TurnManager.getInstance().getBotCount() == 1) {
            if (PlayerModel.getInstance().getSum() < sum && Bot1Model.getInstance().getSum() < sum) {
                endTurn();
            } else if (PlayerModel.getInstance().busts && Bot1Model.getInstance().busts) {
                endTurn();
            } else
                while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum)) {
                    DealerModel.super.hit();
                }
        } else if (TurnManager.getInstance().getBotCount() == 2) {
            if (PlayerModel.getInstance().getSum() < sum && Bot1Model.getInstance().getSum() < sum && Bot2Model.getInstance().getSum() < sum) {
                endTurn();
            } else if (PlayerModel.getInstance().busts && Bot1Model.getInstance().busts && Bot2Model.getInstance().busts) {
                endTurn();
            } else
                while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum) || (Bot2Model.getInstance().getSum() <= 21 && Bot2Model.getInstance().getSum() > sum)) {
                    DealerModel.super.hit();
                }
        }
    }

    /**
     * Checks if the dealer has busted. If the dealer's sum exceeds 21, it sets the busts flag to true and ends the turn.
     */
    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.DEALER) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;
            }
        }
    }
}