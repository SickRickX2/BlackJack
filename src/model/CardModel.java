package model;

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
        if (value == Value.ACE){
            return 11;
        } else if (value == Value.TWO){
            return 2;
        } else if (value == Value.THREE){
            return 3;
        } else if (value == Value.FOUR){
            return 4;
        } else if (value == Value.FIVE){
            return 5;
        } else if (value == Value.SIX){
            return 6;
        } else if (value == Value.SEVEN){
            return 7;
        } else if (value == Value.EIGHT){
            return 8;
        } else if (value == Value.NINE){
            return 9;
        } else {
            return 10;
        }
    }


    public boolean isAce() {
        return value == Value.ACE;

    }
}
