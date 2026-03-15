package edu.ntnu.idatt2003.emil.a5.model.poker.round;

import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
  private final Random rand;
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final List<PlayingCard> cards;

  public DeckOfCards() {
    this.rand = new Random();
    this.cards = new ArrayList<>();
    restockCards();
  }

  public char[] getSuit() {
    return suit;
  }

  public List<PlayingCard> getCards() {
    return this.cards;
  }

  public void restockCards() {
    cards.clear();
    int deckSize = 52;
    int maxFaceSize = 13;

    int face = 1;
    int index = 0;
    for(int n = 0; n < deckSize; n++) {
      if (face > maxFaceSize) {
        face = 1;
        index++;
      }
      cards.add(new PlayingCard(suit[index], face));
      face++;
    }
  }

  public List<PlayingCard> dealHand(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n is less than 1");
    }
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
