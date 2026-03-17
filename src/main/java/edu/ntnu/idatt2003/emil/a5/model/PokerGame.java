package edu.ntnu.idatt2003.emil.a5.model;

import edu.ntnu.idatt2003.emil.a5.model.poker.round.PokerRound;
import edu.ntnu.idatt2003.emil.a5.model.poker.round.Round;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import edu.ntnu.idatt2003.emil.a5.model.users.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class holds all the logic associated with the game of Poker.
 */
public class PokerGame {
  private final static Logger logger = Logger.getLogger(PokerGame.class.getName());

  private final Player player;
  private final List<Bot> bots;
  private final List<Participant> participants;
  private final PokerRound currentRound;

  private boolean activeBettingRound;

  /**
   * Constructs a new instance of the game of Poker.
   */
  public PokerGame() {
    this.player = new Player("Emil", 50000);
    this.bots = new ArrayList<>();
    this.participants = new ArrayList<>();
    this.currentRound = new PokerRound(player, bots, participants);

    this.activeBettingRound = false;

    populateBots(3);
  }

  public Player getPlayer() {
    return player;
  }

  public List<Bot> getBots() {
    return bots;
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  public PokerRound getCurrentRound() {
    return currentRound;
  }

  public void populateBots(int botCount) {
    if (botCount > 22) {
      throw new IllegalArgumentException("Bot count can't be greater than 22.");
    }
    for(int i = 1; i <= botCount; i++) {
      this.bots.add(new Bot("Bot" + i, 50000));
    }
  }

  public void startGame() {
    participants.clear();
    participants.add(this.player);
    participants.addAll(this.bots);
    currentRound.resetCards();
    currentRound.getRoundState().startNewRound();
    System.out.println("Starting");
    currentRound.dealPreFlop();
    logGameInfo();
  }

  public void advanceRound() {
    currentRound.getRoundState().advanceRound();
    switch (currentRound.getRoundState().getCurrentState()) {
      case PRE_FLOP -> currentRound.dealPreFlop();
      case FLOP     -> currentRound.dealFlop();
      case TURN     -> currentRound.dealTurn();
      case RIVER    -> currentRound.dealRiver();
      case SHOWDOWN -> currentRound.resolveShowdown();
    }
    logGameInfo();
  }

  public void stopGame() {
  }

  public void logGameInfo() {
    StringBuilder participantInfo = new StringBuilder();
    for (Participant participant : participants) {
      participantInfo.append(participant.toString()).append("\n");
    }

    String gameInfo = "--- Game Info --- \n"
      + "Current Round: " + currentRound.getRoundState().getCurrentState().toString() + "\n"
      + "Current Pot: " + currentRound.getPot().toString() + "\n"
      + currentRound.getCommunityCardsString() + "\n"
      + participantInfo;

    logger.info(gameInfo);
  }
}
