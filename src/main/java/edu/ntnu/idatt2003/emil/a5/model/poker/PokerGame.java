package edu.ntnu.idatt2003.emil.a5.model;

import edu.ntnu.idatt2003.emil.a5.PokerRound;
import edu.ntnu.idatt2003.emil.a5.model.poker.DeckOfCards;
import edu.ntnu.idatt2003.emil.a5.model.poker.Pot;
import edu.ntnu.idatt2003.emil.a5.model.poker.RoundState;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import edu.ntnu.idatt2003.emil.a5.model.users.User;
import edu.ntnu.idatt2003.emil.a5.model.users.hand.HandChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * This class holds all the logic associated with the game of Poker.
 */
public class PokerGame {
  private final static Logger logger = Logger.getLogger(PokerGame.class.getName());

  private final DeckOfCards deck;
  private final List<PlayingCard> communityCards;

  private Player player;
  private final List<Bot> bots;
  private final List<User> participants;

  private final HandChecker handChecker;
  private final RoundState roundState;
  private final Pot pot;

  /**
   * Constructs a new instance of the game of Poker.
   */
  public PokerGame() {
    this.deck = new DeckOfCards();
    this.communityCards = new ArrayList<>();

    this.player = new Player("Emil", 50000);
    this.bots = new ArrayList<>();
    this.participants = new ArrayList<>();

    this.handChecker = new HandChecker();
    this.roundState = new RoundState();
    this.pot = new Pot();
    populateBots(3);
  }

  public DeckOfCards getDeck() {
    return deck;
  }

  public List<PlayingCard> getCommunityCards() {
    return communityCards;
  }

  public String getCommunityCardsString() {
    StringBuilder communityCardsString = new StringBuilder();
    communityCardsString.append("Community Cards: [");
    for (int i = 0; i < this.communityCards.size(); i++) {
      if (i == this.communityCards.size() - 1) {
        communityCardsString.append(this.communityCards.get(i).getAsString());
        continue;
      }
      communityCardsString.append(this.communityCards.get(i).getAsString()).append(", ");
    }
    communityCardsString.append("]");
    return communityCardsString.toString();
  }

  public Player getPlayer() {
    return player;
  }

  public List<Bot> getBots() {
    return bots;
  }

  public PokerRound getRoundState() {
    return this.roundState.getRoundState();
  }

  public void setPlayer(Player player) {
    this.player = Objects.requireNonNull(player, "player is null");
  }

  public void populateBots(int botCount) {
    if (botCount > 22) {
      throw new IllegalArgumentException("Bot count can't be greater than 22.");
    }
    for(int i = 1; i <= botCount; i++) {
      this.bots.add(new Bot("Bot" + i, 50000));
    }
  }

  public void dealCards() {
    this.deck.restockCards();
    this.player.getHand().setCards(deck.dealHand(2));
    for(Bot bot : this.bots) {
      bot.getHand().setCards(deck.dealHand(2));
    }
  }

//  public void bettingRound() {
//    boolean raised = false;
//    for (User participant : participants) {
//      participant.setBetting(true);
//      while (participant.isBetting()) {
//        participant.setBetting(false);
//      }
//    }
//  }

  public void startGame() {
    participants.clear();
    participants.add(this.player);
    participants.addAll(this.bots);

    dealCards();

    logGameInfo();
  }

  public void stopGame() {
  }

  public void logGameInfo() {
    StringBuilder participantInfo = new StringBuilder();
    for (User participant : participants) {
      participantInfo.append(participant.toString()).append("\n");
    }

    String gameInfo = "--- Game Info --- \n"
      + "Current Round: " + getRoundState().toString() + "\n"
      + "Current Pot: " + pot.toString() + "\n"
      + getCommunityCardsString() + "\n"
      + participantInfo;

    logger.info(gameInfo);
  }
}
