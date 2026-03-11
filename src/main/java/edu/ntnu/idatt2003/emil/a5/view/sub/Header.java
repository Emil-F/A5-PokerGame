package edu.ntnu.idatt2003.emil.a5.view.sub;

import edu.ntnu.idatt2003.emil.a5.view.MainView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HeaderView extends StackPane {
  public HeaderView(MainView view) {
    getChildren().add(getHomeBtn());
  }

  public Button getHomeBtn() {
    return new Button("Home");
  }
}
