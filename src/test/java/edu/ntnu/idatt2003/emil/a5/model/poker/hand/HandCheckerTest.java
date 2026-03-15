package edu.ntnu.idatt2003.emil.a5.model.poker.hand;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckerTest {

  private HandChecker handChecker;
  private List<PlayingCard> playerCards;
  private List<PlayingCard> highCardHand;

  private PlayingCard card(char suit, int face) {
    return new PlayingCard(suit, face);
  }

  @BeforeEach
  void setUp() {
    playerCards = new ArrayList<>();
    handChecker = new HandChecker();
    highCardHand = List.of(
      card('S', 2),
      card('H', 5),
      card('D', 7),
      card('C', 9),
      card('S', 11),
      card('H', 13),
      card('D', 3)
    );
  }

  @Test
  void calculateCardTotal_ReturnsTheHandsTotalCardValue() {
  }

  @Test
  void checkHand_returnsRoyalFlushIfHandHasRoyalFlush() {
    playerCards = List.of(card('S', 1), card('S', 10));
    List<PlayingCard> royalFlush = List.of(
      card('S', 11),
      card('S', 12),
      card('S', 13),
      card('S', 2),
      card('S', 3)
    );
    int cardTotal = 52;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.ROYAL_FLUSH);

    assertEquals(result, handChecker.checkHand(playerCards, royalFlush));
  }

  @Test
  void checkHand_returnsStraightFlushIfHandHasStraightFlush() {
    playerCards = List.of(card('S', 1), card('S', 3));
    List<PlayingCard> straightFlush = List.of(
      card('S', 4),
      card('S', 5),
      card('S', 6),
      card('S', 7),
      card('H', 3)
    );
    int cardTotal = 29;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.STRAIGHT_FLUSH);

    assertEquals(result, handChecker.checkHand(playerCards, straightFlush));
  }

  @Test
  void checkHand_returnsFourOfAKindIfHandHasFourOfAKind() {
    playerCards = List.of(card('S', 1), card('S', 3));
    List<PlayingCard> fourOfAKind = List.of(
      card('H', 3),
      card('D', 3),
      card('H', 6),
      card('S', 7),
      card('C', 3)
    );
    int cardTotal = 26;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.FOUR_OF_A_KIND);

    assertEquals(result, handChecker.checkHand(playerCards, fourOfAKind));
  }

  @Test
  void checkHand_returnsFullHouseIfHandHasFullHouse() {
    playerCards = List.of(card('S', 1), card('S', 3));
    List<PlayingCard> fullHouse = List.of(
      card('H', 3),
      card('D', 3),
      card('H', 6),
      card('S', 2),
      card('C', 2)
    );
    int cardTotal = 20;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.FULL_HOUSE);

    assertEquals(result, handChecker.checkHand(playerCards, fullHouse));
  }

  @Test
  void checkHand_returnsFlushIfHandHasFlush() {
    playerCards = List.of(card('S', 1), card('H', 4));
    List<PlayingCard> flush = List.of(
      card('H', 6),
      card('H', 10),
      card('H', 13),
      card('S', 8),
      card('H', 2)
    );
    int cardTotal = 44;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.FLUSH);

    assertEquals(result, handChecker.checkHand(playerCards, flush));
  }

  @Test
  void checkHand_returnsStraightIfHandHasStraight() {
    playerCards = List.of(card('S', 1), card('D', 3));
    List<PlayingCard> straight = List.of(
      card('H', 4),
      card('C', 5),
      card('H', 6),
      card('S', 7),
      card('H', 2)
    );
    int cardTotal = 28;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.STRAIGHT);

    assertEquals(result, handChecker.checkHand(playerCards, straight));
  }

  @Test
  void checkHand_returnsThreeOfAKindIfHandHasThreeOfAKind() {
    playerCards = List.of(card('S', 1), card('D', 3));
    List<PlayingCard> threeOfAKind = List.of(
      card('H', 3),
      card('C', 5),
      card('H', 6),
      card('S', 3),
      card('H', 2)
    );
    int cardTotal = 23;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.THREE_OF_A_KIND);

    assertEquals(result, handChecker.checkHand(playerCards, threeOfAKind));
  }

  @Test
  void checkHand_returnsTwoPairsIfHandHasTwoPairs() {
    playerCards = List.of(card('S', 1), card('D', 3));
    List<PlayingCard> twoPairs = List.of(
      card('H', 3),
      card('C', 5),
      card('H', 6),
      card('S', 5),
      card('H', 2)
    );
    int cardTotal = 25;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.TWO_PAIRS);

    assertEquals(result, handChecker.checkHand(playerCards, twoPairs));
  }

  @Test
  void checkHand_returnsOnePairIfHandHasOnePair() {
    playerCards = List.of(card('S', 1), card('D', 3));
    List<PlayingCard> onePair = List.of(
      card('H', 3),
      card('C', 5),
      card('H', 6),
      card('S', 7),
      card('H', 2)
    );
    int cardTotal = 27;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.ONE_PAIR);

    assertEquals(result, handChecker.checkHand(playerCards, onePair));
  }

  @Test
  void checkHand_returnsHighCardIfHandHasHighCard() {
    playerCards = List.of(card('S', 2), card('H', 5));
    List<PlayingCard> highCard = List.of(
      card('D', 7),
      card('C', 9),
      card('S', 11),
      card('H', 13),
      card('D', 3)
    );
    int cardTotal = 50;

    HandCheckResult result = new HandCheckResult(cardTotal, HandRank.HIGH_CARD);

    assertEquals(result, handChecker.checkHand(playerCards, highCard));
  }

  @Test
  void hasRoyalFlush_returnsTrueIfHandHasRoyalFlush() {
    List<PlayingCard> royalFlush = List.of(
      card('S', 1),
      card('S', 10),
      card('S', 11),
      card('S', 12),
      card('S', 13),
      card('S', 2),
      card('S', 3)
    );

    assertTrue(handChecker.hasRoyalFlush(royalFlush));
  }

  @Test
  void hasRoyalFlush_returnsFalseIfHandDoesNotHaveRoyalFlush() {
    assertFalse(handChecker.hasRoyalFlush(highCardHand));
  }

  @Test
  void hasRoyalFlush_returnsFalseIfCardsDontAllHaveTheSameSuit() {
    List<PlayingCard> cards = List.of(
      card('S', 1),
      card('S', 10),
      card('H', 11),
      card('S', 12),
      card('S', 13),
      card('H', 13),
      card('C', 13)
    );

    assertFalse(handChecker.hasRoyalFlush(cards));
  }

  @Test
  void hasStraightFlush_returnsTrueIfHandHasStraightFlush() {
    List<PlayingCard> straightFlush = List.of(
      card('S', 7),
      card('S', 8),
      card('S', 9),
      card('S', 10),
      card('S', 11),
      card('S', 1),
      card('S', 2)
    );

    assertTrue(handChecker.hasStraightFlush(straightFlush));
  }

  @Test
  void hasStraightFlush_returnsFalseIfHandDoesNotHaveStraightFlush() {
    assertFalse(handChecker.hasStraightFlush(highCardHand));
  }

  @Test
  void hasStraightFlush_returnsFalseIfCardsDontAllHaveTheSameSuit() {
    List<PlayingCard> cards = List.of(
      card('D', 7),
      card('S', 8),
      card('H', 9),
      card('S', 10),
      card('S', 11),
      card('S', 2),
      card('S', 1)
    );

    assertFalse(handChecker.hasStraightFlush(cards));
  }

  @Test
  void hasFourOfAKind_returnsTrueIfHandHasFourOfAKind() {
    List<PlayingCard> fourOfAKind = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 5),
      card('C', 5),
      card('C', 10),
      card('S', 11),
      card('H', 11)
    );

    assertTrue(handChecker.hasFourOfAKind(fourOfAKind));
  }

  @Test
  void hasFourOfAKind_returnsFalseIfHandDoesNotHaveFourOfAKind() {
    List<PlayingCard> threeOfAKind = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 5),
      card('C', 2),
      card('C', 10),
      card('S', 11),
      card('H', 11)
    );

    assertAll(
      () -> assertFalse(handChecker.hasFourOfAKind(highCardHand)),
      () -> assertFalse(handChecker.hasFourOfAKind(threeOfAKind))
    );
  }

  @Test
  void hasFullHouse_returnsTrueIfHandHasFullHouse() {
    List<PlayingCard> fullHouseV1 = List.of(
      card('S', 6),
      card('H', 6),
      card('D', 6),
      card('C', 12),
      card('H', 12),
      card('S', 1),
      card('H', 2)
    );

    List<PlayingCard> fullHouseV2 = List.of(
      card('S', 6),
      card('H', 5),
      card('D', 5),
      card('C', 5),
      card('H', 12),
      card('S', 1),
      card('H', 6)
    );

    assertAll(
      () -> assertTrue(handChecker.hasFullHouse(fullHouseV1)),
      () -> assertTrue(handChecker.hasFullHouse(fullHouseV2))
    );
  }

  @Test
  void hasFullHouse_returnsFalseIfHandDoesNotHaveFullHouse() {
    assertFalse(handChecker.hasFullHouse(highCardHand));
  }

  @Test
  void hasFlush_returnsTrueIfHandHasFlush() {
    List<PlayingCard> flushV1 = List.of(
      card('S', 1),
      card('S', 3),
      card('S', 4),
      card('S', 6),
      card('H', 8),
      card('S', 10),
      card('H', 11)
    );

    List<PlayingCard> flushV2= List.of(
      card('H', 1),
      card('H', 3),
      card('H', 4),
      card('H', 6),
      card('H', 8),
      card('H', 10),
      card('H', 11)
    );

    assertAll(
      () -> assertTrue(handChecker.hasFlush(flushV1)),
      () -> assertTrue(handChecker.hasFlush(flushV2))
    );
  }

  @Test
  void hasFlush_returnsFalseIfHandDoesNotHaveFlush() {
    assertFalse(handChecker.hasFlush(highCardHand));
  }

  @Test
  void hasStraight_returnsTrueIfHandHasStraight() {
    List<PlayingCard> straightV1 = List.of(
      card('S', 3),
      card('H', 1),
      card('D', 6),
      card('C', 7),
      card('C', 8),
      card('S', 9),
      card('S', 10)
    );

    List<PlayingCard> straightV2 = List.of(
      card('S', 13),
      card('H', 3),
      card('D', 4),
      card('C', 5),
      card('C', 6),
      card('S', 7),
      card('S', 10)
    );

    List<PlayingCard> straightV3 = List.of(
      card('S', 13),
      card('H', 4),
      card('D', 5),
      card('C', 6),
      card('C', 7),
      card('S', 8),
      card('S', 1)
    );

    List<PlayingCard> straightV4 = List.of(
      card('S', 13),
      card('H', 4),
      card('D', 11),
      card('C', 7),
      card('C', 8),
      card('S', 9),
      card('S', 10)
    );

    List<PlayingCard> straightV5 = List.of(
      card('S', 7),
      card('S', 8),
      card('S', 9),
      card('S', 10),
      card('S', 11),
      card('S', 1),
      card('S', 2)
    );

    assertAll(
      () -> assertTrue(handChecker.hasStraight(straightV1)),
      () -> assertTrue(handChecker.hasStraight(straightV2)),
      () -> assertTrue(handChecker.hasStraight(straightV3)),
      () -> assertTrue(handChecker.hasStraight(straightV4)),
      () -> assertTrue(handChecker.hasStraight(straightV5))
    );
  }

  @Test
  void hasStraight_returnsFalseIfHandDoesNotHaveStraight() {
    assertFalse(handChecker.hasStraight(highCardHand));
  }

  @Test
  void hasThreeOfAKind_returnsTrueIfHandHasThreeOfAKind() {
    List<PlayingCard> threeOfAKind = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 5),
      card('C', 9),
      card('C', 10),
      card('S', 11),
      card('S', 11)
    );

    assertTrue(handChecker.hasThreeOfAKind(threeOfAKind));
  }

  @Test
  void hasThreeOfAKind_returnsFalseIfHandDoesNotHaveThreeOfAKind() {
    List<PlayingCard> fourOfAKind = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 5),
      card('C', 5),
      card('C', 10),
      card('S', 11),
      card('S', 11)
    );

    assertAll(
      () -> assertFalse(handChecker.hasThreeOfAKind(highCardHand)),
      () -> assertFalse(handChecker.hasThreeOfAKind(fourOfAKind))
    );
  }

  @Test
  void hasTwoPairs_returnsTrueIfHandHasTwoPairs() {
    List<PlayingCard> twoPairs = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 1),
      card('C', 2),
      card('C', 10),
      card('S', 10),
      card('S', 12)
    );

    assertTrue(handChecker.hasTwoPairs(twoPairs));
  }

  @Test
  void hasTwoPairs_returnsFalseIfHandHasOnePair() {
    List<PlayingCard> onePair = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 1),
      card('C', 2),
      card('C', 10),
      card('S', 11),
      card('S', 12)
    );

    assertFalse(handChecker.hasTwoPairs(onePair));
  }

  @Test
  void hasTwoPairs_returnsFalseIfHandDoesNotHaveTwoPairs() {
    assertFalse(handChecker.hasTwoPairs(highCardHand));
  }

  @Test
  void hasOnePair_returnsTrueIfHandHasOnePair() {
    List<PlayingCard> onePair = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 1),
      card('C', 2),
      card('C', 10),
      card('S', 11),
      card('S', 12)
    );

    assertTrue(handChecker.hasOnePair(onePair));
  }

  @Test
  void hasOnePair_returnsFalseIfHandHasTwoPairs() {
    List<PlayingCard> twoPairs = List.of(
      card('S', 5),
      card('H', 5),
      card('D', 1),
      card('C', 2),
      card('C', 10),
      card('S', 10),
      card('S', 12)
    );

    assertFalse(handChecker.hasOnePair(twoPairs));
  }

  @Test
  void hasOnePair_returnsFalseIfHandDoesNotHaveOnePair() {
    assertFalse(handChecker.hasOnePair(highCardHand));
  }
}