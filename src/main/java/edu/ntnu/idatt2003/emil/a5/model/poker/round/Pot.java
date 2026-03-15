package edu.ntnu.idatt2003.emil.a5.model.poker.round;

public class Pot {
  private int total;

  public Pot() {
    this.total = 0;
  }

  @Override
  public String toString() {
    return String.valueOf(total);
  }

  public int getTotal() {
    return total;
  }

  public void addToPot(int amount) {
    this.total += amount;
  }

  public void clear() {
    this.total = 0;
  }
}
