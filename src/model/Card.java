package model;

/**
 * Rappresenta una carta da gioco nel BlackJack.
 * Fa parte del modello (Model) e contiene le proprietà della carta.
 */
public class Card {
    private Suit suit;
    private Rank rank;

    /**
     * Constructor
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get the card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the card's rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Get the card's value for BlackJack scoring
     */
    public int getValue() {
        switch (rank) {
            case ACE:
                return 11; // Initially count as 11, can be adjusted to 1 later
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return 0;
        }
    }

    /**
     * String representation of the card
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Card suits
     */
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    /**
     * Card ranks
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
}
