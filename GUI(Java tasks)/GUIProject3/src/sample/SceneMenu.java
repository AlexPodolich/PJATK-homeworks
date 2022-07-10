package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class SceneMenu {
    private Group group;
    private Stage stage;
    private ImageView background;
    private Scene scene;
    private Button btnStart;
    private Button btnScore;
    private Button btnExit;

    public SceneMenu(Stage stage){
        this.stage = stage;
        group = new Group();
    }

    public Scene initializeAllObjects(){
        group = new Group();
        Image img = new Image(getClass().getResource("menu.jpg").toExternalForm());
        background = new ImageView(img);
        background.setFitHeight(450);
        background.setFitWidth(600);
        btnStart = new Button("Start game");
        btnStart.setLayoutX(215);
        btnStart.setLayoutY(100);
        btnStart.setFont(Font.font(28));
        btnStart.setTextFill(Paint.valueOf("BLUE"));
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneGameplay sceneGameplay = new SceneGameplay(stage);
                Scene scene = sceneGameplay.initializeAllObjects();
                stage.setScene(scene);
                stage.show();
            }
        });
        btnScore = new Button("High Scores");
        btnScore.setLayoutX(210);
        btnScore.setLayoutY(200);
        btnScore.setFont(Font.font(28));
        btnScore.setTextFill(Paint.valueOf("GREEN"));
        btnScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneScores sceneScores = new SceneScores(stage);
                Scene scene = sceneScores.initializeAllObjects();
                stage.setScene(scene);
                stage.show();
            }
        });
        btnExit = new Button("Exit");
        btnExit.setLayoutX(260);
        btnExit.setLayoutY(300);
        btnExit.setTextFill(Paint.valueOf("RED"));
        btnExit.setFont(Font.font(28));
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        group.getChildren().add(background);
        group.getChildren().add(btnStart);
        group.getChildren().add(btnScore);
        group.getChildren().add(btnExit);
        scene = new Scene(group);
        return scene;
    }
}
