package edu.ntnu.idatt2003.emil.a5.model.poker.round;

import edu.ntnu.idatt2003.emil.a5.model.PlayerAction;
import edu.ntnu.idatt2003.emil.a5.model.users.User;

public class BettingRound {
  private int currentBet;
  private int minRaise;
  private int pot;
  private boolean isComplete;

  public void handleAction(User user, PlayerAction action) {
    switch (action.getType()) {
      case CHECK -> check(user);
      case CALL -> call(user);
      case RAISE -> raise(user, action.getAmount());
      case FOLD -> fold(user);
    }
  }

  public void check(User user) {
    if (currentBet > 0) {

    }
    if (currentBet == 0) {

    }
  }

  public void call(User user) {

  }

  public void raise(User user, int amount) {

  }

  public void fold(User user) {

  }

  public boolean isComplete() {
    return isComplete;
  }
}
