package model;

import java.util.ArrayList;

public class PlayerModel extends EntityModel {
    CardModel card;
    ArrayList<CardModel> playerHand = new ArrayList<CardModel>();
    int playerSum = 0;
    int playerAceCount = 0;
    public PlayerModel(DeckModel deckModel) {
            for (int i = 0; i < 2; i++) {
                drawCard(deckModel);
                playerSum += card.getCardValue();
                playerAceCount += card.isAce() ? 1 : 0;
                playerHand.add(card);
            }
    }
}
