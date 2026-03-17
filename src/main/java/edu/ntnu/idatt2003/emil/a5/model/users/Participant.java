package edu.ntnu.idatt2003.emil.a5.model.users;

import edu.ntnu.idatt2003.emil.a5.model.poker.PlayingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
  private final String name;
  private List<PlayingCard> cards;
  private int chips;

  public Participant(String name, int chips) {
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
      ", " + getCardsAsString();
  }

  public String getCardsAsString() {
    StringBuilder cardsString = new StringBuilder();
    cardsString.append("Cards: [");
    for (int i = 0; i < this.cards.size(); i++) {
      if (i == this.cards.size() - 1) {
        cardsString.append(this.cards.get(i).getAsString());
        continue;
      }
      cardsString.append(this.cards.get(i).getAsString()).append(", ");
    }
    cardsString.append("]");
    return cardsString.toString();
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

  public void clearCards() {
    cards.clear();
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
