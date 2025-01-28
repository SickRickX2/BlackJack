package model;

import java.util.ArrayList;
import java.util.Random;

public class DeckModel {
    private static DeckModel instance = null;
    private ArrayList<CardModel> deck = new ArrayList<CardModel>();
    private Random random = new Random();
    private DeckModel(){
        createDeck();
        shuffleDeck();
    }
    public static DeckModel getInstance(){
        if(instance == null){
            instance = new DeckModel();
        }
        return instance;
    }
    public void createDeck(){
        for(Suit suit : Suit.values()){
            for(Value value : Value.values()){
                deck.add(new CardModel(suit, value));
            }
        }
    }
    public CardModel getCard(){
        return deck.remove(deck.size()-1);
    }
    public void shuffleDeck(){
        for (int i = 0; i < deck.size(); i++){
            int randomIndex = random.nextInt(deck.size());
            CardModel tempCard = deck.get(i);
            CardModel randomCard = deck.get(randomIndex);
            deck.set(i, randomCard);
            deck.set(randomIndex, tempCard);


        }

    }

}
