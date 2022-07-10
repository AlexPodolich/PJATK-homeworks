package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SceneScores {
    private Group group;
    private Stage stage;
    private Scene scene;
    private Button btnMenu;
    private ImageView background;
    private Image img;
    private ListView<String> scoreList;

    public SceneScores(Stage stage){
        this.stage = stage;
        group = new Group();
    }

    public Scene initializeAllObjects(){
        group = new Group();
        img = new Image(getClass().getResource("scores.jpg").toExternalForm());
        background = new ImageView(img);
        background.setFitHeight(520);
        background.setFitWidth(400);
        btnMenu = new Button("Menu");
        btnMenu.setLayoutX(165);
        btnMenu.setLayoutY(30);
        btnMenu.setFont(Font.font(22));
        btnMenu.setTextFill(Paint.valueOf("GREEN"));
        btnMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneMenu sceneMenu = new SceneMenu(stage);
                Scene scene = sceneMenu.initializeAllObjects();
                stage.setScene(scene);
                stage.show();
            }
        });
        scoreList = new ListView();
        scoreList.setLayoutX(80);
        scoreList.setLayoutY(140);
        scoreList.setPrefHeight(300);
        scoreList.setPrefWidth(250);
        ArrayList<String> bestList = new ArrayList<>();
        try {
            File file = new File("ListOfBest.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                bestList.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scoreList.getItems().addAll(bestList);
        group.getChildren().add(background);
        group.getChildren().add(btnMenu);
        group.getChildren().add(scoreList);
        scene = new Scene(group);
        return scene;
    }
}
