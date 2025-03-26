package controller;

import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controller class for the Blackjack game
 */
public class BlackJackController {
    public static final String MENU_VIEW = "MENU";
    public static final String PROFILE_VIEW = "OPTIONS";
    public static final String GAME_VIEW = "GAME";
    public static final String RESULT_VIEW = "RESULT";

    private Game model;
    private BlackJackView view;

    private Player player;

    private int numAiPlayers = 3;

    /**
     * Constructor for the controller
     * 
     * @param model The model for the game
     */
    public BlackJackController(Game model) {
        this.model = model;

        // TODO caricare il player da file
        try (BufferedReader br = new BufferedReader(new FileReader("resources/data/profile.txt"))) {

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String fullFile = sb.toString();

            String[] splitFile = fullFile.split("\n");

            player = new Player(splitFile[0]);
            player.setLevel(Integer.parseInt(splitFile[1]));
            player.setBalance(Integer.parseInt(splitFile[2]));
            player.setGamesPlayed(Integer.parseInt(splitFile[3]));
            player.setGamesWon(Integer.parseInt(splitFile[4]));
            player.setGamesLost(Integer.parseInt(splitFile[5]));

        } catch (Exception e) {
            System.err.println(e.toString());
            player = new Player("Player");
        }

        model.setPlayer(player);

        // Initialize the view
        initializeView();

        // Register action listeners
        registerActionListeners();
    }

    /**
     * Initialize the view
     */
    private void initializeView() {
        // Create main frame
        view = new BlackJackView("Blackjack Game");

        // Create individual views
        MenuView menuView = new MenuView();
        GameView gameView = new GameView();
        ResultView resultView = new ResultView();
        ProfileView profileView = new ProfileView(player);

        // Add views to the main frame
        view.addView(MENU_VIEW, menuView);
        view.addView(GAME_VIEW, gameView);
        view.addView(RESULT_VIEW, resultView);
        view.addView(PROFILE_VIEW, profileView);

        // Register game view as observer of the model
        model.addObserver(view);

        // Start with menu
        view.start(MENU_VIEW);

    }

