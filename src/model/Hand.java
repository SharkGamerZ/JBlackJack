package model;

import java.util.ArrayList;
import model.Card.Rank;

/**
 * Represents a hand of cards in the game of BlackJack.
 */
public class Hand {
    private ArrayList<Card> cards;

    /**
     * Constructor
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Add a card to the hand
     * @param card The card to add to the hand
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Clears the hand from the cards
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Gets the current hand's cards.
     * @return ArrayList of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Calculate the current score of the hand.
     * @return Score of the hand
     */
    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
                score += 11;
            } else {
                score += card.getValue();
            }
        }

        // Adjust Aces if needed
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    /**
     * Gets the visible card of the dealer
     * @return The visible card of the dealer
     */
    public Card getVisibleCard() {
        if (cards.size() > 1) {
            return cards.get(1);
        }
        return null;
    }
}
