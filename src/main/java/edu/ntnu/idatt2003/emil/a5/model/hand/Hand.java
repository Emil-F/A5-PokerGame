package edu.ntnu.idatt2003.emil.a5.model.hand;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Hand {
  private List<PlayingCard> cards;
  private final char[] suit = {'S', 'H', 'D', 'C'};

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public int calculateCardTotal(List<PlayingCard> communityCards) {
    AtomicInteger cardTotal = new AtomicInteger();
    cards.forEach(card -> {
      cardTotal.addAndGet(card.getFace());
    });
    communityCards.forEach(card -> {
      cardTotal.addAndGet(card.getFace());
    });
    return cardTotal.get();
  }

  public void setCards(List<PlayingCard> cards) {
    this.cards = cards;
  }

  public HandCheckResult checkHand(List<PlayingCard> communityCards) {
    List<HandRank> ranks = new ArrayList<>();
    if (hasRoyalFlush(communityCards)) {
      ranks.add(HandRank.ROYAL_FLUSH);
    }
    if (hasStraightFlush(communityCards)) {
      ranks.add(HandRank.STRAIGHT_FLUSH);
    }
    if (hasFourOfAKind(communityCards)) {
      ranks.add(HandRank.FOUR_OF_A_KIND);
    }
    if (hasFullHouse(communityCards)) {
      ranks.add(HandRank.FULL_HOUSE);
    }
    if (hasFlush(communityCards)) {
      ranks.add(HandRank.FLUSH);
    }
    if (hasStraight(communityCards)) {
      ranks.add(HandRank.STRAIGHT);
    }
    if (hasThreeOfAKind(communityCards)) {
      ranks.add(HandRank.THREE_OF_A_KIND);
    }
    if (hasTwoPairs(communityCards)) {
      ranks.add(HandRank.TWO_PAIRS);
    }
    if (hasOnePair(communityCards)) {
      ranks.add(HandRank.ONE_PAIR);
    }
    if (ranks.isEmpty()) {
      ranks.add(HandRank.HIGH_CARD);
    }

    return new HandCheckResult(calculateCardTotal(communityCards), ranks);
  }

  private boolean hasRoyalFlush(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasStraightFlush(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasFourOfAKind(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasFullHouse(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasFlush(List<PlayingCard> communityCards) {
    for(char s: suit) {
      long count = cards.stream().filter(card -> card.getSuit() == s).count();
      if (count >= 5) {
        return true;
      }
    }
    return false;
  }

  private boolean hasStraight(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasThreeOfAKind(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasTwoPairs(List<PlayingCard> communityCards) {
    return false;
  }

  private boolean hasOnePair(List<PlayingCard> communityCards) {
    return false;
  }
}
