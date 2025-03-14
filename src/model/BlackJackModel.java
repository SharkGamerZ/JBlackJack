package model;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class BlackJackModel extends Observable {
    // Constants
    private static final int BLACKJACK = 21;
    private static final int DEALER_STAND_VALUE = 17;

    // Game state
    private Deck deck;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private GameState gameState;

    // Singleton instance
    private static BlackJackModel instance;

    /**
     * Get singleton instance of the model
     */
    public static BlackJackModel getInstance() {
        if (instance == null) {
            instance = new BlackJackModel();
        }
        return instance;
    }

    /**
     * Constructor - initialize default state
     */
    public BlackJackModel() {
        deck = new Deck();
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        gameState = GameState.NOT_STARTED;
    }

    /**
     * Start a new game
     */
    public void newGame() {
        // Clear previous cards
        playerCards.clear();
        dealerCards.clear();

        // Create a new shuffled deck
        deck = new Deck();
        deck.shuffle();

        // Deal initial cards
        dealerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());
        dealerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());

        // Set game state
        gameState = GameState.PLAYER_TURN;

        // Notify observers about the new game
        setChanged();
        notifyObservers(new GameEvent("NEW_GAME"));
    }

    /**
     * Player hits (takes another card)
     */
    public void playerHit() {
        if (gameState == GameState.PLAYER_TURN) {
            Card card = deck.drawCard();
            playerCards.add(card);

            // Check if player busts
            if (getPlayerScore() > BLACKJACK) {
                gameState = GameState.GAME_OVER;
            }

            // Notify observers
            setChanged();
            notifyObservers(new GameEvent("PLAYER_HIT", card));
        }
    }

    /**
     * Player stands, dealer plays
     */
    public void dealerPlay() {
        if (gameState == GameState.PLAYER_TURN) {
            gameState = GameState.DEALER_TURN;

            // Dealer keeps hitting until reaching 17 or busting
            while (getDealerScore() < DEALER_STAND_VALUE) {
                Card card = deck.drawCard();
                dealerCards.add(card);

                // Notify observers
                setChanged();
                notifyObservers(new GameEvent("DEALER_HIT", card));
            }

            gameState = GameState.GAME_OVER;

            // Notify game over
            setChanged();
            notifyObservers(new GameEvent("GAME_OVER"));
        }
    }

    /**
     * Calculate player's hand score
     */
    public int getPlayerScore() {
        return calculateHandScore(playerCards);
    }

    /**
     * Calculate dealer's full hand score
     */
    public int getDealerScore() {
        return calculateHandScore(dealerCards);
    }

    /**
     * Calculate dealer's visible score (only the face-up card)
     */
    public int getDealerVisibleScore() {
        if (dealerCards.size() > 1) {
            return dealerCards.get(1).getValue();
        }
        return 0;
    }

    /**
     * Get the dealer's visible card (the second card, which is face up)
     */
    public Card getDealerVisibleCard() {
        if (dealerCards.size() > 1) {
            return dealerCards.get(1);
        }
        return null;
    }

    /**
     * Calculate score for a hand
     */
    private int calculateHandScore(List<Card> hand) {
        int score = 0;
        int aceCount = 0;

        // Sum up all card values
        for (Card card : hand) {
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
                score += 11; // Initially count Ace as 11
            } else {
                score += card.getValue();
            }
        }

        // Adjust Aces if needed (count as 1 instead of 11)
        while (score > BLACKJACK && aceCount > 0) {
            score -= 10; // Change Ace value from 11 to 1 (reduce by 10)
            aceCount--;
        }

        return score;
    }

    /**
     * Check if player has busted
     */
    public boolean isPlayerBusted() {
        return getPlayerScore() > BLACKJACK;
    }

    /**
     * Check if dealer has busted
     */
    public boolean isDealerBusted() {
        return getDealerScore() > BLACKJACK;
    }

    /**
     * Get player's cards
     */
    public List<Card> getPlayerCards() {
        return new ArrayList<>(playerCards);
    }

    /**
     * Get dealer's cards
     */
    public List<Card> getDealerCards() {
        return new ArrayList<>(dealerCards);
    }

    /**
     * Get current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Game state enum
     */
    public enum GameState {
        NOT_STARTED,
        PLAYER_TURN,
        DEALER_TURN,
        GAME_OVER
    }
}
