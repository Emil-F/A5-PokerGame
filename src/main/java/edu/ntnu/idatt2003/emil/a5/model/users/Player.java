package edu.ntnu.idatt2003.emil.a5.model.users;

import java.util.Objects;

/**
 * This class represents a player in the game.
 */
public class Player extends User {
  private boolean folded;

  /**
   * Constructs a new Player.
   *
   * @param name name of the player.
   * @param chips amount of chips owned by the player at the start of the game.
   * @throws NullPointerException when name is null.
   * @throws IllegalArgumentException when chips are negative.
   */
  public Player(String name, int chips) {
    if (chips < 0) {
      throw new IllegalArgumentException("chips is negative");
    }
    super(Objects.requireNonNull(name , "name is null"), chips);
    this.folded = false;
  }

  public boolean isFolded() {
    return folded;
  }

  public void fold() {
    folded = true;
  }
}
