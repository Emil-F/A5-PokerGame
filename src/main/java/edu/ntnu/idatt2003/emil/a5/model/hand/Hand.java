package edu.ntnu.idatt2003.emil.a5.model.hand;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Hand {
  private static final Logger logger = Logger.getLogger(Hand.class.getName());
  private List<PlayingCard> cards;
  private final ObservableList<PlayingCard> obsCards = FXCollections.observableArrayList();
  private final char[] suit = {'S', 'H', 'D', 'C'};

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

  public List<PlayingCard> getCards() {
    return cards;
  }

  public ObservableList<PlayingCard> getObsCards() {
    return obsCards;
  }

  public void setCards(List<PlayingCard> cards) {
    this.cards = cards;
    this.obsCards.setAll(cards);
  }

  /**
   * <p>Calculates the hand's total card value</p>
   *
   * @param combinedCards hand + communityCards.
   * @return the hand's total card value as an {@link Integer}
   */
  public int calculateCardTotal(List<PlayingCard> combinedCards) {
    Objects.requireNonNull(combinedCards, "communityCards is null");

    AtomicInteger cardTotal = new AtomicInteger();
    combinedCards.forEach(card -> {
      cardTotal.addAndGet(card.getFace());
    });
    return cardTotal.get();
  }

  /**
   * <p>
   * Goes through all possible combinations of a poker hand and
   * determines if the current hand possesses cards with one of
   * the following combinations:
   * </p>
   *
   * <li>Royal Flush</li>
   * <li>Straight Flush</li>
   * <li>Four of a Kind</li>
   * <li>Full House</li>
   * <li>Straight</li>
   * <li>Three of a Kind</li>
   * <li>Two Pairs</li>
   * <li>One Pair</li>
   * <li>High Card</li>
   *
   * @param communityCards cards shared by all the players in the game.
   * @return the hands total card value and its combinations as a {@link HandCheckResult}.
   */
  public HandCheckResult checkHand(List<PlayingCard> communityCards) {
    Objects.requireNonNull(communityCards, "communityCards is null");
    List<PlayingCard> combinedCards = new ArrayList<>();
    combinedCards.addAll(this.cards);
    combinedCards.addAll(communityCards);

    List<HandRank> ranks = new ArrayList<>();
    if (hasRoyalFlush(combinedCards)) {
      ranks.add(HandRank.ROYAL_FLUSH);
    }
    if (hasStraightFlush(combinedCards)) {
      ranks.add(HandRank.STRAIGHT_FLUSH);
    }
    if (hasFourOfAKind(combinedCards)) {
      ranks.add(HandRank.FOUR_OF_A_KIND);
    }
    if (hasFullHouse(combinedCards)) {
      ranks.add(HandRank.FULL_HOUSE);
    }
    if (hasFlush(combinedCards)) {
      ranks.add(HandRank.FLUSH);
    }
    if (hasStraight(combinedCards)) {
      ranks.add(HandRank.STRAIGHT);
    }
    if (hasThreeOfAKind(combinedCards)) {
      ranks.add(HandRank.THREE_OF_A_KIND);
    }
    if (hasTwoPairs(combinedCards)) {
      ranks.add(HandRank.TWO_PAIRS);
    }
    if (hasOnePair(combinedCards)) {
      ranks.add(HandRank.ONE_PAIR);
    }
    if (ranks.isEmpty()) {
      ranks.add(HandRank.HIGH_CARD);
    }

    return new HandCheckResult(calculateCardTotal(combinedCards), ranks);
  }

  /**
   * <p>Checks if the hand has the rank Royal Flush.</p>
   * <p>
   *   A Royal Flush consists of cards with faces [1, 10, 11, 12, 13], all with the same suit.
   * </p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Royal Flush, else false.
   */
  protected boolean hasRoyalFlush(List<PlayingCard> combinedCards) {
    for (char suit : this.suit) {
      Set<Integer> faces = combinedCards.stream()
          .filter(card -> card.getSuit() == suit)
          .map(PlayingCard::getFace)
          .collect(Collectors.toSet());

      if (faces.containsAll(List.of(1, 10, 11, 12, 13))) {
        return true;
      }
    }

    return false;
  }

  protected boolean hasStraightFlush(List<PlayingCard> combinedCards) {
    for (char suit : this.suit) {
      Set<Integer> faces = combinedCards.stream()
          .filter(card -> card.getSuit() == suit)
          .map(PlayingCard::getFace)
          .collect(Collectors.toSet());

      if (faces.containsAll(List.of(7, 8, 9, 10, 11))) {
        return true;
      }
    }

    return false;
  }

  protected boolean hasFourOfAKind(List<PlayingCard> combinedCards) {
    int maxDistinctFaces = 3;
    long distinctFacesCount = combinedCards.stream()
        .map(PlayingCard::getFace)
        .distinct()
        .count();

    return distinctFacesCount <= maxDistinctFaces;
  }

  protected boolean hasFullHouse(List<PlayingCard> combinedCards) {
    return false;
  }

  protected boolean hasFlush(List<PlayingCard> combinedCards) {
    for (char suit : this.suit) {
      Set<Integer> faces = combinedCards.stream()
          .filter(card -> card.getSuit() == suit)
          .map(PlayingCard::getFace)
          .collect(Collectors.toSet());

      if (faces.size() == 5) {
        return true;
      }
    }

    return false;
  }

  protected boolean hasStraight(List<PlayingCard> combinedCards) {
    return false;
  }

  protected boolean hasThreeOfAKind(List<PlayingCard> combinedCards) {
    int maxDistinctFaces = 4;
    long distinctFacesCount = combinedCards.stream()
        .map(PlayingCard::getFace)
        .distinct()
        .count();

    return distinctFacesCount <= maxDistinctFaces;
  }

  protected boolean hasTwoPairs(List<PlayingCard> combinedCards) {
    return false;
  }

  protected boolean hasOnePair(List<PlayingCard> combinedCards) {
    return false;
  }
}
