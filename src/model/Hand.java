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

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

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

    public Card getVisibleCard() {
        if (cards.size() > 1) {
            return cards.get(1);
        }
        return null;
    }
}
