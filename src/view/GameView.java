package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JPanel implements BlackJackPanelView, Observer {
    private JPanel dealerPanel;
    private JPanel playerPanel;
    private JLabel dealerScoreLabel;
    private JLabel playerScoreLabel;
    private JButton hitButton;
    private JButton standButton;
    private JButton menuButton;

    public GameView() {
        setLayout(new BorderLayout());
        initialize();
    }

    @Override
    public String getViewName() {
        return "GAME";
    }

    @Override
    public void initialize() {
        // Create dealer panel
        dealerPanel = new JPanel();
        dealerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));
        dealerScoreLabel = new JLabel("Score: 0");
        dealerPanel.add(dealerScoreLabel);

        // Create player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerScoreLabel = new JLabel("Score: 0");
        playerPanel.add(playerScoreLabel);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        menuButton = new JButton("Back to Menu");

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(menuButton);

        // Add panels to main panel
        add(dealerPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void updateView() {
        revalidate();
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        // This will be called when the model changes
        updateView();
    }

    /**
     * Updates the dealer's score display
     */
    public void updateDealerScore(int score) {
        dealerScoreLabel.setText("Score: " + score);
    }

    /**
     * Updates the player's score display
     */
    public void updatePlayerScore(int score) {
        playerScoreLabel.setText("Score: " + score);
    }

    /**
     * Adds a card to the dealer's panel
     */
    public void addDealerCard(String cardName) {
        // In a real implementation, you'd show actual card images
        JLabel cardLabel = new JLabel(cardName);
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardLabel.setPreferredSize(new Dimension(60, 80));
        dealerPanel.add(cardLabel);
    }

    /**
     * Adds a card to the player's panel
     */
    public void addPlayerCard(String cardName) {
        JLabel cardLabel = new JLabel(cardName);
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardLabel.setPreferredSize(new Dimension(60, 80));
        playerPanel.add(cardLabel);
    }

    // Action listener setters for buttons
    public void setHitButtonListener(ActionListener listener) {
        hitButton.addActionListener(listener);
    }

    public void setStandButtonListener(ActionListener listener) {
        standButton.addActionListener(listener);
    }

    public void setMenuButtonListener(ActionListener listener) {
        menuButton.addActionListener(listener);
    }
}
