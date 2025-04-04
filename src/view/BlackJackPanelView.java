package view;

/**
 * Interface to manage all the applications' panels
 */
public interface BlackJackPanelView {
    /**
     * Get the name identifier for this view
     */
    String getViewName();

    /**
     * Initialize the view components
     */
    void initialize();

    /**
     * Update the view based on model changes
     */
    void updateView();
}
