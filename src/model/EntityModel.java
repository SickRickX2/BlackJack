package model;

import java.util.ArrayList;

public abstract class EntityModel {
    protected int sum = 0;
    protected int aceCount = 0;

    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();


    protected EntityModel() {
        for (int i = 0; i < 2; i++) {
            drawCard();
        }

    }

    protected void drawCard(){
        CardModel card = DeckModel.getInstance().getCard();
        sum += card.getCardValue();
        aceCount += card.isAce() ? 1 : 0;
        hand.add(card);
    }
    public void hit(){
        drawCard();
        System.out.println("Player's hand: " + hand);
    }
    public void stay(){
        TurnManager.getInstance().nextTurn();
        System.out.println("Player stays");
    }
    public ArrayList<CardModel> getHand(){
       return hand;
    }
}
