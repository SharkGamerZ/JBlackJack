package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the menu view
 */
public class MenuView extends JPanel implements BlackJackPanelView {
    private JLabel titleLabel;
    private JButton newGameButton;
    private JButton profileButton;
    private JButton exitButton;

    /**
     * Constructor for the menu view
     */
    public MenuView() {
        initialize();
    }

    /**
     * Get the view name
     * @return the view name
     */
    @Override
    public String getViewName() {
        return "MENU";
    }

    /**
     * Initialize the menu view
     */
    @Override
    public void initialize() {
        titleLabel = new JLabel();
        profileButton = new JButton();
        newGameButton = new JButton();
        exitButton = new JButton();


        //---- optionsButton ----
        profileButton.setText("Profilo");

        //---- newGameButton ----
        newGameButton.setText("Nuova Partita");

        //---- exitButton ----
        exitButton.setText("Esci");

        //---- Titolo ----
        titleLabel.setText("JBlackJack");
        titleLabel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(newGameButton);
        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(profileButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(exitButton);
    }

    /**
     * Update the menu view
     */
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

    /**
     * Set action listener for the profile button
     */
    public void setProfileButtonListener(ActionListener listener) {
        profileButton.addActionListener(listener);
    }

    /**
     * Set action listener for the exit button
     */
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
