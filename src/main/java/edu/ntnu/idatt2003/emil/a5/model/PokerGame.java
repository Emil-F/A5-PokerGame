package edu.ntnu.idatt2003.emil.a5.model;

import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class PokerGame {
  private final static Logger LOGGER = Logger.getLogger(PokerGame.class.getName());
  private final DeckOfCards deck;
  private final List<PlayingCard> communityCards;
  private Player player;
  private final List<Bot> bots;
  // Todo: implement pot system
  // private int pot;
  private int round;

  private final ObservableList<PlayingCard> observableCommunityCards = FXCollections.observableArrayList();

  public PokerGame() {
    this.deck = new DeckOfCards();
    this.player = new Player("Emil", 50000);
    this.bots = new ArrayList<>();
    this.communityCards = new ArrayList<>();
    this.round = 1;
    populateBots(3);
  }

  public String getCommunityCardsString() {
    StringBuilder communityCardsString = new StringBuilder();
    communityCardsString.append("Community Cards: [");
    for (int i = 0; i < this.communityCards.size(); i++) {
      if (i == this.communityCards.size() - 1) {
        communityCardsString.append(this.communityCards.get(i).getAsString());
        continue;
      }
      communityCardsString.append(this.communityCards.get(i).getAsString()).append(", ");
    }
    communityCardsString.append("]");
    return communityCardsString.toString();
  }

  public Player getPlayer() {
    return player;
  }

  public List<Bot> getBots() {
    return bots;
  }

  public int getRound() {
    return round;
  }

  public ObservableList<PlayingCard> getObservableCommunityCards() {
    return observableCommunityCards;
  }

  public void setPlayer(Player player) {
    this.player = Objects.requireNonNull(player, "player is null");
  }

  public void populateBots(int botCount) {
    if (botCount > 22) {
      throw new IllegalArgumentException("Bot count can't be greater than 22.");
    }
    for(int i = 1; i <= botCount; i++) {
      this.bots.add(new Bot("Bot" + i));
    }
  }

  public void startGame() {
    dealCards();

    System.out.println(getCommunityCardsString());
    System.out.println(this.player.toString());
    this.bots.forEach(System.out::println);
  }

  public void stopGame() {
    determineWinner();
  }

  public void dealCards() {
    this.deck.restockCards();
    this.communityCards.clear();
    this.observableCommunityCards.clear();

    this.communityCards.addAll(deck.dealHand(5));

    this.player.getHand().setCards(deck.dealHand(2));
    for(Bot bot : this.bots) {
      bot.getHand().setCards(deck.dealHand(2));
    }

    this.observableCommunityCards.addAll(this.communityCards);
  }

  public void determineWinner() {}

  private void preFlopRound() {}

  private void FlopRound() {}

  private void TurnRound() {}

  private void RiverRound() {}

  private void ShowDown() {}
}
