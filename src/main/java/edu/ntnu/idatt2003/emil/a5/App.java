package edu.ntnu.idatt2003.emil.a5;

import edu.ntnu.idatt2003.emil.a5.controller.PokerController;
import edu.ntnu.idatt2003.emil.a5.model.PokerGame;
import edu.ntnu.idatt2003.emil.a5.view.PokerView;

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
    PokerGame game = new PokerGame();
    PokerController controller = new PokerController(game);
    PokerView view = new PokerView(controller);
    Scene scene = new Scene(view, 1280, 720);

    System.out.println(game.getPlayer().toString());
    game.getBots().forEach(bot -> System.out.println(bot.toString()));

    try {
      Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.jpg"), "Icon cannot be null"));
      stage.getIcons().add(icon);
    } catch (NullPointerException e) {
      System.err.println("Exception message: " + e.getMessage());
    }

    stage.setTitle("Card Game");
    stage.setScene(scene);
    stage.show();
  }

  static void main(String[] args) {
    launch(args);
  }
}
