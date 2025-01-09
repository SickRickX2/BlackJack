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



}
