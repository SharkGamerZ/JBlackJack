package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel implements BlackJackPanelView {
    private JButton newGameButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MenuView() {
        setLayout(new BorderLayout());
        initialize();
    }

    @Override
    public String getViewName() {
        return "MENU";
    }

    @Override
    public void initialize() {
        // Create a panel for centered buttons with some spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // Create title
        JLabel titleLabel = new JLabel("Blackjack Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Create buttons
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));

        optionsButton = new JButton("Options");
        optionsButton.setFont(new Font("Arial", Font.PLAIN, 18));

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add components to panels
        buttonPanel.add(titleLabel);
        buttonPanel.add(newGameButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton);

        // Add button panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void updateView() {
        // Nothing to update in menu view
    }

    /**
     * Set action listener for the new game button
     */
    public void setNewGameButtonListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    public void setOptionsButtonListener(ActionListener listener) {
        optionsButton.addActionListener(listener);
    }

    /**
     * Set action listener for the exit button
     */
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
