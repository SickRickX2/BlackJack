package model;

import java.util.ArrayList;

public abstract class EntityModel {
    protected int sum = 0;
    protected int aceCount = 0;

    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();


    protected EntityModel() {

    }

    protected void drawCard(){
        CardModel card = DeckModel.getInstance().getCard();
        sum += card.getCardValue();
        aceCount += card.isAce() ? 1 : 0;
        hand.add(card);
    }
    private void hit(){
        drawCard();
    }
    private void stay(){
        //TODO passa il turno
    }
    public ArrayList<CardModel> getHand(){
       return hand;
    }
}
