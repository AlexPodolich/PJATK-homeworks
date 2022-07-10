package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SceneGameplay {
    private Group group;
    private Stage stage;
    private ImageView background;
    private Scene scene;
    private ImageView wolfImg;
    private Label labelCapturedEggs;
    private Label labelBrokenEggs;

    public SceneGameplay(Stage stage){
        this.stage = stage;
        group = new Group();
    }

    public Scene initializeAllObjects(){
        group = new Group();
        Image img = new Image(getClass().getResource("nupogod.png").toExternalForm());
        background = new ImageView(img);
        background.setFitHeight(500);
        background.setFitWidth(800);
        Image wolf = new Image("sample\\wolf.png");
        wolfImg = new ImageView();
        wolfImg.setImage(wolf);
        wolfImg.setFitHeight(230);
        wolfImg.setFitWidth(200);
        wolfImg.setLayoutX(460);
        wolfImg.setLayoutY(140);
        labelCapturedEggs = new Label("Captured eggs: 0");
        labelCapturedEggs.setLayoutX(450);
        labelCapturedEggs.setLayoutY(10);
        labelCapturedEggs.setTextFill(Color.color(0,1 ,0));
        labelCapturedEggs.setFont(Font.font(32));
        labelBrokenEggs = new Label("Broken eggs: 0");
        labelBrokenEggs.setLayoutX(230);
        labelBrokenEggs.setLayoutY(10);
        labelBrokenEggs.setTextFill(Color.color(1, 0 ,0));
        labelBrokenEggs.setFont(Font.font(32));
        group.getChildren().add(background);
        group.getChildren().add(wolfImg);
        group.getChildren().add(labelCapturedEggs);
        group.getChildren().add(labelBrokenEggs);
        scene = new Scene(group);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            KeyCombination combintation = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
            @Override
            public void handle(KeyEvent keyEvent) {
                if (combintation.match(keyEvent)) {
                    saveScores();
                    SceneMenu sceneMenu = new SceneMenu(stage);
                    Scene scene = sceneMenu.initializeAllObjects();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                moveWolf();
            }
        });
        startThread();
        return scene;
    }

    public void moveW(){
        if(wolfImg.getLayoutX() == 340 && wolfImg.getLayoutY() == 240){
            wolfImg.setLayoutX(340);
            wolfImg.setLayoutY(140);
        }
        if(wolfImg.getLayoutX() == 460 && wolfImg.getLayoutY() == 240){
            wolfImg.setLayoutX(460);
            wolfImg.setLayoutY(140);
        }
    }

    public void moveA(){
        if(wolfImg.getLayoutY() == 140 && wolfImg.getLayoutX() == 460){
            wolfImg.setLayoutX(340);
            wolfImg.setLayoutY(140);
            wolfImg.getTransforms().add(new Rotate(-180, Rotate.Y_AXIS));
        }
        if(wolfImg.getLayoutY() == 240 && wolfImg.getLayoutX() == 460){
            wolfImg.setLayoutX(340);
            wolfImg.setLayoutY(240);
            wolfImg.getTransforms().add(new Rotate(-180, Rotate.Y_AXIS));
        }
    }

    public void moveS(){
        if(wolfImg.getLayoutX() == 340 && wolfImg.getLayoutY() == 140){
            wolfImg.setLayoutY(240);
            wolfImg.setLayoutX(340);
        }
        if(wolfImg.getLayoutX() == 460 && wolfImg.getLayoutY() == 140){
            wolfImg.setLayoutY(240);
            wolfImg.setLayoutX(460);
        }
    }

    public void moveD(){
        if(wolfImg.getLayoutY() == 140 && wolfImg.getLayoutX() == 340){
            wolfImg.setLayoutX(460);
            wolfImg.setLayoutY(140);
            wolfImg.getTransforms().add(new Rotate(180, Rotate.Y_AXIS));
        }
        if(wolfImg.getLayoutY() == 240 && wolfImg.getLayoutX() == 340){
            wolfImg.setLayoutX(460);
            wolfImg.setLayoutY(240);
            wolfImg.getTransforms().add(new Rotate(180, Rotate.Y_AXIS));
        }
    }

    public void moveWolf(){
        Scene thisScene = wolfImg.getScene();
        thisScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W ->{
                        moveW();
                    }
                    case S ->{
                        moveS();
                    }
                    case A ->{
                        moveA();
                    }
                    case D ->{
                        moveD();
                    }
                }
            }
        });
    }

    public void animateEgg(Group group, int caseNumber) throws InterruptedException {
        Image image = new Image("sample\\egg.png");
        ImageView egg = new ImageView(image);
        if(caseNumber == 1){
            egg.setX(30);
            egg.setY(120);
        }else if(caseNumber == 2){
            egg.setX(770);
            egg.setY(120);
        }else if(caseNumber == 3){
            egg.setX(30);
            egg.setY(250);
        }else if(caseNumber == 4){
            egg.setX(740);
            egg.setY(250);
        }
        egg.setFitHeight(40);
        egg.setFitWidth(30);
        group.getChildren().add(egg);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(egg);
        if(caseNumber == 1){
            translateTransition.setByX(100);
            translateTransition.setByY(80);
        }else if(caseNumber == 2){
            translateTransition.setByX(-130);
            translateTransition.setByY(80);
        }else if(caseNumber == 3){
            translateTransition.setByX(100);
            translateTransition.setByY(70);
        }else if(caseNumber == 4){
            translateTransition.setByX(-100);
            translateTransition.setByY(70);
        }
        translateTransition.setDuration(Duration.millis(3000));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            switch(caseNumber){
                case 1->{
                    if(wolfImg.getLayoutX() == 340 && wolfImg.getLayoutY() == 140){
                        int capturedEggs = Integer.parseInt(labelCapturedEggs.getText().split(" ")[2]) + 1;
                        labelCapturedEggs.setText("Captured eggs: " + capturedEggs);
                        egg.setVisible(false);
                    }else {
                        int brokenEggs = Integer.parseInt(labelBrokenEggs.getText().split(" ")[2]) + 1;
                        labelBrokenEggs.setText("Broken eggs: " + brokenEggs);
                        egg.setVisible(false);
                    }
                }
                case 2->{
                    if(wolfImg.getLayoutX() == 460 && wolfImg.getLayoutY() == 140){
                        int capturedEggs = Integer.parseInt(labelCapturedEggs.getText().split(" ")[2]) + 1;
                        labelCapturedEggs.setText("Captured eggs: " + capturedEggs);
                        egg.setVisible(false);
                    }else {
                        int brokenEggs = Integer.parseInt(labelBrokenEggs.getText().split(" ")[2]) + 1;
                        labelBrokenEggs.setText("Broken eggs: " + brokenEggs);
                        egg.setVisible(false);
                    }
                }
                case 3->{
                    if(wolfImg.getLayoutX() == 340 && wolfImg.getLayoutY() == 240){
                        int capturedEggs = Integer.parseInt(labelCapturedEggs.getText().split(" ")[2]) + 1;
                        labelCapturedEggs.setText("Captured eggs: " + capturedEggs);
                        egg.setVisible(false);
                    }else {
                        int brokenEggs = Integer.parseInt(labelBrokenEggs.getText().split(" ")[2]) + 1;
                        labelBrokenEggs.setText("Broken eggs: " + brokenEggs);
                        egg.setVisible(false);
                    }
                }
                case 4->{
                    if(wolfImg.getLayoutX() == 460 && wolfImg.getLayoutY() == 240){
                        int capturedEggs = Integer.parseInt(labelCapturedEggs.getText().split(" ")[2]) + 1;
                        labelCapturedEggs.setText("Captured eggs: " + capturedEggs);
                        egg.setVisible(false);
                    }else {
                        int brokenEggs = Integer.parseInt(labelBrokenEggs.getText().split(" ")[2]) + 1;
                        labelBrokenEggs.setText("Broken eggs: " + brokenEggs);
                        egg.setVisible(false);
                    }
                }
            }
        });
    }

    public void addEgg() throws InterruptedException {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                try {
                    int caseNumber = new Random().nextInt(4);
                    switch(caseNumber){
                        case 0 ->{
                            animateEgg(group, 1);
                        }
                        case 1 ->{
                            animateEgg(group, 2);
                        }
                        case 2 ->{
                            animateEgg(group, 3);
                        }
                        case 3 ->{
                            animateEgg(group, 4);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveScores(){
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter your name!");
        td.setTitle("End Of Game");
        td.showAndWait();
        String filePath = "ListOfBest.txt";
        String addString = System.lineSeparator() + td.getEditor().getText() + " " + labelCapturedEggs.getText().split(" ")[2];
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(addString);
            bufferWriter.close();
        }
        catch (IOException err) {
            System.out.println(err);
        }
    }

    public void startThread() {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                int brokenEggs = 0;
                int capturedEggs = 0;
                while (brokenEggs < 4){
                    try {
                        Thread.sleep(1500);
                        addEgg();
                        brokenEggs = Integer.parseInt(labelBrokenEggs.getText().split(" ")[2]);
                        capturedEggs = Integer.parseInt(labelCapturedEggs.getText().split(" ")[2]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                labelCapturedEggs.setText("Captured eggs: " + capturedEggs);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        saveScores();
                        SceneMenu sceneMenu = new SceneMenu(stage);
                        Scene scene = sceneMenu.initializeAllObjects();
                        stage.setScene(scene);
                        stage.show();
                    }
                });
            }
        });
        thread.start();
    }
}
