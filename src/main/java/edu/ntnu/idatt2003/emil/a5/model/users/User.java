package edu.ntnu.idatt2003.emil.a5.model.users;

import edu.ntnu.idatt2003.emil.a5.model.hand.Hand;

public abstract class User {
  private final String name;
  private final Hand hand;

  public User(String name) {
    this.name = name;
    this.hand = new Hand();
  }

  public String getName() {
    return name;
  }

  public Hand getHand() {
    return hand;
  }

  public void check() {}

  public void call() {}

  public void raise() {}

  public void bet() {}

  public void allIn() {}
}
