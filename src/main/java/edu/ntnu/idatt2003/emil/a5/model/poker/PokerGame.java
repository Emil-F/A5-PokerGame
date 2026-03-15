package edu.ntnu.idatt2003.emil.a5.model.poker;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.model.poker.round.PokerRound;
import edu.ntnu.idatt2003.emil.a5.model.users.Bot;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;
import edu.ntnu.idatt2003.emil.a5.model.users.User;

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
  private final List<User> participants;

  private final PokerRound currentRound;

  /**
   * Constructs a new instance of the game of Poker.
   */
  public PokerGame() {
    this.player = new Player("Emil", 50000);
    this.bots = new ArrayList<>();
    this.participants = new ArrayList<>();

    this.currentRound = new PokerRound();

    populateBots(3);
  }

  public List<PlayingCard> getCommunityCards() {
    return currentRound.getCommunityCards();
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

  public Player getPlayer() {
    return player;
  }

  public List<Bot> getBots() {
    return bots;
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
      + "Current Round: " + currentRound.getRoundState().getCurrentState().toString() + "\n"
      + "Current Pot: " + currentRound.getPot().toString() + "\n"
      + getCommunityCardsString() + "\n"
      + participantInfo;

    logger.info(gameInfo);
  }
}
