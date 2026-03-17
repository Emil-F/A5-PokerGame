package edu.ntnu.idatt2003.emil.a5.model.poker.hand;

import edu.ntnu.idatt2003.emil.a5.model.poker.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.users.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HandChecker {
  private final static Logger logger = Logger.getLogger(HandChecker.class.getName());
  private final char[] suits = {'S', 'H', 'D', 'C'};

  public HandChecker() {}

  /**
   * <p>Calculates the hand's total card value</p>
   *
   * @param p participant.
   * @return the hand's total card value as an {@link Integer}
   */
  public int calculateCardTotal(Participant p) {
    AtomicInteger cardTotal = new AtomicInteger();
    p.getCards().forEach(card -> {
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
   * @param participant a participant in the game.
   * @param communityCards cards shared by all players.
   * @return the hands total card value and its combinations as a {@link HandCheckResult}.
   */
  public HandCheckResult checkHand(Participant participant, List<PlayingCard> communityCards) {
    List<PlayingCard> combinedCards = new ArrayList<>();
    combinedCards.addAll(participant.getCards());
    combinedCards.addAll(communityCards);

    if (hasRoyalFlush(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.ROYAL_FLUSH);
    }
    if (hasStraightFlush(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.STRAIGHT_FLUSH);
    }
    if (hasFourOfAKind(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.FOUR_OF_A_KIND);
    }
    if (hasFullHouse(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.FULL_HOUSE);
    }
    if (hasFlush(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.FLUSH);
    }
    if (hasStraight(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.STRAIGHT);
    }
    if (hasThreeOfAKind(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.THREE_OF_A_KIND);
    }
    if (hasTwoPair(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.TWO_PAIR);
    }
    if (hasOnePair(combinedCards)) {
      return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.ONE_PAIR);
    }
    return new HandCheckResult(participant, calculateCardTotal(participant), HandRank.HIGH_CARD);
  }

  /**
   * <p>Checks if the hand has the rank Royal Flush.</p>
   * <p>A Royal Flush consists of cards with faces [1, 10, 11, 12, 13],
   * all with the same suit (Flush).</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Royal Flush, otherwise false.
   */
  protected boolean hasRoyalFlush(List<PlayingCard> combinedCards) {
    for (char suit : this.suits) {
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

  /**
   * <p>Checks if the hand has the rank Straight Flush</p>
   *
   * <p>A Straight Flush consists of five cards with a sequential rank
   * e.g. [1, 2, 3, 4, 5] or [5, 6, 7, 8, 9] (Straight),
   * all with the same suit (Flush).</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Straight Flush, otherwise false.
   */
  protected boolean hasStraightFlush(List<PlayingCard> combinedCards) {
    return hasFlush(getStraight(combinedCards));
  }

  /**
   * <p>Checks if the hand has the rank Four of a Kind</p>
   *
   * <p>Four of a Kind consists of four cards of the same face.</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has Four of a Kind, otherwise false.
   */
  protected boolean hasFourOfAKind(List<PlayingCard> combinedCards) {
    List<PlayingCard> fourOfAKind = new ArrayList<>();
    for(PlayingCard card : combinedCards) {
      if (combinedCards.stream().filter(c -> c.getFace() == card.getFace()).count() == 4) {
        return true;
      };
    }
    return false;
  }

  /**
   * <p>Checks if the hand has the rank Full House</p>
   *
   * <p>A Full House consists of three cards of the same face (Three of a Kind) and one pair.</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Full House, otherwise false.
   */
  protected boolean hasFullHouse(List<PlayingCard> combinedCards) {
    List<PlayingCard> threeOfAKind = getThreeOfAKind(combinedCards).stream().distinct().toList();
    if (threeOfAKind.isEmpty()) {
      return false;
    }

    List<PlayingCard> fullHouse = combinedCards.stream().filter(
      c -> c.getFace() != threeOfAKind.getFirst().getFace()
    ).toList();
    return hasOnePair(fullHouse);
  }

  /**
   * <p>Checks if the hand has the rank Flush</p>
   *
   * <p>A Flush consists of five cards, all with the same suit.</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Flush, otherwise false.
   */
  protected boolean hasFlush(List<PlayingCard> combinedCards) {
    List<Character> combinedSuits = combinedCards.stream()
      .map(PlayingCard::getSuit)
      .toList();

    for (char suit : this.suits) {
      if (combinedSuits.stream().filter(s -> s.equals(suit)).count() >= 5) {
        return true;
      };
    }

    return false;
  }

  /**
   * <p>Checks if the hand has the rank Straight</p>
   *
   * <p>A Straight consists of five cards with its faces in sequential order
   * e.g. [1, 2, 3, 4, 5] or [5, 6, 7, 8, 9].</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Straight, otherwise false.
   */
  protected boolean hasStraight(List<PlayingCard> combinedCards) {
    return getStraight(combinedCards).size() >= 5;
  }

  /**
   * <p>Checks if the hand has the rank Three of a Kind</p>
   *
   * <p>Three of a Kind consists of three cards, all with the same face.</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has a Three of a Kind, otherwise false.
   */
  protected boolean hasThreeOfAKind(List<PlayingCard> combinedCards) {
    List<PlayingCard> threeOfAKind = getThreeOfAKind(combinedCards);
    return getThreeOfAKind(combinedCards).size() == 3;
  }

  /**
   * <p>Checks if the hand has the rank Two Pairs</p>
   *
   * <p>Two Pairs consists of four cards with pairs of two, with the same face.
   * e.g. [3, 3, 10, 10]</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has Two Pairs, otherwise false.
   */
  protected boolean hasTwoPair(List<PlayingCard> combinedCards) {
    List<PlayingCard> pairs = getPairs(combinedCards);

    return pairs.size() > 2;
  }

  /**
   * <p>Checks if the hand has the rank One Pair</p>
   *
   * <p>One Pair consists of two cards with the same face e.g. [3, 3].</p>
   *
   * @param combinedCards hand + communityCards
   * @return true if the hand has Two Pairs, otherwise false.
   */
  protected boolean hasOnePair(List<PlayingCard> combinedCards) {
    List<PlayingCard> pairs = getPairs(combinedCards);

    if (pairs.size() > 2) {
      logger.warning("hasOnePair() was called even though there are more than one pair present");
    }

    return pairs.size() == 2;
  }

  private List<PlayingCard> getStraight(List<PlayingCard> combinedCards) {
    List<Integer> faces = combinedCards.stream()
      .map(PlayingCard::getFace)
      .distinct()
      .sorted()
      .toList();

    List<Integer> sequentialIdx = new ArrayList<>();
    int lastFaceIdx = 0;
    int lastFace = faces.getFirst();
    for (int i = 1; i < faces.size(); i++) {
      if (faces.get(i) - lastFace == 1) {
        if (sequentialIdx.isEmpty()) {
          sequentialIdx.add(lastFaceIdx);
        }
        sequentialIdx.add(i);

        if (sequentialIdx.size() == 5) {
          break;
        }
      } else {
        sequentialIdx.clear();
      }
      lastFace = faces.get(i);
      lastFaceIdx = i;
    }

    return sequentialIdx.stream()
      .map(combinedCards::get).toList();
  }

  private List<PlayingCard> getThreeOfAKind(List<PlayingCard> combinedCards) {
    List<PlayingCard> threeOfAKind = new ArrayList<>();
    for(PlayingCard card : combinedCards) {
      List<PlayingCard> filtered = combinedCards.stream().filter(c -> c.getFace() == card.getFace()).toList();
      if (filtered.size() == 3) {
        threeOfAKind = filtered;
      }
    }
    return threeOfAKind;
  }

  private List<PlayingCard> getPairs(List<PlayingCard> combinedCards) {
    List<Integer> faces = combinedCards.stream()
      .map(PlayingCard::getFace)
      .sorted()
      .toList();

    List<Integer> pairs = new ArrayList<>();
    int lastFaceIdx = 0;
    int lastFace = faces.getFirst();
    for (int i = 1; i < faces.size(); i++) {
      if (faces.get(i) == lastFace) {
        if (pairs.isEmpty()) {
          pairs.add(lastFaceIdx);
        }
        pairs.add(i);
      }

      lastFace = faces.get(i);
      lastFaceIdx = i;
    }

    return pairs.stream().map(combinedCards::get).toList();
  }
}
