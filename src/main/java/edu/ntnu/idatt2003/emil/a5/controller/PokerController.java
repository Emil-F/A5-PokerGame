package edu.ntnu.idatt2003.emil.a5.controller;

import edu.ntnu.idatt2003.emil.a5.model.PokerGame;
import edu.ntnu.idatt2003.emil.a5.model.users.Player;

public class PokerController {
  private final PokerGame game;

  public PokerController(PokerGame game) {
    this.game = game;
    this.game.setPlayer(new Player("Emil", 50000));
    this.game.populateBots(3);
  }
}
