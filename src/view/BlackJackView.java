package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The main view for the BlackJack game.
 *
 */
public class BlackJackView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, JPanel> views;

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
        } else {
            System.err.println("View not found: " + viewName);
        }
    }

    /**
     * Gets a view by name
     * 
     * @param viewName The name of the view
     * @return The JPanel associated with the name, or null if not found
     */
    public JPanel getView(String viewName) {
        return views.get(viewName);
    }

    /**
     * Makes the frame visible and shows the initial view
     * 
     * @param initialView The name of the initial view to display
     */
    public void start(String initialView) {
        setVisible(true);
        showView(initialView);
    }
}
