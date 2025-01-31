package model;

import java.util.ArrayList;

/**
 * The EntityModel class represents a generic entity in the game.
 * It provides common properties and methods for entities such as players, bots, and the dealer.
 */
public abstract class EntityModel {
    protected int sum = 0;
    protected int aceCount = 0;
    protected boolean blackjack = false;
    protected boolean busts = false;

    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();

    /**
     * Constructs an EntityModel and initializes the hand with two cards.
     * Checks for blackjack after drawing the initial cards.
     */
    protected EntityModel() {
        for (int i = 0; i < 2; i++) {
            drawCard();
        }
        blackjackCheck();
    }

    /**
     * Draws a card from the deck and adds it to the hand.
     * Updates the sum and ace count, and adjusts the value of aces if necessary.
     */
    protected void drawCard() {
        CardModel card = DeckModel.getInstance().getCard();
        sum += card.getCardValue();
        aceCount += card.isAce() ? 1 : 0;
        hand.add(card);
        changeAceValue();
    }

    /**
     * The hit behavior for the entity.
     * Draws a card, adjusts the value of aces, and checks for busts.
     */
    public void hit() {
        drawCard();
        changeAceValue();
        bustsCheck();
    }

    /**
     * The stay behavior for the entity.
     * Ends the entity's turn.
     */
    public void stay() {
        TurnManager.getInstance().nextTurn();
    }

    /**
     * Returns the hand of the entity.
     *
     * @return the hand of the entity
     */
    public ArrayList<CardModel> getHand() {
        return hand;
    }

    /**
     * Returns the sum of the card values in the entity's hand.
     *
     * @return the sum of the card values in the entity's hand
     */
    public int getSum() {
        return sum;
    }

    /**
     * Adjusts the value of aces in the hand if the sum exceeds 21.
     * Changes the value of an ace from 11 to 1 if necessary.
     */
    public void changeAceValue() {
        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
    }

    /**
     * Checks if the entity has a blackjack.
     * A blackjack is when the sum is 21 with exactly two cards.
     */
    public void blackjackCheck() {
        if (sum == 21 && hand.size() == 2) {
            blackjack = true;
        }
    }

    /**
     * Ends the entity's turn.
     */
    public void endTurn() {
        TurnManager.getInstance().nextTurn();
    }

    /**
     * Abstract method to check if the entity has busted.
     * Must be implemented by subclasses.
     */
    public abstract void bustsCheck();
}