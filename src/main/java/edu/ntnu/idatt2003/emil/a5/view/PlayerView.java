package edu.ntnu.idatt2003.emil.a5.view;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.view.sub.PlayerHand;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayerView extends StackPane {
  private final PokerController controller;
  private final PlayerHand playerHand;

  public PlayerView(PokerController controller) {
    this.controller = controller;
    this.playerHand = new PlayerHand(controller);
    getStylesheets().add(getClass().getResource("/css/PlayerView.css").toExternalForm());
    getChildren().add(createBody());
  }

  private VBox createBody() {
    VBox body = new VBox();
    body.getChildren().addAll(
      playerHand,
      createButtonRow()
    );
    return body;
  }

  private HBox createButtonRow() {
    HBox buttonRow = new HBox();
    buttonRow.setId("buttonRow");
    buttonRow.setSpacing(10);
    buttonRow.setAlignment(Pos.CENTER);

    buttonRow.getChildren().addAll(
      createStartButton(),
      createCheckButton(),
      createCallButton(),
      createRaiseButton(),
      createAllInButton()
    );
    return buttonRow;
  }

  private Button createStartButton() {
    Button startButton = new Button("Start Game");
    startButton.setOnAction(e -> {
      controller.handleStartGame();
    });
    return startButton;
  }

  private Button createCheckButton() {
    Button checkButton = new Button("Check");
    checkButton.setOnAction(e -> {
      controller.handleCheck();
    });
    return checkButton;
  }

  private Button createCallButton() {
    Button callButton = new Button("Call");
    callButton.setOnAction(e -> {
      controller.handleCall();
    });
    return callButton;
  }

  private Button createRaiseButton() {
    Button raiseButton = new Button("Raise");
    raiseButton.setOnAction(e -> {
      controller.handleRaise();
    });
    return raiseButton;
  }

  private Button createAllInButton() {
    Button allInButton = new Button("All In");
    allInButton.setOnAction(e -> {
      controller.handleAllIn();
    });
    return allInButton;
  }
}
