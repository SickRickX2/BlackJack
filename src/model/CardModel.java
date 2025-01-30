package model;

import static model.Value.ACE;

public class CardModel {
    private Suit suit;
    private Value value;

    public CardModel(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

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

    public String getCardSuit() {
        return switch (suit) {
            case HEARTS -> "-H";
            case DIAMONDS -> "-D";
            case CLUBS -> "-C";
            case SPADES -> "-S";
        };
    }


    public boolean isAce() {
        return value == ACE;

    }

    public String getCardImagePath() {
        return "res/images/cards/" + this + ".png";
    }

    @Override
    public String toString() {
        return getValue().getSymbol() + getCardSuit();
    }

}
