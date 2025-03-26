package view;

import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents the profile view
 */
public class ProfileView extends JPanel implements BlackJackPanelView {
    private static ProfileView instance = null;

    private JButton muteButton;
    private Icon muteIcon;
    private Icon unmuteIcon;

    private JButton homeButton;

    private Icon propicIcon;
    private JLabel profileLabel;
    private JSlider volumeSlider;
    private JLabel volumeLevel;
    private JSeparator separator;
    private JLabel gamesPlayedLabel;
    private JLabel gamesWonLabel;
    private JLabel gamesLostLabel;
    private JLabel propicLabel;

    /**
     * Constructor for the profile view
     */
    private ProfileView() {
        muteButton = new JButton();
        homeButton = new JButton();
        profileLabel = new JLabel();
        volumeSlider = new JSlider();
        volumeLevel = new JLabel();
        separator = new JSeparator();
        gamesPlayedLabel = new JLabel();
        gamesWonLabel = new JLabel();
        gamesLostLabel = new JLabel();
        propicLabel = new JLabel();
        initialize();
    }

    /**
     * Get the instance of the profile view
     * 
     * @return the instance of the profile view
     */
    public static ProfileView getInstance() {
        if (instance == null) {
            instance = new ProfileView();
        }
        return instance;
    }

    /**
     * Get the view name
     * 
     * @return the view name
     */
    @Override
    public String getViewName() {
        return "OPTIONS";
    }

    /**
     * Set the player
     * 
     * @param player the player
     */
    public void setPlayer(Player player) {
        profileLabel.setText(player.getNickname());
        setGamesPlayedLabel(player.getGamesPlayed());
        setGamesWonLabel(player.getGamesWon());
        setGamesLostLabel(player.getGamesLost());
    }

    /**
     * Initialize the profile view
     */
    @Override
    public void initialize() {
        // ---- muteButton ----
        muteButton = new JButton();
        try {
            unmuteIcon = new ImageIcon(getClass().getClassLoader().getResource("images/unmute.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating the Unmute Icon");
        }
        try {
            muteIcon = new ImageIcon(getClass().getClassLoader().getResource("images/mute.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating the Mute Icon");
        }

        // ---- muteButton ----
        muteButton.setIcon(unmuteIcon);

        // ---- homeButton ----
        homeButton.setText("Home");

        // ---- propicLabel ----
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("images/propics/matteo.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating the Propic Icon");
        }
        propicIcon = new ImageIcon(img.getScaledInstance(96, 96, Image.SCALE_SMOOTH));

        propicLabel.setIcon(propicIcon);
        propicLabel.setPreferredSize(new Dimension(96, 96));
        propicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ---- profileLabel ----
        profileLabel.setFont(new Font("Source Code Pro Black", Font.BOLD, 40));
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ---- volumeSlider ----
        volumeSlider.setPaintLabels(true);
        volumeSlider.setPaintTicks(true);

        // ---- volumeLevel ----
        volumeLevel.setText("100");

        // ---- partiteGiocate ----
        gamesPlayedLabel.setFont(new Font("Arial", Font.BOLD, 25));

        // ---- partiteVinte ----
        gamesWonLabel.setFont(new Font("Arial", Font.BOLD, 25));

        // ---- partitePerse ----
        gamesLostLabel.setFont(new Font("Arial", Font.BOLD, 25));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(propicLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(profileLabel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(muteButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(volumeLevel)
                                .addGap(51, 51, 51))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(613, Short.MAX_VALUE)
                                .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(352, 352, 352)
                                                .addComponent(separator, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(gamesPlayedLabel, GroupLayout.PREFERRED_SIZE, 400,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(gamesLostLabel, GroupLayout.PREFERRED_SIZE, 400,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(gamesWonLabel, GroupLayout.PREFERRED_SIZE, 400,
                                                                GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(448, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(68, 68, 68)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(muteButton, GroupLayout.PREFERRED_SIZE, 60,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(volumeLevel)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(propicLabel, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(profileLabel, GroupLayout.PREFERRED_SIZE, 120,
                                                                GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(gamesPlayedLabel, GroupLayout.PREFERRED_SIZE, 72,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194,
                                                        Short.MAX_VALUE)
                                                .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 60,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(59, 59, 59))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(gamesWonLabel, GroupLayout.PREFERRED_SIZE, 72,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(gamesLostLabel, GroupLayout.PREFERRED_SIZE, 72,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(99, Short.MAX_VALUE)))));

    }

    /**
     * Update the profile view
     */
    @Override
    public void updateView() {
        // Nothing to update in menu view
    }

    // Event Listeners

    /**
     * Set the mute button listener
     * 
     * @param listener the listener
     */
    public void setMuteButtonListener(ActionListener listener) {
        muteButton.addActionListener(listener);
    }

    /**
     * Set the volume slider listener
     * 
     * @param listener the listener
     */
    public void setVolumeSliderListener(ChangeListener listener) {
        volumeSlider.addChangeListener(listener);
    }

    /**
     * Set the home button listener
     * 
     * @param listener the listener
     */
    public void setHomeButtonListener(ActionListener listener) {
        homeButton.addActionListener(listener);
    }

    // Getters
    /**
     * Get the volume level
     * 
     * @return the volume level
     */
    public int getVolumeLevel() {
        return volumeSlider.getValue();
    }

    // Setters

    /**
     * Set the volume level
     * 
     * @param volume the volume level
     */
    public void setVolumeLevel(int volume) {
        volumeLevel.setText(Integer.toString(volume));
    }

    /**
     * Toggle the mute button
     */
    public void toggleMute() {
        boolean isMuted = volumeSlider.isEnabled();
        volumeSlider.setEnabled(!isMuted);
        volumeLevel.setEnabled(!isMuted);
        muteButton.setIcon(isMuted ? muteIcon : unmuteIcon);
    }

    /**
     * Set the profile label
     * 
     * @param username the username
     */
    public void setProfileLabel(String username) {
        profileLabel.setText(username);
    }

    /**
     * Set the games played label
     * 
     * @param gamesPlayed the games played
     */
    public void setGamesPlayedLabel(int gamesPlayed) {
        gamesPlayedLabel.setText("Games Played:" + gamesPlayed);
    }

    /**
     * Set the games won label
     * 
     * @param gamesWon the games won
     */
    public void setGamesWonLabel(int gamesWon) {
        gamesWonLabel.setText("Games Won:" + gamesWon);
    }

    /**
     * Set the games lost label
     * 
     * @param gamesLost the games lost
     */
    public void setGamesLostLabel(int gamesLost) {
        gamesLostLabel.setText("Games Lost:" + gamesLost);
    }

}
