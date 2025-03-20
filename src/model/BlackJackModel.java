//package model;
//
//import java.util.Observable;
//
//public class BlackJackModel extends Observable {
//    private Game game;
//    private Player player;
//    private Player dealer;
//
//    public BlackJackModel() {
//        player = new Player("Player");
//        dealer = new Player("Dealer");
//        dealer.setIsDealer(true);
//        game = new Game(new Deck(), player, dealer);
//    }
//
//    public void startNewGame(int betAmount) {
//        player.placeBet(betAmount);
//        game.startNewGame();
//
//        setChanged();
//        notifyObservers(new GameEvent("NEW_GAME"));
//    }
//
//// Other methods that delegate to the appropriate classes
//
//}
