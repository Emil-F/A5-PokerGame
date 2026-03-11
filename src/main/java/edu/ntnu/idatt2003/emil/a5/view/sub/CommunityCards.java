package edu.ntnu.idatt2003.emil.a5.view.sub;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CommunityCardsView extends StackPane {
  private PokerController pokerController;

  public CommunityCardsView(PokerController pokerController) {
    this.pokerController = pokerController;
    getStylesheets().add(getClass().getResource("/css/CommunityCards.css").toExternalForm());
    getChildren().add(getCardRow());
    setId("community_cards");
    setAlignment(Pos.CENTER);
  }

  public HBox getCardRow() {
    HBox cards = new HBox();
    cards.setId("cards");
    for (String c : pokerController.getCommunityCards()) {
      cards.getChildren().add(getCard(c));
    }
    cards.setSpacing(15);
    cards.setAlignment(Pos.CENTER);
    return cards;
  }

  public StackPane getCard(String text) {
    StackPane frame = new StackPane();
    Rectangle card = new Rectangle();
    Text txt = new Text(text);
    card.setId("card");
    card.setWidth(50);
    card.setHeight(50);
    frame.getChildren().addAll(card, txt);
    return frame;
  }
}
