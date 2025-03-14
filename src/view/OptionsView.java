package view;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionListener;

public class OptionsView extends JPanel implements BlackJackPanelView {
    private JButton muteButton;
    private JSlider volumeSlider;

    private JButton homeButton;


    public OptionsView() {
        setLayout(new BorderLayout());
        initialize();
    }

    @Override
    public String getViewName() {
        return "OPTIONS";
    }

    @Override
    public void initialize() {
        // Create a panel for centered buttons with some spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        // Create title
        JLabel titleLabel = new JLabel("Options", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Create Slider
        volumeSlider = new JSlider(0, 100, 100);

        // Create buttons
        muteButton = new JButton("Mute");
        muteButton.setFont(new Font("Arial", Font.PLAIN, 18));

        homeButton = new JButton("Exit");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add components to panels
        buttonPanel.add(titleLabel);
        buttonPanel.add(volumeSlider);
        buttonPanel.add(muteButton);
        buttonPanel.add(homeButton);

        // Add button panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void updateView() {
        // Nothing to update in menu view
    }

    /**
     * Set action listener for the volume slider
     */
//    public void setVolumeSliderListener(ActionListener listener) {
//        volumeSlider.addActionListener(listener);
//    }

    /**
     * Set action listener for the mute button
     */
    public void setMuteButtonListener(ActionListener listener) {
        muteButton.addActionListener(listener);
    }

    /**
     * Set action listener for the home button
     */
    public void setHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }
}
