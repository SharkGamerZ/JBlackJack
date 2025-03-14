package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResultView extends JPanel implements BlackJackPanelView {
    private JLabel resultLabel;
    private JButton playAgainButton;
    private JButton menuButton;

    public ResultView() {
        setLayout(new BorderLayout());
        initialize();
    }

    @Override
    public String getViewName() {
        return "RESULT";
    }

    @Override
    public void initialize() {
        // Create components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        resultLabel = new JLabel("Game Over", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        playAgainButton = new JButton("Play Again");
        menuButton = new JButton("Back to Menu");

        // Add components
        centerPanel.add(resultLabel);
        centerPanel.add(playAgainButton);
        centerPanel.add(menuButton);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void updateView() {
        revalidate();
        repaint();
    }

    /**
     * Set the result text
     */
    public void setResult(String result) {
        resultLabel.setText(result);
    }

    /**
     * Set action listener for play again button
     */
    public void setPlayAgainButtonListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    /**
     * Set action listener for menu button
     */
    public void setMenuButtonListener(ActionListener listener) {
        menuButton.addActionListener(listener);
    }
}
