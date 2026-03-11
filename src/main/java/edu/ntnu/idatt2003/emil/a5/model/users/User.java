package edu.ntnu.idatt2003.emil.a5.model;

public abstract class User {
  private final String name;
  private Hand hand;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Hand getHand() {
    return hand;
  }

  public void setHand(Hand hand) {
    this.hand = hand;
  }

  public void check() {}

  public void call() {}

  public void raise() {}

  public void bet() {}

  public void allIn() {}
}
