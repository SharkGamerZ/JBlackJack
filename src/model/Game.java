/**
 * Gestisce la logica principale del gioco del BlackJack.
 * Coordina l'avanzamento della partita e le interazioni tra i giocatori.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * The main class of the model.
 * It represents a game in every aspect.
 */
public class Game extends Observable {
    private Deck deck;
    private Player dealer;
    private Player humanPlayer;
    private ArrayList<Player> aiPlayers;
    private GameState state;

    /**
     * Constructor method
     */
    public Game() {
        // Initialize components
        deck = new Deck();
        dealer = new Player("Dealer");

        humanPlayer = new Player("Player");

    }

    public void setPlayer(Player newPlayer) {
        humanPlayer = newPlayer;
    }

    public Player getPlayer() {
        return humanPlayer;
    }

    /**
     * Starts a new Game
     * 
     * @param numAiPlayers Number of AI opponents
     */
    public void startNewGame(int numAiPlayers) {
        // Create AI players
        aiPlayers = new ArrayList<Player>(numAiPlayers);
        for (int i = 0; i < numAiPlayers; i++) {
            Player ai = new Player("AI Player " + (i + 1));
            aiPlayers.add(ai);
        }

        state = GameState.NOT_STARTED;

        // Reset all hands
        dealer.getHand().clear();
        humanPlayer.getHand().clear();
        for (Player ai : aiPlayers) {
            ai.getHand().clear();
        }

        // Collect bets (human bet would be set before calling this)
        // AI betting logic could go here
        for (Player ai : aiPlayers) {
            ai.setBet(calculateAiBet(ai));
        }

        // Shuffle deck
        deck = new Deck(); // New deck each game
        deck.shuffle();

        // Deal initial cards (2 to each player and dealer)
        // First card
        dealer.getHand().addCard(deck.drawCard());
        humanPlayer.getHand().addCard(deck.drawCard());
        for (Player ai : aiPlayers) {
            ai.getHand().addCard(deck.drawCard());
        }

        // Second card
        dealer.getHand().addCard(deck.drawCard());
        humanPlayer.getHand().addCard(deck.drawCard());
        for (Player ai : aiPlayers) {
            ai.getHand().addCard(deck.drawCard());
        }

        // Notify observers
        setChanged();
        notifyObservers(new GameEvent("NEW_GAME"));
    }

    /**
     * Stops the possibility to bet
     */
    public void stopBets() {
        state = GameState.PLAYER_TURN;
    }

    // ---------------- Player ----------------

    /**
     * The human player hits and takes a card
     */
    public void playerHit() {
        if (state == GameState.PLAYER_TURN) {
            humanPlayer.hit(deck);

            if (humanPlayer.isBusted()) {
                playAiTurns();
            }

            setChanged();
            notifyObservers(new GameEvent("PLAYER_HIT"));
        }
    }

    /**
     * The human player stands, so the opponents (if present) then the dealer play.
     */
    public void playerStand() {
        if (state == GameState.PLAYER_TURN) {
            playAiTurns();
        }
    }

    /**
     * Gets the human Player cards from his hand.
     * 
     * @return ArrayList of Cards
     */
    public ArrayList<Card> getPlayerCards() {
        return humanPlayer.getHand().getCards();
    }

    /**
     * Gets the current score of the human player.
     * 
     * @return The player current score.
     */
    public int getPlayerScore() {
        return humanPlayer.getScore();
    }

    /**
     * Gets if the player busted or no.
     * 
     * @return True if the player busted, false otherwise.
     */
    public boolean isPlayerBusted() {
        return humanPlayer.isBusted();
    }

    /**
     * Gets the human player current balance.
     * 
     * @return Player's balance/
     */
    public int getPlayerBalance() {
        return humanPlayer.getBalance();
    }

