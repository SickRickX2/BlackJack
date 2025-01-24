package model;

public abstract class EntityModel {
    protected CardModel card;
    protected int sum = 0;
    protected int aceCount = 0;

    protected EntityModel() {

    }

    protected void drawCard(DeckModel deckModel){
        card = deckModel.deck.removeLast();
    }
}
