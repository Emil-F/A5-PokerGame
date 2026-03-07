package edu.ntnu.idatt2003.emil.a5.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
  private final Random rand;
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final List<PlayingCard> cards;

  public DeckOfCards() {
    rand = new Random();
    cards = new ArrayList<>();
    stockCards();
  }

  public void stockCards() {
    int face = 1;
    int index = 0;

    for(int n = 0; n < 52; n++) {
      if (face > 13) {
        face = 1;
        index++;
      }
      cards.add(new PlayingCard(suit[index], face));
      face++;
    }
  }

  public char[] getSuit() {
    return suit;
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public List<PlayingCard> dealHand(int n) {
    List<PlayingCard> deck = getCards();
    List<PlayingCard> hand = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      int cardIdx = rand.nextInt(0, deck.size());
      hand.add(deck.get(cardIdx));
      deck.remove(cardIdx);
    }
    return hand;
  }
}
