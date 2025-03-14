package view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Objects;
//import java.awt.event.ActionListener;

public class OptionsView extends JPanel implements BlackJackPanelView {
    private JLabel optionsLabel;
    private JButton muteButton;
    private Icon muteIcon;
    private Icon unmuteIcon;
    private JSlider volumeSlider;
    private JLabel volumeLevel;

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
        optionsLabel = new JLabel();
        muteButton = new JButton();
        try {
            unmuteIcon = new ImageIcon(getClass().getClassLoader().getResource("images/unmute.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creatin the Unmute Icon");
        }
        try {
            muteIcon = new ImageIcon(getClass().getClassLoader().getResource("images/mute.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating the Mute Icon");
        }
        homeButton = new JButton();
        volumeSlider = new JSlider();
        volumeLevel = new JLabel();

        //---- muteButton ----
        muteButton.setIcon(unmuteIcon);

        //---- homeButton ----
        homeButton.setText("Home");

        //---- optionsLabel ----
        optionsLabel.setText("Opzioni");
        optionsLabel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 48));
        optionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //---- volumeSlider ----
        volumeSlider.setMinimum(0);
        volumeSlider.setMaximum(100);
        volumeSlider.setValue(100);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPaintTicks(true);

        //---- volumeLevel ----
        volumeLevel.setText("100");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(220, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(100, 100, 100))
                                                .addComponent(optionsLabel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(muteButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(volumeLevel)))
                                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(optionsLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(muteButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(volumeLevel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                                .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))
        );
    }

    @Override
    public void updateView() {
        // Nothing to update in menu view
    }


    // Event Listeners
    public void setMuteButtonListener(ActionListener listener) {
        muteButton.addActionListener(listener);
    }

    public void setVolumeSliderListener(ChangeListener listener) {
        volumeSlider.addChangeListener(listener);
    }

    public void setHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }


    // Getters
    public int getVolumeValue() {
        return volumeSlider.getValue();
    }

    // Setters
    public void setVolumeLevel(int volume) {
        volumeLevel.setText(Integer.toString(volume));
    }

    public void toggleMute() {
        boolean isMuted = volumeSlider.isEnabled();
        volumeSlider.setEnabled(!isMuted);
        volumeLevel.setEnabled(!isMuted);
        muteButton.setIcon(isMuted ? muteIcon : unmuteIcon);
    }
}
