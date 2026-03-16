package edu.ntnu.idatt2003.emil.a5.view.casino;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CasinoView extends StackPane {
  private final PokerController controller;
  private final CommunityCards communityCards;

  public CasinoView(PokerController controller) {
    this.controller = controller;
    this.communityCards = new CommunityCards(controller);
    getChildren().addAll(
      createBody()
    );
  }

  private VBox createBody() {
    VBox body = new VBox();
    body.getChildren().addAll(
      createRoundInfo(),
      communityCards
    );
    return body;
  }

  private HBox createRoundInfo() {
    HBox roundInfo = new HBox();
    roundInfo.setSpacing(10);
    roundInfo.setAlignment(Pos.BASELINE_CENTER);

    Label roundText = new Label("Round: ");
    Label currentRound = new Label("Pre-Flop");
    currentRound.textProperty().bind(controller.getCurrentRound());

    Label potText = new Label("Pot value: ");
    Label potValue =  new Label("0");
    potValue.textProperty().bind(controller.getPotValue());

    roundInfo.getChildren().addAll(
      roundText,
      currentRound
    );
    return roundInfo;
  }
}
