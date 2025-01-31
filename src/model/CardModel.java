package model;

import static model.Value.ACE;

/**
 * The CardModel class represents a card in a deck.
 * It contains the suit and value of the card and provides methods to retrieve card properties.
 */
public class CardModel {
    private final Suit suit;
    private final Value value;

    /**
     * Constructs a CardModel with the specified suit and value.
     *
     * @param suit  the suit of the card
     * @param value the value of the card
     */
    public CardModel(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns the value of the card.
     *
     * @return the value of the card
     */
    public Value getValue() {
        return value;
    }

    /**
     * Returns the numerical value of the card.
     * Aces are worth 11, face cards are worth 10, and other cards are worth their face value.
     *
     * @return the numerical value of the card
     */
    public int getCardValue() {
        return switch (value) {
            case ACE -> 11;
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            default -> 10;
        };
    }

    /**
     * Returns the suit of the card as a string.
     * The suit is represented by its first letter (e.g., "-H" for hearts).
     *
     * @return the suit of the card as a string
     */
    public String getCardSuit() {
        return switch (suit) {
            case HEARTS -> "-H";
            case DIAMONDS -> "-D";
            case CLUBS -> "-C";
            case SPADES -> "-S";
        };
    }

    /**
     * Checks if the card is an Ace.
     *
     * @return true if the card is an Ace, false otherwise
     */
    public boolean isAce() {
        return value == ACE;
    }

    /**
     * Returns the file path to the image of the card.
     *
     * @return the file path to the image of the card
     */
    public String getCardImagePath() {
        return "res/images/cards/" + this + ".png";
    }

    /**
     * Returns a string representation of the card.
     * The string consists of the card's value symbol followed by its suit.
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return getValue().getSymbol() + getCardSuit();
    }
}