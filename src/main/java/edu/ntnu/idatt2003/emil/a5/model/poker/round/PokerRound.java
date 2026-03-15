package edu.ntnu.idatt2003.emil.a5.model.poker.round;

import edu.ntnu.idatt2003.emil.a5.model.PlayerAction;
import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.users.User;
import edu.ntnu.idatt2003.emil.a5.model.poker.hand.HandChecker;

import java.util.ArrayList;
import java.util.List;

public class PokerRound {
  private final RoundState roundState;
  private final List<PlayingCard> communityCards;
  private final DeckOfCards deck;
  private final Pot pot;
  private final BettingRound bettingRound;
  private final HandChecker handChecker;
  private User currentPlayer;

  public PokerRound() {
    this.roundState = new RoundState();
    this.communityCards = new ArrayList<>();
    this.deck = new DeckOfCards();
    this.pot = new Pot();
    this.bettingRound = new BettingRound();
    this.handChecker = new HandChecker();
  }

  public void advanceRoundState() {
    roundState.advanceRound();
  }

  public void dealPreFlop() {
    communityCards.addAll(deck.dealHand(3));
  }

  public void dealFlop() {
    communityCards.addAll(deck.dealHand(1));
  }

  public void dealTurn() {
    communityCards.addAll(deck.dealHand(1));
  }

  public void dealRiver() {}

  public void handleAction(User user, PlayerAction action) {
    bettingRound.handleAction(user, action);
  }

  public void resolveShowdown() {
    advanceRoundState();
  }

  public void dealHoleCards(List<User> participants) {
    this.deck.restockCards();
    for (User participant : participants) {
      participant.setCards(deck.dealHand(2));
    }
  }

  public RoundState getRoundState() {
    return roundState;
  }

  public List<PlayingCard> getCommunityCards() {
    return communityCards;
  }

  public DeckOfCards getDeck() {
    return deck;
  }

  public Pot getPot() {
    return pot;
  }

  public BettingRound getBettingRound() {
    return bettingRound;
  }

  public HandChecker getHandChecker() {
    return handChecker;
  }
}
