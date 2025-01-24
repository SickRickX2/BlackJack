package model;

import java.util.ArrayList;

public class DealerModel extends EntityModel {
    CardModel hiddenCard;
    ArrayList<CardModel> dealerHand = new ArrayList<CardModel>();
    int dealerSum = 0;
    int dealerAceCount = 0;
    public DealerModel(DeckModel deckModel){
        drawCard(deckModel);
        dealerSum += hiddenCard.getCardValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;
        drawCard(deckModel);
        dealerSum += hiddenCard.getCardValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;
        dealerHand.add(hiddenCard);

    }
    @Override
    protected void drawCard(DeckModel deckModel){
        hiddenCard = deckModel.deck.removeLast();
    }

}
