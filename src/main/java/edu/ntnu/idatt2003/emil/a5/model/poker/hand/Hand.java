package edu.ntnu.idatt2003.emil.a5.model.poker.hand;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * This class represent a player's hand in the game of Poker.
 */
public class Hand {
  private static final Logger logger = Logger.getLogger(Hand.class.getName());
  private List<PlayingCard> cards;
  private final char[] suit = {'S', 'H', 'D', 'C'};

  /**
   * Constructs a new hand to be owned by a User.
   */
  public Hand() {
    this.cards = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder handString = new StringBuilder();
    handString.append("Hand: [");
    for (int i = 0; i < this.cards.size(); i++) {
      if (i == this.cards.size() - 1) {
        handString.append(this.cards.get(i).getAsString());
        continue;
      }
      handString.append(this.cards.get(i).getAsString()).append(", ");
    }
    handString.append("]");
    return handString.toString();
  }

//  public List<PlayingCard> getCards() {
//    return cards;
//  }
//
//  public void setCards(List<PlayingCard> cards) {
//    this.cards = cards;
//  }
//
//  /**
//   * <p>Calculates the hand's total card value</p>
//   *
//   * @param communityCards cards shared by all players.
//   * @return the hand's total card value as an {@link Integer}
//   */
//  public int calculateCardTotal(List<PlayingCard> communityCards) {
//    List<PlayingCard> allCards = new ArrayList<>(this.cards);
//    allCards.addAll(communityCards);
//
//    AtomicInteger cardTotal = new AtomicInteger();
//    allCards.forEach(card -> {
//      cardTotal.addAndGet(card.getFace());
//    });
//    return cardTotal.get();
//  }
}
