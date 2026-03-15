package edu.ntnu.idatt2003.emil.a5.model.users;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {
  private final String name;
  private List<PlayingCard> cards;
  private int chips;

  public User(String name, int chips) {
    if (chips < 0) {
      throw new IllegalArgumentException("chips is negative");
    }
    this.name = Objects.requireNonNull(name, "name is null");
    this.cards = new ArrayList<>();
    this.chips = chips;
  }

  @Override
  public String toString() {
    return "Class: " + getClass().getSimpleName() +
      ", Name: " + getName() +
      ", Chips: " + getChips() +
      ", " + getCards().toString();
  }

  public String getName() {
    return name;
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public void setCards(List<PlayingCard> holeCards) {
    cards = holeCards;
  }

  public int getChips() {
    return chips;
  }

  public void addChips(int chips) {
    if (chips < 0) {
      throw new IllegalArgumentException("chips is negative");
    }
    this.chips += chips;
  }

  public void removeChips(int chips) {
    if (chips < 0) {
      throw new IllegalArgumentException("chips is negative");
    }
    this.chips -= chips;
  }
}
