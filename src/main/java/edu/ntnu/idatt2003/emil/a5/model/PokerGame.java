package edu.ntnu.idatt2003.emil.a5.model;

import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
  private final DeckOfCards deck;
  private Player player;
  private final List<Bot> bots;
  private final List<PlayingCard> communityCards;
  // Todo: implement pot system
  // private int pot;
  private int round;

  public PokerGame() {
    this.deck = new DeckOfCards();
    this.player = new Player("Emil", 50000);
    this.bots = new ArrayList<>();
    this.communityCards = new ArrayList<>();
    this.round = 1;
  }

  public DeckOfCards getDeck() {
    return deck;
  }

  public Player getPlayer() {
    return player;
  }

  public List<Bot> getBots() {
    return bots;
  }

  public List<PlayingCard> getCommunityCards() {
    return communityCards;
  }

  public int getRound() {
    return round;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void populateBots(int botCount) {
    for(int i = 1; i <= botCount; i++) {
      this.bots.add(new Bot("Bot" + i));
    }
  }

  public void startGame() {
    dealCards();
  }

  public void stopGame() {}

  public void dealCards() {
    deck.stockCards();
    this.communityCards.clear();
    this.player.getHand().getCards().clear();

    this.communityCards.addAll(deck.dealHand(5));
    this.player.getHand().getCards().addAll(deck.dealHand(5));
    this.player.getHand().getCards().addAll(this.communityCards);

    this.player.getHand().getCards().forEach(System.out::println);
  }

  public void determineWinner() {}
}
