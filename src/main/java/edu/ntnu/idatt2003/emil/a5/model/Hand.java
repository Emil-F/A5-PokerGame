package edu.ntnu.idatt2003.emil.a5.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
  private List<PlayingCard> cards;
  private final char[] suit = {'S', 'H', 'D', 'C'};

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public void setCards(List<PlayingCard> cards) {
    this.cards = cards;
  }

  public String checkHand() {
    if (checkFlush()) {
      return "Flush";
    }
    return "No combination found";
  }

  private boolean checkFlush() {
    for(char s: suit) {
      long count = cards.stream().filter(card -> card.getSuit() == s).count();
      if (count >= 5) {
        return true;
      }
    }
    return false;
  }
}
