package model;

import java.util.ArrayList;

public class DeckModel {
    ArrayList<CardModel> deck = new ArrayList<CardModel>();
    public void createDeck(){
        for(Suit suit : Suit.values()){
            for(Value value : Value.values()){
                deck.add(new CardModel(suit, value));
            }
        }
    }
}
