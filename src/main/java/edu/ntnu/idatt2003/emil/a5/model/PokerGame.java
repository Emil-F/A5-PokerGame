package edu.ntnu.idatt2003.emil.a5.model;

import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;

import java.util.List;

public class Poker {
  private final DeckOfCards deck;
  private Player player;
  private List<Bot> bots;
  private int round;

  public Poker() {
    this.deck = new DeckOfCards();
    this.round = 1;
  }
}