    /**
     * Gets the human player current result.
     * 
     * @return A String representing it's score
     */
    public String getPlayerResult() {
        if (humanPlayer.getScore() > 21)
            return "BUSTED";
        if (isDealerBusted())
            return "WIN";
        if (humanPlayer.getScore() > dealer.getScore())
            return "WIN";
        if (humanPlayer.getScore() == dealer.getScore())
            return "TIE";
        return "LOST";
    }

    // ---------------- AI ----------------

    /**
     * Plays the AI turns
     */
    public void playAiTurns() {
        // AI decision making for each AI player
        for (Player ai : aiPlayers) {
            while (shouldAiHit(ai)) {
                ai.getHand().addCard(deck.drawCard());

                setChanged();
                notifyObservers(new GameEvent("AI_HIT", ai));
            }
        }

        // After AI players, dealer plays
        playDealerTurn();
    }

    /**
     * Returns if an opponent should hit
     * 
     * @param ai The number of the opponent
     * @return If it should hit (true) or stand (false)
     */
    private boolean shouldAiHit(Player ai) {
        // Simple AI strategy: hit on 16 or below
        return ai.getHand().calculateScore() <= 16 && !ai.isBusted();
    }

    /**
     * Calculate the bet for the AI opponent.
     * 
     * @param ai The number of the opponent.
     * @return A random value between 5 and 100.
     */
    private int calculateAiBet(Player ai) {
        // AI betting strategy
        Random random = new Random();
        // Generate a number between 1 and 20 (inclusive)
        int multiplier = random.nextInt(20) + 1;
        // Multiply by 5 to get a multiple of 5
        return multiplier * 5;
    }

    /**
     * Gets the AI opponent current bet.
     * 
     * @param AI The number of the opponent.
     * @return The current bet of the opponent with number AI.
     */
    public int getAIBet(int AI) {
        return aiPlayers.get(AI).getBet();
    }

    /**
     * Gets the AI opponent current balance.
     * 
     * @param AI The number of the opponent.
     * @return The current balance of the opponent with number AI.
     */
    public int getAIBalance(int AI) {
        return aiPlayers.get(AI).getBalance();
    }

    /**
     * Gets the AI Opponent cards from his hand.
     * 
     * @param AI The number of the opponent.
     * @return ArrayList of the cards.
     */
    public ArrayList<Card> getAIPlayerCards(int AI) {
        return aiPlayers.get(AI).getHand().getCards();
    }

    /**
     * Gets the AI Opponent current score.
     * 
     * @param AI The number of the opponent.
     * @return Current score of opponent with number AI
     */
    public int getAIPlayerScore(int AI) {
        return aiPlayers.get(AI).getScore();
    }

    /**
     * Gets the AI opponent current result.
     * 
     * @param AI The number of the opponent.
     * @return A String representing it's score
     */
    public String getAIResult(int AI) {
        if (aiPlayers.get(AI).getScore() > 21)
            return "BUSTED";
        if (isDealerBusted())
            return "WIN";
        if (aiPlayers.get(AI).getScore() > dealer.getScore())
            return "WIN";
        if (aiPlayers.get(AI).getScore() == dealer.getScore())
            return "TIE";
        return "LOST";
    }

    // ---------------- Dealer ----------------

    /**
     * Plays the dealer's turn
     */
    private void playDealerTurn() {
        state = GameState.DEALER_TURN;

        // Dealer hits until 17 or higher
        while (dealer.getHand().calculateScore() < 17) {
            dealer.getHand().addCard(deck.drawCard());

            setChanged();
            notifyObservers(new GameEvent("DEALER_HIT"));
        }

        // Resolve game outcomes
        resolveGame();
    }

    /**
     * Gets the only currently visible card from the Dealer.
     * 
     * @return Current visible card.
     */
    public Card getDealerVisibleCard() {
        return dealer.getHand().getVisibleCard();
    }

    /**
     * Gets the currently visible score from the Dealer.
     * 
     * @return Current visible score.
     */
    public int getDealerVisibleScore() {
        return dealer.getHand().getVisibleCard().getValue();
    }

