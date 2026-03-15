package edu.ntnu.idatt2003.emil.a5.view.sub;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.model.PlayingCard;
import edu.ntnu.idatt2003.emil.a5.view.Card;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class CommunityCards extends StackPane {
  private final PokerController controller;
  private final HBox cards = new HBox();

  public CommunityCards(PokerController pokerController) {
    this.controller = pokerController;

    getStylesheets().add(
        getClass().getResource("/css/CommunityCards.css").toExternalForm()
    );

    cards.setId("cards");
    cards.setSpacing(15);
    cards.setAlignment(Pos.CENTER);
    cards.setFillHeight(true);

    getChildren().add(cards);

    setId("community_cards");
    setAlignment(Pos.CENTER);

    // Initial render
    refreshCards();

    // Automatic updates
    controller.getCommunityCards().addListener(
        (ListChangeListener<PlayingCard>) change -> refreshCards()
    );
  }

  public void refreshCards() {
    cards.getChildren().clear();

    for (PlayingCard card : controller.getCommunityCards()) {
      cards.getChildren().add(new Card(card));
    }
  }
}