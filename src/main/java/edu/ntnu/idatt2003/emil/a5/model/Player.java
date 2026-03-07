package edu.ntnu.idatt2003.emil.a5.model;

public class Player {
  private String name;
  private Hand hand;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Hand getHand() {
    return hand;
  }
}