    /**
     * Gets all the cards of the dealer.
     * 
     * @return ArrayList of cards of the dealer.
     */
    public ArrayList<Card> getDealerCards() {
        return dealer.getHand().getCards();
    }

    /**
     * Gets the dealer total score.
     * 
     * @return The dealer's score
     */
    public int getDealerScore() {
        return dealer.getScore();
    }

    /**
     * Gets if the dealer is busted.
     * 
     * @return True if the dealer busted, false otherwise.
     */
    public boolean isDealerBusted() {
        return dealer.isBusted();
    }

    /**
     * Determine the outcome of the game
     */
    private void resolveGame() {
        state = GameState.GAME_OVER;

        // Determine outcomes for human player
        int humanPayout = calculatePayout(humanPlayer) - humanPlayer.getBet();
        humanPlayer.receiveWinnings(humanPayout);

        // Determine outcomes for AI players
        for (Player ai : aiPlayers) {
            int aiPayout = calculatePayout(ai) - ai.getBet();
            ai.receiveWinnings(aiPayout);
        }

        // Update statistics
        updatePlayerStats(humanPlayer);
        for (Player ai : aiPlayers) {
            updatePlayerStats(ai);
        }

        setChanged();
        notifyObservers(new GameEvent("GAME_OVER"));
    }

    /**
     * Updates the stats after the game/
     * 
     * @param player Player to which increment stats.
     */
    private void updatePlayerStats(Player player) {
        player.incrementGamesPlayed();

        // Determine win/loss
        int playerScore = player.getHand().calculateScore();
        int dealerScore = dealer.getHand().calculateScore();

        boolean playerBusted = playerScore > 21;
        boolean dealerBusted = dealerScore > 21;

        if ((playerBusted) ||
                (!dealerBusted && dealerScore > playerScore)) {
            player.incrementGamesLost();
        } else if (!playerBusted &&
                (dealerBusted || playerScore > dealerScore)) {
            player.incrementGamesWon();
        }
        // Ties don't count as win or loss
    }

    /**
     * Calculate the payout of the current Game
     * 
     * @param player Player to which calculate the payout
     * @return The payout (positive or negative)
     */
    public int calculatePayout(Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        int bet = player.getBet();

        // Player busts
        if (playerScore > 21) {
            return 0;
        }

        // Dealer busts, player wins
        if (dealerScore > 21) {
            return bet * 2;
        }

        // Check for blackjack
        boolean playerBlackjack = playerScore == 21 && player.getHand().getCards().size() == 2;
        boolean dealerBlackjack = dealerScore == 21 && dealer.getHand().getCards().size() == 2;

        if (playerBlackjack && !dealerBlackjack) {
            return (int) (bet * 2.5); // 3:2 payout for blackjack
        } else if (playerBlackjack && dealerBlackjack) {
            return bet; // Push on double blackjack
        } else if (!playerBlackjack && dealerBlackjack) {
            return 0; // Dealer blackjack wins
        }

        // Normal comparison
        if (playerScore > dealerScore) {
            return bet * 2;
        } else if (playerScore == dealerScore) {
            return bet; // Push, return the bet
        } else {
            return 0; // Dealer wins
        }

    }

    /**
     * Gets the number of opponents
     * 
     * @return The number of AI opponents
     */
    public int getNumOpponents() {
        return aiPlayers.size();
    }

    /**
     * Gets the bet of the human player
     * 
     * @return The human player's bet
     */
    public int getHumanPlayerBet() {
        return humanPlayer.getBet();
    }

    /**
     * Sets the bet of the human player
     * 
     * @param bet New bet to set
     */
    public void setHumanPlayerBet(int bet) {
        humanPlayer.setBalance(humanPlayer.getBalance() + humanPlayer.getBet());
        humanPlayer.setBet(bet);
        humanPlayer.setBalance(humanPlayer.getBalance() - bet);
    }

    /**
     * Gets the state of the game
     * 
     * @return Avalue of the GameState enum
     */
    public GameState getState() {
        return state;
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
