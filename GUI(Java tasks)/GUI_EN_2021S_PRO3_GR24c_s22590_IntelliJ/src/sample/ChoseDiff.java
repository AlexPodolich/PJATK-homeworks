package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoseDiff {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnchorPane anchorPane;

    ChoseDiff(Stage stage){
        this.stage = stage;
    }

    public Scene create(){
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(151);
        anchorPane.setPrefWidth(413);
        Button easyBtn = new Button("Easy");
        easyBtn.setLayoutX(29);
        easyBtn.setLayoutY(50);
        easyBtn.setFont(Font.font("System Bold", 24));
        easyBtn.setStyle("-fx-background-color: lightgreen;");
        easyBtn.setOnAction(actionEvent -> {
            Game.gameDiff = "Easy";
            Game game = new Game(stage);
            Scene scene = game.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Game " + Game.gameDiff + " difficulty");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        Button mediumBtn = new Button("Medium");
        mediumBtn.setLayoutX(146);
        mediumBtn.setLayoutY(50);
        mediumBtn.setFont(Font.font("System Bold", 24));
        mediumBtn.setStyle("-fx-background-color: yellow;");
        mediumBtn.setOnAction(actionEvent -> {
            Game.gameDiff = "Medium";
            Game game = new Game(stage);
            Scene scene = game.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Game " + Game.gameDiff + " difficulty");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        Button hardBtn = new Button("Hard");
        hardBtn.setLayoutX(302);
        hardBtn.setLayoutY(50);
        hardBtn.setFont(Font.font("System Bold", 24));
        hardBtn.setStyle("-fx-background-color: red;");
        hardBtn.setOnAction(actionEvent -> {
            Game.gameDiff = "Hard";
            Game game = new Game(stage);
            Scene scene = game.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Game " + Game.gameDiff + " difficulty");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        anchorPane.getChildren().addAll(easyBtn, mediumBtn, hardBtn);

        Scene scene = new Scene(anchorPane);
        return scene;
    }

    public void startEasyDiff(ActionEvent event) throws IOException{
        Game.gameDiff = "Easy";
        switchToGame(event);
    }

    public void startMediumDiff(ActionEvent event) throws IOException{
        Game.gameDiff = "Medium";
        switchToGame(event);
    }

    public void startHardDiff(ActionEvent event) throws IOException{
        Game.gameDiff = "Hard";
        switchToGame(event);
    }

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Nu Pogodi Game - Game " + Game.gameDiff + " difficulty");
        stage.show();
    }
}

