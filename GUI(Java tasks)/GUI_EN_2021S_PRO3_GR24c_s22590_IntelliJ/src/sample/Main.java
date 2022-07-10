package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Menu menu = new Menu(primaryStage);
        Scene scene = menu.create();
        Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Nu Pogodi Game - Menu");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
