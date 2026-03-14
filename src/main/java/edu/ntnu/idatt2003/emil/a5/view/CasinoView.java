package edu.ntnu.idatt2003.emil.a5.view;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.view.sub.CommunityCards;
import edu.ntnu.idatt2003.emil.a5.view.sub.PlayerHand;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CasinoView extends StackPane {
  private final PokerController controller;
  private final CommunityCards communityCards;

  public CasinoView(PokerController controller) {
    this.controller = controller;
    this.communityCards = new CommunityCards(controller);
    getChildren().add(createBody());
  }

  private VBox createBody() {
    VBox body = new VBox();
    body.getChildren().addAll(
      communityCards
    );
    return body;
  }
}
