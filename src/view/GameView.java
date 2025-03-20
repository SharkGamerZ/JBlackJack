package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JPanel implements BlackJackPanelView, Observer {
    private JPanel dealerPanel;
    private JLabel dealerScoreLabel;
    private JPanel dealerCardsPanel;

    private JPanel playersPanel;
    private ArrayList<JPanel> aiPlayerPanel;
    private ArrayList<JLabel> aiPlayerScoreLabel;
    private ArrayList<JPanel> aiPlayerCardsPanel;
    private ArrayList<JLabel> aiPlayerResultLabel;

    private JPanel humanPlayerPanel;
    private JLabel humanPlayerScoreLabel;
    private JPanel humanPlayerCardsPanel;
    private JLabel humanPlayerResultLabel;

    private MyButton sub10Button;
    private MyButton sub5Button;
    private MyButton sub1Button;
    private JLabel betValue;
    private JButton add5Button;
    private JButton add10Button;
    private JButton add25Button;

    private JPanel buttonPanel;
    private CardLayout buttonsCardLayout;

    private MyButton startGameButton;
    private MyButton menuButton;

    private MyButton hitButton;
    private MyButton standButton;

    private MyButton clearButton;

    public GameView() {
        aiPlayerPanel = new ArrayList<JPanel>(3);
        aiPlayerScoreLabel = new ArrayList<JLabel>(3);
        aiPlayerCardsPanel = new ArrayList<JPanel>(3);
        aiPlayerResultLabel = new ArrayList<JLabel>(3);

        setLayout(new BorderLayout());
        initialize();
        setBackground(new Color(53, 101, 77));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public String getViewName() {
        return "GAME";
    }

    @Override
    public void initialize() {
        // Create the main dealer panel with BorderLayout
        dealerPanel = new JPanel();
        dealerPanel.setLayout(new BorderLayout());
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));


        // Create the score label and center it at the top
        dealerScoreLabel = new JLabel("", SwingConstants.CENTER);
        dealerScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create the panel for dealer's cards with FlowLayout centered
        dealerCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dealerCardsPanel.setOpaque(false);

        // Add components to the dealer panel
        dealerPanel.add(dealerScoreLabel, BorderLayout.NORTH);
        dealerPanel.add(dealerCardsPanel, BorderLayout.CENTER);

        dealerPanel.setOpaque(false);

        // Create Players panel
        playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(1, 4));
        playersPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playersPanel.setOpaque(false);

        // Create human player panel
        humanPlayerPanel = new JPanel();
        humanPlayerPanel.setLayout(new BorderLayout());

        humanPlayerScoreLabel = new JLabel("", SwingConstants.CENTER);
        humanPlayerScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        humanPlayerCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        humanPlayerCardsPanel.setOpaque(false);

        humanPlayerResultLabel = new JLabel("", SwingConstants.CENTER);
        humanPlayerResultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        humanPlayerPanel.add(humanPlayerScoreLabel, BorderLayout.NORTH);
        humanPlayerPanel.add(humanPlayerCardsPanel, BorderLayout.CENTER);
        humanPlayerPanel.add(humanPlayerResultLabel, BorderLayout.SOUTH);
        humanPlayerPanel.setOpaque(false);

        playersPanel.add(humanPlayerPanel);

        // Create AI player panels
        for (int i = 0; i < 3; i++) {
            aiPlayerPanel.add(i, new JPanel());
            aiPlayerPanel.get(i).setLayout(new BorderLayout());

            aiPlayerScoreLabel.add(i, new JLabel("", SwingConstants.CENTER));
            aiPlayerScoreLabel.get(i).setFont(new Font("Arial", Font.BOLD, 24));

            aiPlayerCardsPanel.add(i, new JPanel(new FlowLayout(FlowLayout.CENTER)));
            aiPlayerCardsPanel.get(i).setOpaque(false);

            aiPlayerResultLabel.add(i, new JLabel("", SwingConstants.CENTER));
            aiPlayerResultLabel.get(i).setFont(new Font("Arial", Font.BOLD, 24));

            aiPlayerPanel.get(i).add(aiPlayerScoreLabel.get(i), BorderLayout.NORTH);
            aiPlayerPanel.get(i).add(aiPlayerCardsPanel.get(i), BorderLayout.CENTER);
            aiPlayerPanel.get(i).add(aiPlayerResultLabel.get(i), BorderLayout.SOUTH);

            aiPlayerPanel.get(i).setOpaque(false);

            playersPanel.add(aiPlayerPanel.get(i));
        }



        // Bet Panel
        ImageIcon chip5 = null;
        ImageIcon chip10 = null;
        ImageIcon chip25 = null;
        try {
            chip5 = new ImageIcon(getClass().getClassLoader().getResource("images/chips/chip-5.png"));
            chip10 = new ImageIcon(getClass().getClassLoader().getResource("images/chips/chip-10.png"));
            chip25 = new ImageIcon(getClass().getClassLoader().getResource("images/chips/chip-25.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error creating the chips Icons");
        }
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        betPanel.setBorder(BorderFactory.createTitledBorder("Bet"));
        sub10Button = new MyButton("-10");
        sub5Button = new MyButton("-5");
        sub1Button = new MyButton("-1");
        betValue = new JLabel("0");

        add5Button = new JButton();
        add5Button.setIcon(chip5);
        add5Button.setOpaque(false);
        add5Button.setContentAreaFilled(false);
        add5Button.setBorderPainted(false);

        add10Button = new JButton();
        add10Button.setIcon(chip10);
        add10Button.setOpaque(false);
        add10Button.setContentAreaFilled(false);
        add10Button.setBorderPainted(false);

        add25Button = new JButton();
        add25Button.setIcon(chip25);
        add25Button.setOpaque(false);
        add25Button.setContentAreaFilled(false);
        add25Button.setBorderPainted(false);

        betPanel.add(sub10Button);
        betPanel.add(sub5Button);
        betPanel.add(sub1Button);
        betPanel.add(betValue);
        betPanel.add(add5Button);
        betPanel.add(add10Button);
        betPanel.add(add25Button);
        betPanel.setOpaque(false);



        // Buttons panel
        /**
         * First:
         * - Start Game
         * - Back to Menu
         * Second:
         * - Hit
         * - Stand
         * Third:
         * - Clear
         * - Exit
         */

        buttonPanel = new JPanel();
        buttonsCardLayout = new CardLayout();
        buttonPanel.setLayout(buttonsCardLayout);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonPanel.setOpaque(false);

        // First Panel
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        firstPanel.setOpaque(false);

        startGameButton = new MyButton("Start Game");
        menuButton = new MyButton("Exit");

        firstPanel.add(startGameButton);
        firstPanel.add(menuButton);


        // Second Panel
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        secondPanel.setOpaque(false);

        hitButton = new MyButton("Hit");
        standButton = new MyButton("Stand");

        secondPanel.add(hitButton);
        secondPanel.add(standButton);

        // Third Panel
        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        thirdPanel.setOpaque(false);

        clearButton = new MyButton("Clear");

        thirdPanel.add(clearButton);
//        thirdPanel.add(menuButton);



        buttonPanel.add(firstPanel, "1");
        buttonPanel.add(secondPanel, "2");
        buttonPanel.add(thirdPanel, "3");


        buttonsCardLayout.show(buttonPanel, "1");


        // South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(betPanel);
        southPanel.add(buttonPanel);
        southPanel.setOpaque(false);


        // Add panels to main panel
        add(dealerPanel, BorderLayout.NORTH);
        add(playersPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
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
     * Updates AI score
     * @param AI Number of AI to which change the score
     * @param score
     */
    public void updateAIScore(int AI, int score) {
        aiPlayerScoreLabel.get(AI).setText("Score: " + score);
    }

    /**
     * Updates the player's score display
     */
    public void updatePlayerScore(int score) {
        humanPlayerScoreLabel.setText("Score: " + score);
    }


    public void setAIResult(int AI, String result){
        aiPlayerResultLabel.get(AI).setText(result);
    }

    public void setPlayerResult(String result) {
        humanPlayerResultLabel.setText(result);
    }

   // ----------------- CARDS -----------------

    /**
     * Gets the sprite from te card name
     * @param cardName formatted as: "rank_suit"
     * @return
     */
    public ImageIcon getCardImage(String cardName) {
        cardName = cardName.toLowerCase();
        ImageIcon cardImage = new ImageIcon(getClass().getClassLoader().getResource("images/cards/" + cardName + ".png"));

        return cardImage;
    }
    /**
     * Adds a card to the dealer's panel
     */
    public void addDealerCard(String cardName) {
        // In a real implementation, you'd show actual card images
        JLabel cardLabel = new JLabel(getCardImage(cardName));
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dealerCardsPanel.add(cardLabel);
        dealerCardsPanel.repaint();
        dealerCardsPanel.revalidate();
    }

    public void clearDealerCards() {
        //Get the components in the panel
        Component[] componentList = dealerCardsPanel.getComponents();

        for(Component c : componentList){
            dealerScoreLabel.setText("");
            dealerCardsPanel.remove(c);
        }

        dealerCardsPanel.revalidate();
        dealerCardsPanel.repaint();
    }

    public void addAICard(int AI, String cardName) {
        JLabel cardLabel = new JLabel(getCardImage(cardName));
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aiPlayerCardsPanel.get(AI).add(cardLabel);

        aiPlayerCardsPanel.get(AI).repaint();
        aiPlayerCardsPanel.get(AI).revalidate();
    }

    public void clearAICards(int AI) {
        //Get the components in the panel
        Component[] componentList = aiPlayerCardsPanel.get(AI).getComponents();

        for(Component c : componentList){
            aiPlayerScoreLabel.get(AI).setText("");
            aiPlayerCardsPanel.get(AI).remove(c);
            aiPlayerResultLabel.get(AI).setText("");
        }

        aiPlayerCardsPanel.get(AI).revalidate();
        aiPlayerCardsPanel.get(AI).repaint();
    }

    /**
     * Adds a card to the player's panel
     */
    public void addPlayerCard(String cardName) {
        JLabel cardLabel = new JLabel(getCardImage(cardName));
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        humanPlayerCardsPanel.add(cardLabel);

        humanPlayerCardsPanel.repaint();
        humanPlayerCardsPanel.revalidate();
    }

    public void clearPlayerCards() {
        //Get the components in the panel
        Component[] componentList = humanPlayerCardsPanel.getComponents();

        for(Component c : componentList){
            humanPlayerScoreLabel.setText("");
            humanPlayerCardsPanel.remove(c);
        }

        humanPlayerResultLabel.setText("");

        humanPlayerCardsPanel.revalidate();
        humanPlayerCardsPanel.repaint();
    }

    // Update BetValue
    public void setBetValue(int newBetValue) {
        this.betValue.setText(Integer.toString(newBetValue));
    }

    public int getBetValue() {
        return Integer.parseInt(betValue.getText());
    }


    public void setButtonsPanel(int panel) {
        buttonsCardLayout.show(buttonPanel, Integer.toString(panel));
    }


    // ---------------- Action listeners ----------------
    /*b*
     * Sets the action listener for the -10 button
     */
    public void setSub10ButtonListener(ActionListener listener) {
        sub10Button.addActionListener(listener);
    }

    /**
     * Sets the action listener for the -5 button
     */
    public void setSub5ButtonListener(ActionListener listener) {
        sub5Button.addActionListener(listener);
    }

    /**
     * Sets the action listener for the -1 button
     */
    public void setSub1ButtonListener(ActionListener listener) {
        sub1Button.addActionListener(listener);
    }

    /**
     * Sets the action listener for the +1 button
     */
    public void setAdd5ButtonListener(ActionListener listener) {
        add5Button.addActionListener(listener);
    }

    /**
     * Sets the action listener for the +5 button
     */
    public void setAdd10ButtonListener(ActionListener listener) {
        add10Button.addActionListener(listener);
    }

    /**
     * Sets the action listener for the +10 button
     */
    public void setAdd25ButtonListener(ActionListener listener) {
        add25Button.addActionListener(listener);
    }

    // Action listener setters for interactions
    /**
     * Sets the action listener for the Hit button
     */
    public void setHitButtonListener(ActionListener listener) {
        hitButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the Stand button
     */
    public void setStandButtonListener(ActionListener listener) {
        standButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the Start Game button
     */
    public void setStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the Menu button
     */
    public void setMenuButtonListener(ActionListener listener) {
        menuButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the Clear button
     */
    public void setClearButtonListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
}
