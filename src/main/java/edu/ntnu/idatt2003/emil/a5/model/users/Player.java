package edu.ntnu.idatt2003.emil.a5.model;

public class Player extends User {
  private int chips;
  private boolean folded;

  public Player(String name, int chips) {
    super(name);
    this.chips = chips;
    this.folded = false;
  }

  public int getChips() {
    return chips;
  }

  public boolean hasFolded() {
    return folded;
  }

  public void addChips(int chips) {
    this.chips += chips;
  }

  public void removeChips(int chips) {
    this.chips -= chips;
  }

  public void fold() {
    folded = true;
  }
}
