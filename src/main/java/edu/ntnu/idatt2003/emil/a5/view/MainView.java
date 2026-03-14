package edu.ntnu.idatt2003.emil.a5.view;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.view.sub.CommunityCards;
import edu.ntnu.idatt2003.emil.a5.view.sub.PlayerHand;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends BorderPane {
  private final CasinoView casinoView;
  private final PlayerView playerView;

  public MainView(PokerController controller) {
    Scene root = new Scene(this, 1280, 720);
    HeaderView headerView = new HeaderView(controller, this);
    this.casinoView = new CasinoView(controller);
    this.playerView = new PlayerView(controller);

    setId("main_view");
    getStylesheets().add(getClass().getResource("/css/MainView.css").toExternalForm());
    setTop(headerView);
    setCenter(createBody());
    setBottom(playerView);
  }

  public ScrollPane createBody() {
    ScrollPane body = new ScrollPane();
    body.setFitToWidth(true);
    body.setFitToHeight(true);

    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(
        casinoView
    );
    body.setContent(vbox);
    return body;
  }
}
