package controller;


import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlackJackController {
    public static final String MENU_VIEW = "MENU";
    public static final String OPTIONS_VIEW = "OPTIONS";
    public static final String GAME_VIEW = "GAME";
    public static final String RESULT_VIEW = "RESULT";

    private Game model;
    private BlackJackView view;

    public BlackJackController(Game model) {
        this.model = model;

        // Initialize the view
        initializeView();

        // Register action listeners
        registerActionListeners();
    }

    private void initializeView() {
        // Create main frame
        view = new BlackJackView("Blackjack Game");

        // Create individual views
        MenuView menuView = new MenuView();
        GameView gameView = new GameView();
        ResultView resultView = new ResultView();
        OptionsView optionsView = new OptionsView();

        // Add views to the main frame
        view.addView(MENU_VIEW, menuView);
        view.addView(GAME_VIEW, gameView);
        view.addView(RESULT_VIEW, resultView);
        view.addView(OPTIONS_VIEW, optionsView);

        // Register game view as observer of the model
        model.addObserver(gameView);

        // Start with menu
        view.start(MENU_VIEW);
    }

    private void registerActionListeners() {
        // Get views
        MenuView menuView = (MenuView) view.getView(MENU_VIEW);
        GameView gameView = (GameView) view.getView(GAME_VIEW);
        ResultView resultView = (ResultView) view.getView(RESULT_VIEW);
        OptionsView optionsView = (OptionsView) view.getView(OPTIONS_VIEW);

        // Menu view listeners
        menuView.setNewGameButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        menuView.setOptionsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openOptions();
            }
        });

        menuView.setExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Game view listeners
        gameView.setSub10ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() - 10;
                gameView.setBetValue(Math.max(newBetValue, 0));
            }
        });

        gameView.setSub5ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() - 5;
                gameView.setBetValue(Math.max(newBetValue, 0));
            }
        });

        gameView.setSub1ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() - 1;
                gameView.setBetValue(Math.max(newBetValue, 0));
            }
        });

        gameView.setAdd5ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() + 5;
                gameView.setBetValue(newBetValue);
            }
        });

        gameView.setAdd10ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() + 10;
                gameView.setBetValue(newBetValue);
            }
        });

        gameView.setAdd25ButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newBetValue = gameView.getBetValue() + 25;
                gameView.setBetValue(newBetValue);
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

        // Result view listeners
        resultView.setPlayAgainButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        resultView.setMenuButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
            }
        });

        // Options View listener
        optionsView.setMuteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                optionsView.toggleMute();
            }
        });

        optionsView.setVolumeSliderListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int volumeLevel = optionsView.getVolumeValue();
                optionsView.setVolumeLevel(volumeLevel);

            }
        });

        optionsView.setHomeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnToMenu();
            }
        });
    }

    /**
     * Method to switch views
     */
    public void switchView(String viewName) {
        view.showView(viewName);
    }

    /**
     * Start a new game
     */
    private void startNewGame() {
        // Reset the model
        model.startNewGame(2);

        // Update game view
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Switch to game view
        switchView(GAME_VIEW);
        gameView.setButtonsPanel(1);

    }


    private void dealCards() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Get Bet
//        betValue = gameView.placeBet();

        gameView.setButtonsPanel(2);

        // Show initial cards
        displayInitialCards(gameView);
    }

    /**
     * Display initial cards
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
            for (Card card: model.getAIPlayerCards(i)) {
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

    private void repaintCards() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);
        // Player
        gameView.clearPlayerCards();
        for (Card card: model.getPlayerCards()) {
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
            for (Card card: model.getAIPlayerCards(i)) {
                gameView.addAICard(i, card.toString());
            }
            gameView.updateAIScore(i, model.getAIPlayerScore(i));
        }
    }

    /**
     * End the game and show result
     */
    private void endGame(String result) {
//        model.getBettingResult(betValue, result);

//        ResultView resultView = (ResultView) view.getView(RESULT_VIEW);
//        resultView.setResult(result);
//        switchView(RESULT_VIEW);

        System.out.println(result);

        GameView gameView = (GameView) view.getView(GAME_VIEW);

        gameView.setPlayerResult(model.getPlayerResult());

        for (int i = 0; i < model.getNumOpponents(); i++) {
            gameView.setAIResult(i, model.getAIResult(i));
        }
        gameView.setButtonsPanel(3);

    }

    private void clearGame() {
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Clear cards
        gameView.clearDealerCards();
        gameView.clearPlayerCards();
        for (int i = 0; i < model.getNumOpponents(); i++) {
            gameView.clearAICards(i);
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
    private void openOptions() {
        switchView(OPTIONS_VIEW);
    }
}
