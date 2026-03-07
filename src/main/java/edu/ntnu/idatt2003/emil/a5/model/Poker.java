package edu.ntnu.idatt2003.emil.a5.model;

import java.util.List;

public class Poker {
  private final DeckOfCards deck = new DeckOfCards();
  private List<PlayingCard> pokerDeck;
  private int round;

  public Poker() {
    this.round = 1;
    this.pokerDeck = deck.getCards();
  }
}
