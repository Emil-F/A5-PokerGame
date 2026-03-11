package edu.ntnu.idatt2003.emil.a5.controller;

import java.util.Arrays;

public class GameController {
  public GameController() {}

  public String[] getCommunityCards() {
    String[] communityCards = new String[5];
    Arrays.fill(communityCards, "H1");
    return communityCards;
  }
}
