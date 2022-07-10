package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.*;

public class Menu {

    private Stage stage;
    private AnchorPane anchorPane;

    public void disable(){
        Stage thisStage = (Stage)anchorPane.getScene().getWindow();
        thisStage.setOnCloseRequest(evt -> {
            System.exit(0);
        });
    }

    public Menu(Stage stage){
        this.stage = stage;
    }


    public Scene create(){
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(453);
        anchorPane.setPrefWidth(417);
        anchorPane.setOnMouseEntered(mouseEvent -> {
            disable();
        });
        Label label = new Label("Nu Pogodi! Game");
        label.setLayoutX(66);
        label.setLayoutY(21);
        label.setFont(Font.font("MV Boli", 36));
        Image image = new Image(getClass().getResource("resources/icon.png").toExternalForm());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(174);
        imageView.setFitWidth(174);
        imageView.setLayoutX(122);
        imageView.setLayoutY(89);
        imageView.setImage(image);
        Button startGameBtn = new Button("Start Game");
        startGameBtn.setLayoutX(143);
        startGameBtn.setLayoutY(285);
        startGameBtn.setStyle("-fx-background-color: lime;");
        startGameBtn.setFont(Font.font("System Bold", 20));
        startGameBtn.setOnAction(actionEvent -> {
            ChoseDiff choseDiff = new ChoseDiff(stage);
            Scene scene = choseDiff.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        Button bestScoreBtn = new Button("Best Score");
        bestScoreBtn.setLayoutX(147);
        bestScoreBtn.setLayoutY(342);
        bestScoreBtn.setStyle("-fx-background-color: purple;");
        bestScoreBtn.setFont(Font.font("System Bold", 20));
        bestScoreBtn.setOnAction(actionEvent -> {
            BestScore bestScore = new BestScore(stage);
            Scene scene = bestScore.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        Button exit = new Button("Exit");
        exit.setLayoutX(178);
        exit.setLayoutY(401);
        exit.setStyle("-fx-background-color: red;");
        exit.setFont(Font.font("System Bold", 20));
        exit.setOnAction(actionEvent -> {
            stage.close();
            System.exit(0);
        });
        anchorPane.getChildren().addAll(label, imageView, startGameBtn, bestScoreBtn, exit);
        Scene scene = new Scene(anchorPane);
        return scene;
    }

}
