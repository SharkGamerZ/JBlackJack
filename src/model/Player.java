/**
 * Rappresenta un giocatore nel gioco del BlackJack.
 * Gestisce il profilo utente, statistiche e altre proprietà.
 */
package model;

import java.util.Observable;

/**
 * Class that represents a Player
 */
public class Player extends Observable {
    private String nickname;
    // TODO implementare avatar
    private String avatar;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int level;
    private int balance;

    private Hand hand;
    private int bet;

    /**
     * Costruttore per la classe Player.
     * 
     * @param nickname il nome utente del giocatore
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
        // Inizializzazione delle altre proprietà
        this.balance = 1000;
        this.bet = 0;
    }

    /**
     * Gets the nickname of the player
     * 
     * @return The nickname of the player
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the nickname of the player
     * 
     * @return The nickname of the player
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the nickname of the player
     * 
     * @param gamesPlayed The nickname of the player
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the number of games won by the player
     * 
     * @return The number of games won
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets the number of games won by the player
     * 
     * @param gamesWon The number of games won
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets the number of games lost by the player
     * 
     * @return The number of games lost
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Sets the number of games lost by the player
     * 
     * @param gamesLost The number of games lost
     */
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    /**
     * Gets the level of the player
     * 
     * @return The level of the player
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the player
     * 
     * @param newLevel The level of the player
     */
    public void setLevel(int newLevel) {
        level = newLevel;
    }

    /**
     * Gets the current hand of the player
     * 
     * @return Hand of the player
     */
    public Hand getHand() {
        return hand;
    }

    // Methods for betting

    /**
     * Sets the current bet for the player
     * 
     * @param amount Amount to bet
     */
    public void setBet(int amount) {
        this.bet = amount;
    }

    /**
     * Gets the current bet of the player
     * 
     * @return The amount of the current bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * Receive the winnings (or losses) at the end of a game
     * 
     * @param amount The amount to add (or subtract) from the balance
     */
    public void receiveWinnings(int amount) {
        this.balance += amount;
    }

    /**
     * Gets the player's balance
     * 
     * @return The player's current balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the player's balance
     * 
     * @param newBalance New balance to set
     */
    public void setBalance(int newBalance) {
        balance = newBalance;
    }

    // Add methods for player actions

    /**
     * Hit and draws a card from the deck
     * 
     * @param deck The deck from which to draw the card
     */
    public void hit(Deck deck) {
        hand.addCard(deck.drawCard());
    }

    /**
     * Gets the current score of the player's hand
     * 
     * @return Score of the hand
     */
    public int getScore() {
        return hand.calculateScore();
    }

    /**
     * Gets if the current player's hand busted
     * 
     * @return True if busted, false otherwise.
     */
    public boolean isBusted() {
        return hand.calculateScore() > 21;
    }

    // Methods for player games

    /**
     * Increments the number of games won
     */
    public void incrementGamesWon() {
        gamesWon++;
    }

    /**
     * Increments the number of games lost
     */
    public void incrementGamesLost() {
        gamesLost++;
    }

    /**
     * Increments the number of games played
     */
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }
}
