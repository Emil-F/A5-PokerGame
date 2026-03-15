package edu.ntnu.idatt2003.emil.a5.controller;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.poker.PokerGame;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

public class PokerController {
  private static final Logger logger = Logger.getLogger(PokerController.class.getName());

  private final MainController controller;
  private final PokerGame game;

  private ObservableList<PlayingCard> obsCommunityCards = FXCollections.observableArrayList();
  private ObservableList<PlayingCard> obsPlayerCards = FXCollections.observableArrayList();

  private StringProperty currentRound = new SimpleStringProperty();

  public PokerController(MainController mainController, PokerGame pokerGame) {
    this.controller = mainController;
    this.game = pokerGame;
    obsCommunityCards.setAll(game.getCommunityCards());
    obsPlayerCards.setAll(game.getPlayer().getCards());
  }

  public ObservableList<PlayingCard> getObsCommunityCards() {
    return obsCommunityCards;
  }

  public ObservableList<PlayingCard> getObsPlayerCards() {
    return obsPlayerCards;
  }

  public StringProperty getCurrentRound() {
    return currentRound;
  }

  public Player getPlayer() {
    return game.getPlayer();
  }

  public List<Bot> getBots() {
    return game.getBots();
  }

  public void refreshUI() {
    obsCommunityCards.setAll(game.getCommunityCards());
    obsPlayerCards.setAll(game.getPlayer().getCards());
    switch (game.getCurrentRound().getRoundState().getCurrentState()) {
      case PRE_FLOP -> this.currentRound.setValue("Pre Flop");
      case FLOP     -> this.currentRound.setValue("Flop");
      case TURN     -> this.currentRound.setValue("Turn");
      case RIVER    -> this.currentRound.setValue("River");
      case SHOWDOWN -> this.currentRound.setValue("Showdown");
      default       -> this.currentRound.setValue("Error");
    }
  }

  public void handleStartGame() throws InterruptedException {
    game.startGame();
    refreshUI();
  }

  public void handleFlop() {
    refreshUI();
  }

  public void handleCheck() {}

  public void handleCall() {}

  public void handleRaise() {}

  public void handleAllIn() {}

  public void stopGame() {}
}
