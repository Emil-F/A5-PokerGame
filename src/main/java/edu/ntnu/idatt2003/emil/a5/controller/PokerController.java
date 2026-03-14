package edu.ntnu.idatt2003.emil.a5.controller;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.PokerGame;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import edu.ntnu.idatt2003.emil.a5.view.sub.CommunityCards;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

public class PokerController {
  private static final Logger logger = Logger.getLogger(PokerController.class.getName());
  private final MainController controller;
  private final PokerGame game;

  public PokerController(MainController mainController, PokerGame pokerGame) {
    this.controller = mainController;
    this.game = pokerGame;
  }

  public ObservableList<PlayingCard> getCommunityCards() {
    return game.getObservableCommunityCards();
  }

  public Player getPlayer() {
    return game.getPlayer();
  }

  public List<Bot> getBots() {
    return game.getBots();
  }

  public void handleStartGame() {
    game.startGame();
  }

  public void handleCheck() {}

  public void handleCall() {}

  public void handleRaise() {}

  public void handleAllIn() {}

  public void stopGame() {}
}
