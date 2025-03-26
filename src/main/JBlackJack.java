/**
 * JBlackJack è la classe principale che avvia il gioco.
 * Contiene il metodo main() che inizializza la GUI e il controller.
 */
package main;

import controller.BlackJackController;
import model.Game;

/**
 * The main class
 */
public class JBlackJack {
    /**
     * Main method to start the Game
     * @param args
     */
    public static void main(String[] args) {
        // Create model
        Game model = new Game();

        // Create controller with model
        BlackJackController controller = new BlackJackController(model);

        // The controller will handle initialization and display
    }
}
