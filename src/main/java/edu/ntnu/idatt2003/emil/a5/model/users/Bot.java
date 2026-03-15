package edu.ntnu.idatt2003.emil.a5.model.users;

import java.util.Objects;

/**
 * This class represents a Bot in the game.
 */
public class Bot extends User {
  /**
   * Constructs a new Bot.
   *
   * @param name name of the bot.
   */
  public Bot(String name, int chips) {
    if (chips < 0) {
      throw new IllegalArgumentException("chips is negative");
    }
    super(Objects.requireNonNull(name, "name is null"), chips);
  }
}
