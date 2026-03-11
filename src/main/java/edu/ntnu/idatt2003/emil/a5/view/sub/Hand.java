package edu.ntnu.idatt2003.emil.a5.view.sub;

import edu.ntnu.idatt2003.emil.a5.App;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class HandView extends BorderPane {
  public HandView(App app) {
    HBox hand = new HBox();
    hand.setAlignment(Pos.CENTER);
    Image image = new Image(getClass().getResource("playing_cards.jpg").toExternalForm());
    ImageView imageView = new ImageView();
    imageView.setImage(image);
  }
}
