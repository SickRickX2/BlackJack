package model;

import java.util.ArrayList;
import java.util.Random;

public class DeckModel {
    ArrayList<CardModel> deck = new ArrayList<CardModel>();
    Random random = new Random();
    public DeckModel(){
        createDeck();
        shuffleDeck();
    }
    public void createDeck(){
        for(Suit suit : Suit.values()){
            for(Value value : Value.values()){
                deck.add(new CardModel(suit, value));
            }
        }
    }
    public void shuffleDeck(){
        for (int i = 0; 1 < deck.size(); i++){
            int randomIndex = random.nextInt(deck.size());
            CardModel tempCard = deck.get(i);
            CardModel randomCard = deck.get(randomIndex);
            deck.set(i, randomCard);
            deck.set(randomIndex, tempCard);


        }

    }

}
