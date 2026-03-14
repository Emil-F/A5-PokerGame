package edu.ntnu.idatt2003.emil.a5.view;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HeaderView extends StackPane {
  private final PokerController controller;

  public HeaderView(PokerController pokerController, MainView view) {
    this.controller = pokerController;
    setId("header");
    getStylesheets().add(getClass().getResource("/css/header.css").toExternalForm());
    setAlignment(Pos.CENTER);
    Text title = new Text("PokerGame");
    title.setId("title");
    getChildren().addAll(title, createButtonRow());
  }

  public HBox createButtonRow() {
    HBox buttonRow = new HBox();
    buttonRow.setAlignment(Pos.CENTER);
    buttonRow.setSpacing(10);
    buttonRow.getChildren().addAll(

    );
    return buttonRow;
  }
}
