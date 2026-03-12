package edu.ntnu.idatt2003.emil.a5.controller;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.PokerGame;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

public class PokerController {
  private static final Logger logger = Logger.getLogger(PokerController.class.getName());
  private final MainController controller;
  private final PokerGame game;

  public PokerController(MainController controller, PokerGame pokerGame) {
    this.controller = controller;
    this.game = pokerGame;
    game.startGame(3);
  }

  public ObservableList<PlayingCard> getCommunityCards() {
    return game.getCommunityCardsObservable();
  }

  // Remove or not
  public void addCommunityCard() {
    System.out.println("Adding community card");
    game.getCommunityCardsObservable().add(new PlayingCard('S', 1));
  }

  public Player getPlayer() {
    return game.getPlayer();
  }

  public List<Bot> getBots() {
    return game.getBots();
  }

  public void startGame() {}

  public void stopGame() {}
}