    /**
     * Register action listeners for the views
     */
    private void registerActionListeners() {
        // Get views
        MenuView menuView = (MenuView) view.getView(MENU_VIEW);
        GameView gameView = (GameView) view.getView(GAME_VIEW);
        ResultView resultView = (ResultView) view.getView(RESULT_VIEW);
        ProfileView profileView = (ProfileView) view.getView(PROFILE_VIEW);

        // Menu view listeners
        menuView.setNewGameButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        menuView.setProfileButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openProfile();
            }
        });

        menuView.setExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Game view listeners
        // ----------------- ADD NEW PLAYER -----------------
        gameView.setAddFirstAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.addAIPlayer(0);
            }
        });

        gameView.setAddSecondAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.addAIPlayer(1);
            }
        });

        gameView.setAddThirdAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.addAIPlayer(2);
            }
        });

        gameView.setRemoveFirstAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.removeAIPlayer(0);
            }
        });

        gameView.setRemoveSecondAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.removeAIPlayer(1);
            }
        });

        gameView.setRemoveThirdAIPlayerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;
                gameView.removeAIPlayer(2);
            }
        });

        // ----------------- BETTING -----------------
        gameView.setAdd5ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() + 5;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }
        });

        gameView.setAdd10ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() + 10;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }
        });

        gameView.setAdd25ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() + 25;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }
        });

        gameView.setHitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerHit();
            }
        });

        gameView.setStandButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerStand();
            }
        });

        gameView.setStartGameButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dealCards();
            }
        });
        gameView.setMenuButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });

        gameView.setClearButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearGame();
            }
        });

        // Options View listener
        profileView.setMuteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                profileView.toggleMute();
            }
        });

        profileView.setVolumeSliderListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int volumeLevel = profileView.getVolumeLevel();
                profileView.setVolumeLevel(volumeLevel);

            }
        });

        profileView.setHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnToMenu();
            }
        });

    }

    /**
     * Set listeners for removing the bets
     */
    private void setSubChipsListeners() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        gameView.setSub5Listener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() - 5;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        gameView.setSub10Listener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() - 10;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        gameView.setSub25Listener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() - 25;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        gameView.setSub100Listener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (model.getState() != Game.GameState.NOT_STARTED)
                    return;

                int newBetValue = model.getHumanPlayerBet() - 100;
                model.setHumanPlayerBet(newBetValue);
                gameView.setBetValue(newBetValue);
                gameView.setBalanceValue(model.getPlayerBalance());
                setSubChipsListeners();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

    }

    /**
     * Method to switch views
     * 
     * @param viewName The view to show
     */
    public void switchView(String viewName) {
        view.showView(viewName);
    }

    /**
     * Start a new game
     */
    private void startNewGame() {
        // Reset the model
        model.startNewGame(numAiPlayers);

        // Update game view
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Switch to game view
        switchView(GAME_VIEW);
        gameView.setButtonsPanel(1);

    }

    /**
     * Deal the cards
     */
    private void dealCards() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Display the AI bets
        for (int i = 0; i < numAiPlayers; i++) {
            gameView.setAIBetValue(i, model.getAIBet(i));
            gameView.setAIBalanceValue(i, model.getAIBalance(i));
        }

        // Get Bet
        model.setHumanPlayerBet(gameView.getBetValue());
        model.stopBets();

        gameView.setButtonsPanel(2);

        // Show initial cards
        displayInitialCards(gameView);
    }

    /**
     * Display initial cards
     * 
     * @param gameView The gameView where to display the cards
     */
    private void displayInitialCards(GameView gameView) {
        // Clear existing cards
        // In a real implementation, you'd clear and re-add card components

        // Add dealer's cards
        gameView.addDealerCard("Face_Down");
        gameView.addDealerCard(model.getDealerVisibleCard().toString());
        gameView.updateDealerScore(model.getDealerVisibleScore());

        // Add player's cards
        for (Card card : model.getPlayerCards()) {
            gameView.addPlayerCard(card.toString());
        }
        gameView.updatePlayerScore(model.getPlayerScore());

        // Add opponents' cards
        for (int i = 0; i < model.getNumOpponents(); i++) {
            for (Card card : model.getAIPlayerCards(i)) {
                gameView.addAICard(i, card.toString());
            }
            gameView.updateAIScore(i, model.getAIPlayerScore(i));

        }
    }

    /**
     * Player hits
     */
    private void playerHit() {
        model.playerHit();

        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Add the new card to the view
        Card newCard = model.getPlayerCards().get(model.getPlayerCards().size() - 1);
        gameView.addPlayerCard(newCard.toString());
        gameView.updatePlayerScore(model.getPlayerScore());

        // Check if player busts
        if (model.isPlayerBusted()) {
            repaintCards();
            endGame("Player Busted! Dealer Wins!");
        }
    }

    /**
     * Player stands
     */
    private void playerStand() {
        model.playerStand();

        repaintCards();

        // Determine the winner
        String result;
        if (model.isDealerBusted()) {
            result = "Dealer Busted! Player Wins!";
        } else if (model.getPlayerScore() > model.getDealerScore()) {
            result = "Player Wins!";
        } else if (model.getPlayerScore() < model.getDealerScore()) {
            result = "Dealer Wins!";
        } else {
            result = "Push! It's a Tie!";
        }

        endGame(result);
    }

    /**
     * Repaint all the cards in the view
     */
    private void repaintCards() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);
        // Player
        gameView.clearPlayerCards();
        for (Card card : model.getPlayerCards()) {
            gameView.addPlayerCard(card.toString());
        }
        gameView.updatePlayerScore(model.getPlayerScore());

        // Dealer
        gameView.clearDealerCards();
        for (Card card : model.getDealerCards()) {
            gameView.addDealerCard(card.toString());
        }
        gameView.updateDealerScore(model.getDealerScore());

        // AI
        for (int i = 0; i < model.getNumOpponents(); i++) {
            gameView.clearAICards(i);
            for (Card card : model.getAIPlayerCards(i)) {
                gameView.addAICard(i, card.toString());
            }
            gameView.updateAIScore(i, model.getAIPlayerScore(i));
        }
    }

    /**
     * End the game and show result
     */
    private void endGame(String result) {
        GameView gameView = (GameView) view.getView(GAME_VIEW);
        ProfileView profileView = (ProfileView) view.getView(PROFILE_VIEW);

        gameView.setPlayerResult(model.getPlayerResult());
        gameView.setBalanceValue(model.getPlayerBalance());

        for (int i = 0; i < model.getNumOpponents(); i++) {
            gameView.setAIResult(i, model.getAIResult(i));
            gameView.setAIBalanceValue(i, model.getAIBalance(i));
        }
        gameView.setButtonsPanel(3);

        profileView.setGamesPlayedLabel(player.getGamesPlayed());
        profileView.setGamesWonLabel(player.getGamesWon());
        profileView.setGamesLostLabel(player.getGamesLost());

    }

    /**
     * Clear the view to initiate a new Game
     */
    private void clearGame() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Clear cards
        gameView.clearDealerCards();
        gameView.clearPlayerCards();
        gameView.setBetValue(0);
        model.setHumanPlayerBet(0);
        for (int i = 0; i < model.getNumOpponents(); i++) {
            gameView.clearAICards(i);
            gameView.setAIBetValue(i, 0);
        }

        gameView.setButtonsPanel(1);

        startNewGame();
    }

    /**
     * Return to the main menu
     */
    private void returnToMenu() {
        switchView(MENU_VIEW);
    }

    /**
     * Opens the options view
     */
    private void openProfile() {
        switchView(PROFILE_VIEW);
    }
}
