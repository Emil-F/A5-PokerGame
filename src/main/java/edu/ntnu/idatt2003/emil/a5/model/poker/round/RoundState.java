package edu.ntnu.idatt2003.emil.a5.model.poker;

public class RoundState {
  private Round state = Round.PRE_FLOP;

  public Round getCurrentState() {
    return state;
  }

  public void advanceRound() {
    switch (this.state) {
      case PRE_FLOP -> state = Round.FLOP;
      case FLOP -> state = Round.TURN;
      case TURN -> state = Round.RIVER;
      case RIVER -> state = Round.SHOWDOWN;
    }
  }

  public void startNewRound() {
    this.state = Round.PRE_FLOP;
  }
}
