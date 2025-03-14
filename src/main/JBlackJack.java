/**
 * JBlackJack è la classe principale che avvia il gioco.
 * Contiene il metodo main() che inizializza la GUI e il controller.
 */
package main;

import controller.BlackJackController;
import model.BlackJackModel;

public class JBlackJack {
    public static void main(String[] args) {
        // Create model
        BlackJackModel model = new BlackJackModel();

        // Create controller with model
        BlackJackController controller = new BlackJackController(model);

        // The controller will handle initialization and display
    }
}
