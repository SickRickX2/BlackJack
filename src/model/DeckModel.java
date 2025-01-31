package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The DeckModel class represents a deck of cards used in the game.
 * It provides methods to create, shuffle, and draw cards from the deck.
 */
public class DeckModel {
    private static DeckModel instance = null;
    private ArrayList<CardModel> deck = new ArrayList<CardModel>();
    private Random random = new Random();

    /**
     * Private constructor to prevent instantiation.
     * Initializes the deck by creating and shuffling it.
     */
    private DeckModel() {
        createDeck();
        shuffleDeck();
    }

    /**
     * Returns the singleton instance of the DeckModel.
     *
     * @return the singleton instance of the DeckModel
     */
    public static DeckModel getInstance() {
        if (instance == null) {
            instance = new DeckModel();
        }
        return instance;
    }

    /**
     * Creates a standard deck of 52 cards.
     * Adds each combination of suit and value to the deck.
     */
    public void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                deck.add(new CardModel(suit, value));
            }
        }
    }

    /**
     * Draws a card from the deck.
     * Removes and returns the card from the top of the deck.
     *
     * @return the drawn card
     */
    public CardModel getCard() {
        return deck.remove(deck.size() - 1);
    }

    /**
     * Shuffles the deck using a random number generator.
     * Swaps each card in the deck with another card at a random position.
     */
    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int randomIndex = random.nextInt(deck.size());
            CardModel tempCard = deck.get(i);
            CardModel randomCard = deck.get(randomIndex);
            deck.set(i, randomCard);
            deck.set(randomIndex, tempCard);
        }
    }
}