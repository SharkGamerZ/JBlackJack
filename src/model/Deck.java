package model;

/**
 * Rappresenta un mazzo di carte per il gioco del BlackJack.
 * Gestisce la collezione di carte e le operazioni di mescolamento.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private Random random;

    /**
     * Constructor - create a standard 52-card deck
     */
    public Deck() {
        cards = new ArrayList<>();
        random = new Random();

        // Create all 52 cards
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    /**
     * Draw a card from the top of the deck
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            // If deck is empty, create a new shuffled deck
            cards = new ArrayList<>();
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
            shuffle();
        }

        return cards.remove(0);
    }

    /**
     * Get remaining cards count
     */
    public int getRemainingCards() {
        return cards.size();
    }
}
