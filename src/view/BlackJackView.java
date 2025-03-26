package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * The main view for the BlackJack game.
 *
 */
public class BlackJackView extends JFrame implements Observer {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, JPanel> views;

    private String currentView;

    /**
     * The constructor
     * 
     * @param title Title of the new view
     */
    public BlackJackView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 960);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize components
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        views = new HashMap<>();

        // Add main panel to frame
        add(mainPanel);
    }

    /**
     * Adds a panel view to the frame
     * 
     * @param viewName Unique identifier for the view
     * @param panel    The JPanel to add
     */
    public void addView(String viewName, JPanel panel) {
        views.put(viewName, panel);
        mainPanel.add(panel, viewName);
    }

    /**
     * Shows a specific panel by name
     * 
     * @param viewName The name of the panel to display
     */
    public void showView(String viewName) {
        if (views.containsKey(viewName)) {
            cardLayout.show(mainPanel, viewName);
            views.get(viewName).revalidate();
            views.get(viewName).repaint();
            views.get(viewName).updateUI();

            currentView = viewName;
        } else {
            System.err.println("View not found: " + viewName);
        }
    }

    /**
     * Makes the frame visible and shows the initial view
     * 
     * @param initialView The name of the initial view to display
     */
    public void start(String initialView) {
        setVisible(true);
        showView(initialView);
        currentView = initialView;
    }

    /**
     * Updates the view based on the model
     * 
     * @param o   The observable object
     * @param arg The argument passed
     */
    @Override
    public void update(Observable o, Object arg) {
        // This will be called when the model changes
        views.get(currentView).revalidate();
        views.get(currentView).repaint();
        views.get(currentView).updateUI();
    }
}
