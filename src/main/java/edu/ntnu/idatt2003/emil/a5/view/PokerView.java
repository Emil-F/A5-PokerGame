package edu.ntnu.idatt2003.emil.a5.view;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.view.sub.CommunityCardsView;
import edu.ntnu.idatt2003.emil.a5.view.sub.HeaderView;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
  public MainView(PokerController pokerController) {
    setId("main_view");
    getStylesheets().add(getClass().getResource("/css/MainView.css").toExternalForm());
    setTop(new HeaderView(this));
    setCenter(new CommunityCardsView(pokerController));
  }
}
