/**
 * Rappresenta un giocatore nel gioco del BlackJack.
 * Gestisce il profilo utente, statistiche e altre proprietà.
 */
package model;

public class Player {
    private String nickname;
    private String avatar;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int level;

    /**
     * Costruttore per la classe Player.
     * @param nickname il nome utente del giocatore
     */
    public Player(String nickname) {
        this.nickname = nickname;
        // Inizializzazione delle altre proprietà
    }
}
