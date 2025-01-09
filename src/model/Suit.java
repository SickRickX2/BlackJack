package model;

public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES;
    public static Suit getSuits(int i){
        return Suit.values()[i];
    }
    public static int getSuitsLength(){
        return Suit.values().length;
    }

}
