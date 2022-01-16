package openjfx.LifeGameFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        var scene = new Scene(new LifeGame(140,Color.BLACK,Color.DARKRED).givGame(), 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Game of Life");
    }

    public static void main(String[] args) {
        launch();
    }

}