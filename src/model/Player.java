/**
 * Rappresenta un giocatore nel gioco del BlackJack.
 * Gestisce il profilo utente, statistiche e altre proprietà.
 */
package model;

import java.util.Observable;

public class Player extends Observable {
    private String nickname;
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
     * @param nickname il nome utente del giocatore
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
        // Inizializzazione delle altre proprietà
    }

    public Hand getHand() {
        return hand;
    }


    // Methods for betting
    public void placeBet(int amount) {
        this.bet = amount;
        this.balance -= amount;
    }

    public int getBet() {
        return bet;
    }

    public void resetBet() {
        bet = 0;
    }

    public void receiveWinnings(int amount) {
        this.balance += amount;
    }

    // Add methods for player actions
    public void hit(Deck deck) {
        hand.addCard(deck.drawCard());
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBusted() {
        return hand.calculateScore() > 21;
    }

    // Methods for player games
    public void incrementGamesWon() {
        gamesWon++;
    }

    public void incrementGamesLost() {
        gamesLost++;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }
}
