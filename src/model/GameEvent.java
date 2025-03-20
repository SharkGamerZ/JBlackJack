package model;

import model.*;

public class GameEvent {
    private String type;
    private Card card;

    /**
     * Constructor for events without card data
     */
    public GameEvent(String type) {
        this.type = type;
        this.card = null;
    }

    /**
     * Constructor for events with card data
     */
    public GameEvent(String type, Player player) {
        this.type = type;
    }

    /**
     * Get event type
     */
    public String getType() {
        return type;
    }

    /**
     * Get card associated with the event (if any)
     */
    public Card getCard() {
        return card;
    }
}
