package edu.ntnu.idatt2003.emil.a5.model.poker.round;

import edu.ntnu.idatt2003.emil.a5.model.poker.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.poker.hand.HandCheckResult;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Participant;
import edu.ntnu.idatt2003.emil.a5.model.poker.hand.HandChecker;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;

import java.util.*;
import java.util.logging.Logger;

public class PokerRound {
  private final Logger logger = Logger.getLogger(PokerRound.class.getName());

  private final RoundState roundState;
  private final List<PlayingCard> communityCards;
  private final DeckOfCards deck;
  private final Pot pot;
  private BettingRound bettingRound;
  private final HandChecker handChecker;
  private List<Participant> players;

  private HandCheckResult winningHandCheckResult;

  public PokerRound(Player player, List<Bot> bots, List<Participant> players) {
    this.roundState = new RoundState();
    this.communityCards = new ArrayList<>();
    this.deck = new DeckOfCards();
    this.pot = new Pot();
    this.bettingRound = new BettingRound(player, bots);
    this.handChecker = new HandChecker();
    this.players = players;
  }

  public RoundState getRoundState() {
    return roundState;
  }

  public List<PlayingCard> getCommunityCards() {
    return communityCards;
  }

  public String getCommunityCardsString() {
    StringBuilder communityCardsString = new StringBuilder();
    communityCardsString.append("Community Cards: [");
    for (int i = 0; i < getCommunityCards().size(); i++) {
      if (i == getCommunityCards().size() - 1) {
        communityCardsString.append(getCommunityCards().get(i).getAsString());
        continue;
      }
      communityCardsString.append(getCommunityCards().get(i).getAsString()).append(", ");
    }
    communityCardsString.append("]");
    return communityCardsString.toString();
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

  public HandCheckResult getLastWinner() {
    return winningHandCheckResult;
  }

  public void advanceRoundState() {
    roundState.advanceRound();
  }

  public void resetCards() {
    communityCards.clear();
    for (Participant player : players) {
      player.clearCards();
    }
  }

  public void dealPreFlop() {
    resetCards();
    dealHoleCards(this.players);
    bettingRound.start(players);
  }

  public void dealFlop() {
    communityCards.addAll(deck.dealHand(3));
  }

  public void dealTurn() {
    communityCards.addAll(deck.dealHand(1));
  }

  public void dealRiver() {
    communityCards.addAll(deck.dealHand(1));
  }

  public void handleAction(Participant participant, PlayerAction action) {
    bettingRound.handleAction(participant, action);
  }

  public void resolveShowdown() {
    List<HandCheckResult> checkResults = new ArrayList<>();
    for (Participant player : players) {
      checkResults.add(this.handChecker.checkHand(player, this.communityCards));
    }

    checkResults.sort(Comparator.comparing(HandCheckResult::cardTotal).reversed());
    checkResults.sort(Comparator.comparing(HandCheckResult::rank));
    logger.info(checkResults.toString());
    logger.info(checkResults.getFirst().participant().getName() + " wins with a [" + checkResults.getFirst().rank() + "] and card value of [" + checkResults.getFirst().cardTotal() + "]");
    winningHandCheckResult = checkResults.getFirst();
  }

  public void dealHoleCards(List<Participant> participants) {
    this.deck.restockCards();
    for (Participant participant : participants) {
      participant.setCards(deck.dealHand(2));
    }
  }
}
