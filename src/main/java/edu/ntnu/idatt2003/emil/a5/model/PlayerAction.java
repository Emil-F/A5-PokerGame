package edu.ntnu.idatt2003.emil.a5.model;

public class PlayerAction {

  private final PlayerActionType type;
  private final int amount; // used for raise

  public PlayerAction(PlayerActionType type, int amount) {
    this.type = type;
    this.amount = amount;
  }

  public PlayerActionType getType() {
    return type;
  }

  public int getAmount() {
    return amount;
  }
}