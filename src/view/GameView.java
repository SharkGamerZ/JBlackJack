package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * The main view for the BlackJack game.
 */
public class GameView extends JPanel implements BlackJackPanelView{
    private JPanel dealerPanel;
    private JLabel dealerScoreLabel;
    private JPanel dealerCardsPanel;

    private JPanel playersPanel;
    private ArrayList<Boolean> aiPlayersPlaying;
    private ArrayList<JPanel> addAIPlayerPanel;
    private ArrayList<JButton> addAIPlayerButton;


    private ArrayList<JPanel> aiPlayerPanel;
    private ArrayList<JButton> removeAIPlayerButton;
    private ArrayList<JLabel> aiPlayerScoreLabel;
    private ArrayList<JPanel> aiPlayerCardsPanel;
    private ArrayList<JPanel> aiPlayerSouthPanel;
    private ArrayList<JPanel> aiPlayerBetValuePanel;
    private ArrayList<JLabel> aiPlayerResultLabel;
    private ArrayList<JLabel> aiPlayerBalanceLabel;

    private JPanel humanPlayerPanel;
    private JLabel humanPlayerScoreLabel;
    private JPanel humanPlayerCardsPanel;
    private JPanel humanPlayerSouthPanel;
    private JPanel humanPlayerBetValuePanel;
    private JLabel humanPlayerResultLabel;
    private JLabel humanPlayerBalanceLabel;

    private JPanel setBetPanel;

    private HashMap<Integer,ArrayList<JLabel>> chipsHashmap;
    private ArrayList<HashMap<Integer,ArrayList<JLabel>>> aiPlayersChipsHashmap;

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

    /**
     * Constructor
     */
    public GameView() {
        aiPlayersPlaying = new ArrayList<Boolean>(3);
        for (int i = 0; i < 3; i++) {
            aiPlayersPlaying.add(false);
        }

        addAIPlayerPanel = new ArrayList<JPanel>(3);
        addAIPlayerButton = new ArrayList<JButton>(3);

        aiPlayerPanel = new ArrayList<JPanel>(3);
        removeAIPlayerButton = new ArrayList<JButton>(3);
        aiPlayerScoreLabel = new ArrayList<JLabel>(3);
        aiPlayerCardsPanel = new ArrayList<JPanel>(3);
        aiPlayerSouthPanel = new ArrayList<JPanel>(3);
        aiPlayerBetValuePanel = new ArrayList<JPanel>(3);
        aiPlayersChipsHashmap = new ArrayList<HashMap<Integer,ArrayList<JLabel>>>(3);
        aiPlayerResultLabel = new ArrayList<JLabel>(3);
        aiPlayerBalanceLabel = new ArrayList<JLabel>(3);

        setLayout(new BorderLayout());
        initialize();
        setBackground(new Color(53, 101, 77));
    }

    /**
     * Paints the background of the panel
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Gets the name of the view
     * @return The name of the view
     */
    @Override
    public String getViewName() {
        return "GAME";
    }

    /**
     * Initializes the view components
     */
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
        playersPanel.setBorder(BorderFactory.createTitledBorder("Players"));
        playersPanel.setOpaque(false);

        // Create human player panel
        humanPlayerPanel = new JPanel();
        humanPlayerPanel.setLayout(new BorderLayout());
        humanPlayerPanel.setBorder(BorderFactory.createTitledBorder("Human Player"));

        humanPlayerScoreLabel = new JLabel("", SwingConstants.CENTER);
        humanPlayerScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        humanPlayerCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        humanPlayerCardsPanel.setOpaque(false);

        humanPlayerSouthPanel = new JPanel();
        humanPlayerSouthPanel.setLayout(new GridLayout(3,1));
        humanPlayerSouthPanel.setOpaque(false);

        humanPlayerBetValuePanel = new JPanel();
        humanPlayerBetValuePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        humanPlayerBetValuePanel.setOpaque(false);

        humanPlayerResultLabel = new JLabel("", SwingConstants.CENTER);
        humanPlayerResultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        humanPlayerBalanceLabel = new JLabel("Balance: 1000", SwingConstants.CENTER);
        humanPlayerBalanceLabel.setFont(new Font("Arial", Font.BOLD, 24));

        humanPlayerSouthPanel.add(humanPlayerBetValuePanel);
        humanPlayerSouthPanel.add(humanPlayerResultLabel);
        humanPlayerSouthPanel.add(humanPlayerBalanceLabel);

