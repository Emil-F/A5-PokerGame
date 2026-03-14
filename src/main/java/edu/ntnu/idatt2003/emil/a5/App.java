package edu.ntnu.idatt2003.emil.a5;

import edu.ntnu.idatt2003.emil.a5.controller.MainController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
  private BorderPane root;

  @Override
  public void start(Stage stage) throws Exception {
    MainController controller = new MainController();
    Scene scene = controller.getMainView().getScene();

    try {
      Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.jpg"), "Icon cannot be null"));
      stage.getIcons().add(icon);
    } catch (NullPointerException e) {
      System.err.println("Exception message: " + e.getMessage());
    }

    stage.setTitle("Poker Game");
    stage.setScene(scene);
    stage.show();
  }

  static void main(String[] args) {
    launch(args);
  }
}
