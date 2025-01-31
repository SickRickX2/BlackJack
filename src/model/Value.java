package model;

/**
 * The Value enum represents the values of cards in a standard deck of playing cards.
 * Each value is associated with a symbol.
 */
public enum Value {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

    private final String symbol;

    /**
     * Constructs a Value enum with the specified symbol.
     *
     * @param symbol the symbol associated with the card value
     */
    Value(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with the card value.
     *
     * @return the symbol associated with the card value
     */
    public String getSymbol() {
        return symbol;
    }
}
