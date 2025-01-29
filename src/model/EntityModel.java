package model;

import java.util.ArrayList;

public abstract class EntityModel {
    protected int sum = 0;
    protected int aceCount = 0;
    protected boolean blackjack = false;
    protected boolean busts = false;

    protected ArrayList<CardModel> hand = new ArrayList<CardModel>();


    protected EntityModel() {
        for (int i = 0; i < 2; i++) {
            drawCard();
        }
        blackjackCheck();

    }

    protected void drawCard(){
        CardModel card = DeckModel.getInstance().getCard();
        sum += card.getCardValue();
        aceCount += card.isAce() ? 1 : 0;
        hand.add(card);
    }
    public void hit(){
        drawCard();
        changeAceValue();
        bustsCheck();

    }
    public void stay(){
        TurnManager.getInstance().nextTurn();
    }
    public ArrayList<CardModel> getHand(){
       return hand;
    }
    public int getSum(){
        return sum;
    }
    public int getAceCount(){
        return aceCount;
    }
    public void changeAceValue(){
        while(sum > 21 && aceCount > 0){
            sum -= 10;
            aceCount--;
        }
    }
    public  void blackjackCheck(){
        if (sum == 21 && hand.size() == 2){
            blackjack = true;
        }
    }
    public void endTurn(){
        TurnManager.getInstance().nextTurn();
    }
    public abstract void bustsCheck();

}