        humanPlayerPanel.add(humanPlayerScoreLabel, BorderLayout.NORTH);
        humanPlayerPanel.add(humanPlayerCardsPanel, BorderLayout.CENTER);
        humanPlayerPanel.add(humanPlayerSouthPanel, BorderLayout.SOUTH);

        humanPlayerPanel.setOpaque(false);

        playersPanel.add(humanPlayerPanel);

        // Create AI player panels
        // Initialize the arraylist to all false
        for (int i = 0; i < 3; i++) {
            aiPlayerPanel.add(i, new JPanel());
            aiPlayerPanel.get(i).setLayout(new BorderLayout());
            aiPlayerPanel.get(i).setBorder(BorderFactory.createTitledBorder("AI " + (i+1)));

            removeAIPlayerButton.add(i, new JButton("Remove AI Player"));

            aiPlayerScoreLabel.add(i, new JLabel("", SwingConstants.CENTER));
            aiPlayerScoreLabel.get(i).setFont(new Font("Arial", Font.BOLD, 24));

            aiPlayerCardsPanel.add(i, new JPanel(new FlowLayout(FlowLayout.CENTER)));
            aiPlayerCardsPanel.get(i).setOpaque(false);


            aiPlayerSouthPanel.add(i, new JPanel());
            aiPlayerSouthPanel.get(i).setLayout(new GridLayout(4,1));
            aiPlayerSouthPanel.get(i).setOpaque(false);

            aiPlayerBetValuePanel.add(i,new JPanel());
            aiPlayerBetValuePanel.get(i).setLayout(new FlowLayout(FlowLayout.CENTER));
            aiPlayerBetValuePanel.get(i).setOpaque(false);

            aiPlayerResultLabel.add(i, new JLabel("", SwingConstants.CENTER));
            aiPlayerResultLabel.get(i).setFont(new Font("Arial", Font.BOLD, 24));

            aiPlayerBalanceLabel.add(i, new JLabel("Balance: 1000", SwingConstants.CENTER));
            aiPlayerBalanceLabel.get(i).setFont(new Font("Arial", Font.BOLD, 24));

            aiPlayerSouthPanel.get(i).add(aiPlayerBetValuePanel.get(i));
            aiPlayerSouthPanel.get(i).add(aiPlayerResultLabel.get(i));
            aiPlayerSouthPanel.get(i).add(aiPlayerBalanceLabel.get(i));
            aiPlayerSouthPanel.get(i).add(removeAIPlayerButton.get(i));

            aiPlayerPanel.get(i).add(aiPlayerScoreLabel.get(i), BorderLayout.NORTH);
            aiPlayerPanel.get(i).add(aiPlayerCardsPanel.get(i), BorderLayout.CENTER);
            aiPlayerPanel.get(i).add(aiPlayerSouthPanel.get(i), BorderLayout.SOUTH);

            aiPlayerPanel.get(i).setOpaque(false);


            if (aiPlayersPlaying.get(i) == false) {
                addAIPlayerPanel.add(i, new JPanel());
                addAIPlayerPanel.get(i).setLayout(new BorderLayout());
                addAIPlayerPanel.get(i).setBorder(BorderFactory.createTitledBorder("Add new Player"));
                addAIPlayerPanel.get(i).setOpaque(false);

                addAIPlayerButton.add(i, new JButton("Add AI Player"));

                addAIPlayerPanel.get(i).add(addAIPlayerButton.get(i), BorderLayout.CENTER);

                playersPanel.add(addAIPlayerPanel.get(i));
                continue;
            }

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


        setBetPanel = new JPanel();
        setBetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        setBetPanel.add(add5Button);
        setBetPanel.add(add10Button);
        setBetPanel.add(add25Button);
        setBetPanel.setOpaque(false);





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
        southPanel.add(setBetPanel);
        southPanel.add(buttonPanel);
        southPanel.setOpaque(false);


        // Add panels to main panel
        add(dealerPanel, BorderLayout.NORTH);
        add(playersPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the chip labels
     * @param bet The bet value
     */
    private void updateChipLabels(int bet) {
        HashMap<Integer, ArrayList<JLabel>> chipsLabels = getChipsLabels(bet);
        humanPlayerBetValuePanel.removeAll();
        int[] fishValues = {100, 25, 10, 5};


        for (int i = 0; i < fishValues.length; i++) {
            for (JLabel chipLabel : chipsLabels.get(fishValues[i])) {
                humanPlayerBetValuePanel.add(chipLabel, 0);
            }
        }

        humanPlayerBetValuePanel.revalidate();
        humanPlayerBetValuePanel.repaint();
    }


    /**
     * Gets the chip labels for the bet
     * @param bet The bet value
     * @return The hashmap of chips
     */
    private HashMap<Integer, ArrayList<JLabel>> getChipsLabels(int bet) {
        chipsHashmap = new HashMap<Integer, ArrayList<JLabel>>();
        int[] fishValues = {100, 25, 10, 5};
        int remainingBet = bet;

        for (int i = 0; i < fishValues.length; i++) {
            int fishNumber = remainingBet / fishValues[i];
            ArrayList<JLabel> chipLabelList = new ArrayList<JLabel>();
            for (int j = 0; j < fishNumber; j++) {
                JLabel fishLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/chips/chip-" + fishValues[i] + ".png")));
                chipLabelList.add(fishLabel);
            }
            chipsHashmap.put(fishValues[i], chipLabelList);
            remainingBet = remainingBet % fishValues[i];
        }

        return chipsHashmap;
    }


    /**
     * Updates the AI chip labels
     * @param AI The AI number
     * @param bet The bet value
     */
    private void updateAIChipLabels(int AI, int bet) {
        if (!aiPlayersPlaying.get(AI)) return;
        HashMap<Integer, ArrayList<JLabel>> chipsLabels = getAIChipsLabels(AI, bet);
        aiPlayerBetValuePanel.get(AI).removeAll();
        int[] fishValues = {100, 25, 10, 5};


        for (int i = 0; i < fishValues.length; i++) {
            for (JLabel chipLabel : chipsLabels.get(fishValues[i])) {
                aiPlayerBetValuePanel.get(AI).add(chipLabel, 0);
            }
        }

        aiPlayerBetValuePanel.get(AI).revalidate();
        aiPlayerBetValuePanel.get(AI).repaint();
    }


    /**
     * Gets the AI chip labels for the bet
     * @param AI The AI number
     * @param bet The bet value
     * @return The hashmap of chips
     */
    private HashMap<Integer, ArrayList<JLabel>> getAIChipsLabels(int AI, int bet) {
        if (!aiPlayersPlaying.get(AI)) return null;
        aiPlayersChipsHashmap.add(new HashMap<Integer, ArrayList<JLabel>>());
        int[] fishValues = {100, 25, 10, 5};
        int remainingBet = bet;

        for (int i = 0; i < fishValues.length; i++) {
            int fishNumber = remainingBet / fishValues[i];
            ArrayList<JLabel> chipLabelList = new ArrayList<JLabel>();
            for (int j = 0; j < fishNumber; j++) {
                JLabel fishLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/chips/chip-" + fishValues[i] + ".png")));
                chipLabelList.add(fishLabel);
            }
            aiPlayersChipsHashmap.get(AI).put(fishValues[i], chipLabelList);
            remainingBet = remainingBet % fishValues[i];
        }

        return aiPlayersChipsHashmap.get(AI);
    }

    /**
     * Updates the view
     */
    @Override
    public void updateView() {
        revalidate();
        repaint();
    }


    // ----------------- ADD PLAYER -----------------

    /**
     * Checks if the AI is playing
     * @param AI The AI number
     * @return True if the AI is playing
     */
    public boolean isAIPlaying(int AI) {
        return aiPlayersPlaying.get(AI);
    }

    /**
     * Adds an AI player
     * @param AI The AI number
     */
    public void addAIPlayer(int AI) {
        aiPlayersPlaying.set(AI, true);
        playersPanel.remove(addAIPlayerPanel.get(AI));
        playersPanel.add(aiPlayerPanel.get(AI), AI + 1);
        updateView();
    }

    /**
     * Removes an AI player
     * @param AI The AI number
     */
    public void removeAIPlayer(int AI) {
        aiPlayersPlaying.set(AI, false);
        playersPanel.remove(aiPlayerPanel.get(AI));
        playersPanel.add(addAIPlayerPanel.get(AI), AI + 1);
        updateView();
    }

    // ----------------- SCORE -----------------

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

    /**
     * Updates the AI result
     * @param AI The AI number
     * @param result The result to display
     */
    public void setAIResult(int AI, String result){
        aiPlayerResultLabel.get(AI).setText(result);
    }

    /**
     * Updates the human player's result display
     * @param result The result to display
     */
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

    /**
     * Clears the dealer's cards
     */
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

    /**
     * Adds a card to the AI's panel
     * @param AI The AI number
     * @param cardName The card name
     */
    public void addAICard(int AI, String cardName) {
        if (!aiPlayersPlaying.get(AI)) return;

        JLabel cardLabel = new JLabel(getCardImage(cardName));
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aiPlayerCardsPanel.get(AI).add(cardLabel);

        aiPlayerCardsPanel.get(AI).repaint();
        aiPlayerCardsPanel.get(AI).revalidate();
    }

    /**
     * Clears the AI's cards
     * @param AI The AI number
     */
    public void clearAICards(int AI) {
        if (!aiPlayersPlaying.get(AI)) return;

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

    /**
     * Clears the player's cards
     */
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


    // ---------------- Bets/Balance ----------------

    /**
     * Sets the bet value of the human player
     * @param newBetValue The new bet value
     */
    public void setBetValue(int newBetValue) {
        updateChipLabels(newBetValue);
    }

    /**
     * Gets the bet value of the human player
     * @return The bet value
     */
    public int getBetValue() {
        int betValue = 0;
        int[] fishValues = {100, 25, 10, 5};
        for (int i = 0; i < fishValues.length; i++) {
            betValue += chipsHashmap.get(fishValues[i]).size() * fishValues[i];

        }

        return betValue;
    }

    /**
     * Gets the bet value of the AI player
     * @param AI The AI number
     * @param newBetValue The new bet value
     */
    public void setAIBetValue(int AI, int newBetValue) {
        updateAIChipLabels(AI, newBetValue);
    }

    /**
     * Sets the balance value of the human player
     * @param newBalanceValue The new balance value
     */
    public void setBalanceValue(int newBalanceValue) {
        humanPlayerBalanceLabel.setText("Balance: " + newBalanceValue);
    }

    /**
     * Sets the balance value of the AI player
     * @param AI The AI number
     * @param newBalanceValue The new balance value
     */
    public void setAIBalanceValue(int AI, int newBalanceValue) {
        if (!aiPlayersPlaying.get(AI)) return;
        aiPlayerBalanceLabel.get(AI).setText("Balance: " + newBalanceValue);
    }


    /**
     * Switches the buttons panel
     * @param panel The panel to switch to
     */
    public void setButtonsPanel(int panel) {
        buttonsCardLayout.show(buttonPanel, Integer.toString(panel));
    }


    // ---------------- Action listeners ----------------

    /**
     * Sets the action listener for the First Add AI Player button
     * @param listener The action listener
     */
    public void setAddFirstAIPlayerButtonListener(ActionListener listener) {
        addAIPlayerButton.get(0).addActionListener(listener);
    }

    /**
     * Sets the action listener for the Second Add AI Player button
     * @param listener The action listener
     */
    public void setAddSecondAIPlayerButtonListener(ActionListener listener) {
        addAIPlayerButton.get(1).addActionListener(listener);
    }

    /**
     * Sets the action listener for the Third Add AI Player button
     * @param listener The action listener
     */
    public void setAddThirdAIPlayerButtonListener(ActionListener listener) {
        addAIPlayerButton.get(2).addActionListener(listener);
    }

    /**
     * Sets the action listener for the First Remove AI Player button
     * @param listener The action listener
     */
    public void setRemoveFirstAIPlayerButtonListener(ActionListener listener) {
        removeAIPlayerButton.get(0).addActionListener(listener);
    }

    /**
     * Sets the action listener for the Second Remove AI Player button
     * @param listener The action listener
     */
    public void setRemoveSecondAIPlayerButtonListener(ActionListener listener) {
        removeAIPlayerButton.get(1).addActionListener(listener);
    }

    /**
     * Sets the action listener for the Third Remove AI Player button
     * @param listener The action listener
     */
    public void setRemoveThirdAIPlayerButtonListener(ActionListener listener) {
        removeAIPlayerButton.get(2).addActionListener(listener);
    }

    /**
     * Sets the action listener for the -100 button
     * @param listener The mouse listener
     */
    public void setSub100Listener(MouseListener listener) {
        for (JLabel label : chipsHashmap.get(100)) {
            label.addMouseListener(listener);
        }
    }

    /**
     * Sets the action listener for the -25 button
     * @param listener The mouse listener
     */
    public void setSub25Listener(MouseListener listener) {
        for (JLabel label : chipsHashmap.get(25)) {
            label.addMouseListener(listener);
        }
    }

    /**
     * Sets the action listener for the -10 button
     * @param listener The mouse listener
     */
    public void setSub10Listener(MouseListener listener) {
        for (JLabel label : chipsHashmap.get(10)) {
            label.addMouseListener(listener);
        }
    }

    /**
     * Sets the action listener for the -5 button
     * @param listener The mouse listener
     */
    public void setSub5Listener(MouseListener listener) {
        for (JLabel label : chipsHashmap.get(5)) {
            label.addMouseListener(listener);
        }
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
