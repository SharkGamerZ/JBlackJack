/**
 * Gestisce la logica principale del gioco del BlackJack.
 * Coordina l'avanzamento della partita e le interazioni tra i giocatori.
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    private Deck deck;
    private Player dealer;
    private Player humanPlayer;
    private ArrayList<Player> aiPlayers;
    private GameState state;
    private int currentPlayerIndex;

    public Game() {
        // Initialize components
        deck = new Deck();
        dealer = new Player("Dealer");

        humanPlayer = new Player("Player");

    }

    public void startNewGame(int numAiPlayers) {
        // Create AI players
        aiPlayers = new ArrayList<Player>(numAiPlayers);
        for (int i = 0; i < numAiPlayers; i++) {
            Player ai = new Player("AI Player " + (i+1));
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
            ai.placeBet(calculateAiBet(ai));
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

        // Start with human player
        currentPlayerIndex = 0;
        state = GameState.PLAYER_TURN;

        // Notify observers
        setChanged();
        notifyObservers(new GameEvent("NEW_GAME"));
    }

    private int calculateAiBet(Player ai) {
        // AI betting strategy
        return 10; // Simple fixed bet for now
    }

    // ---------------- Player ----------------
    public void playerHit() {
        if (state == GameState.PLAYER_TURN) {
            humanPlayer.getHand().addCard(deck.drawCard());

            if (humanPlayer.isBusted()) {
                playAiTurns();
            }

            setChanged();
            notifyObservers(new GameEvent("PLAYER_HIT"));
        }
    }

    public void playerStand() {
        if (state == GameState.PLAYER_TURN) {
            playAiTurns();
        }
    }

    public ArrayList<Card> getPlayerCards() {
        return humanPlayer.getHand().getCards();
    }

    public int getPlayerScore() {
        return humanPlayer.getScore();
    }

    public boolean isPlayerBusted() {
        return humanPlayer.isBusted();
    }

    public String getPlayerResult() {
        if (humanPlayer.getScore() > 21) return "BUSTED";
        if (isDealerBusted()) return "WIN";
        if (humanPlayer.getScore() > dealer.getScore()) return "WIN";
        if (humanPlayer.getScore() == dealer.getScore()) return "TIE";
        return "LOST";
    }

    // ---------------- AI ----------------
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

    private boolean shouldAiHit(Player ai) {
        // Simple AI strategy: hit on 16 or below
        return ai.getHand().calculateScore() <= 16 && !ai.isBusted();
    }

    public ArrayList<Card> getAIPlayerCards(int AI) {
        return aiPlayers.get(AI).getHand().getCards();
    }

    public int getAIPlayerScore(int AI) {
        return aiPlayers.get(AI).getScore();
    }

    public boolean isAIPlayerBusted(int AI) {
        return aiPlayers.get(AI).isBusted();
    }

    public String getAIResult(int AI) {
        if (aiPlayers.get(AI).getScore() > 21) return "BUSTED";
        if (isDealerBusted()) return "WIN";
        if (aiPlayers.get(AI).getScore() > dealer.getScore()) return "WIN";
        if (aiPlayers.get(AI).getScore() == dealer.getScore()) return "TIE";
        return "LOST";
    }

    // ---------------- Dealer ----------------
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

    public Card getDealerVisibleCard() {
        return dealer.getHand().getVisibleCard();
    }

    public int getDealerVisibleScore() {
        return dealer.getHand().getVisibleCard().getValue();
    }

    public ArrayList<Card> getDealerCards() {
        return dealer.getHand().getCards();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public boolean isDealerBusted() {
        return dealer.isBusted();
    }

    private void resolveGame() {
        state = GameState.GAME_OVER;

        // Determine outcomes for human player
        int humanPayout = calculatePayout(humanPlayer);
        humanPlayer.receiveWinnings(humanPayout);

        // Determine outcomes for AI players
        for (Player ai : aiPlayers) {
            int aiPayout = calculatePayout(ai);
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
            return (int)(bet * 2.5); // 3:2 payout for blackjack
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

    public int getNumOpponents() {
        return aiPlayers.size();
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