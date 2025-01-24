package model;

import static model.Value.ACE;

public class CardModel {
    private Suit suit;
    private Value value;
    public CardModel(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }
    public Suit getSuit(){
        return suit;
    }
    public Value getValue(){
        return value;
    }
    public int getCardValue(){
        switch (value) {
            case ACE:
                return 11;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            default:
                return 10;
        }
    }
    public String getCardSuit(){
        switch(suit){
            case HEARTS:
                return "-H";
             case DIAMONDS:
                return "-D";
            case CLUBS:
                return "-C";
            case SPADES:
                return "-S";
            default: return null; }
    }


    public boolean isAce() {
        return value == ACE;

    }
}
