package controller;


import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BlackJackController {
    public static final String MENU_VIEW = "MENU";
    public static final String OPTIONS_VIEW = "OPTIONS";
    public static final String GAME_VIEW = "GAME";
    public static final String RESULT_VIEW = "RESULT";

    private BlackJackModel model;
    private BlackJackView view;

    public BlackJackController(BlackJackModel model) {
        this.model = model;
        //

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

        gameView.setMenuButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenu();
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
                System.out.println("Mutato");
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
        model.newGame();

        // Update game view
        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Show initial cards
        displayInitialCards(gameView);

        // Switch to game view
        switchView(GAME_VIEW);
    }

    /**
     * Display initial cards
     */
    private void displayInitialCards(GameView gameView) {
        // Clear existing cards
        // In a real implementation, you'd clear and re-add card components

        // Add dealer's cards
        gameView.addDealerCard("Face Down");
        gameView.addDealerCard(model.getDealerVisibleCard().toString());
        gameView.updateDealerScore(model.getDealerVisibleScore());

        // Add player's cards
        for (Card card : model.getPlayerCards()) {
            gameView.addPlayerCard(card.toString());
        }
        gameView.updatePlayerScore(model.getPlayerScore());
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
            endGame("Player Busted! Dealer Wins!");
        }
    }

    /**
     * Player stands
     */
    private void playerStand() {
        model.dealerPlay();

        GameView gameView = (GameView) view.getView(GAME_VIEW);

        // Update dealer cards and score
        // In a real implementation, you'd clear and re-add all dealer cards
        for (Card card : model.getDealerCards()) {
            // Skip the first card which is already displayed
            if (card != model.getDealerCards().get(0)) {
                gameView.addDealerCard(card.toString());
            }
        }
        gameView.updateDealerScore(model.getDealerScore());

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
     * End the game and show result
     */
    private void endGame(String result) {
        ResultView resultView = (ResultView) view.getView(RESULT_VIEW);
        resultView.setResult(result);
        switchView(RESULT_VIEW);
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
