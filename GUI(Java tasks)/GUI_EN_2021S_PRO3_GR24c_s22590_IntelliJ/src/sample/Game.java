package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Random;

public class Game {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnchorPane anchorPane;
    private int wolfPos;
    static int time = 0;
    static String gameDiff;
    private boolean isPlaying = true;
    private ImageView imgPos1;
    private ImageView imgPos2;
    private ImageView imgPos3;
    private ImageView imgPos4;
    private Label labelScore;
    private Label labelFails;
    private Label labelTime;

    Game(Stage stage){
        this.stage = stage;
    }

    public Scene create(){
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(400);
        anchorPane.setPrefWidth(534);
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(Game.class.getResource("resources/background.css").toExternalForm());
        anchorPane.getStyleClass().add("bodybg");
        anchorPane.setOnMouseEntered(mouseEvent -> {
            askWindow();
        });
        anchorPane.setOnKeyPressed(keyEvent -> {
            moveWolf();
        });
        Button backToMenu = new Button("Back to Menu");
        backToMenu.setLayoutX(14);
        backToMenu.setLayoutY(14);
        backToMenu.setFont(Font.font("14.0"));
        backToMenu.setStyle("-fx-background-color: purple;");
        backToMenu.setOnAction(actionEvent -> {
            switchToMenu();
        });
        labelScore = new Label("Score: 0");
        labelScore.setLayoutX(386);
        labelScore.setLayoutY(12);
        labelScore.setPrefHeight(35);
        labelScore.setPrefWidth(135);
        labelScore.setFont(Font.font("System Bold", 24));
        labelScore.setTextFill(Paint.valueOf("WHITE"));
        imgPos4 = new ImageView();
        imgPos4.setFitHeight(142);
        imgPos4.setFitWidth(127);
        imgPos4.setLayoutY(244);
        imgPos4.setLayoutX(259);
        Image wolf4 = new Image(getClass().getResource("resources/wolf2.png").toExternalForm());
        imgPos4.setImage(wolf4);
        imgPos3 = new ImageView();
        imgPos3.setFitHeight(142);
        imgPos3.setFitWidth(127);
        imgPos3.setLayoutY(244);
        imgPos3.setLayoutX(132);
        imgPos3.setVisible(false);
        Image wolf3 = new Image(getClass().getResource("resources/wolf1.png").toExternalForm());
        imgPos3.setImage(wolf3);
        imgPos2 = new ImageView();
        imgPos2.setFitHeight(142);
        imgPos2.setFitWidth(127);
        imgPos2.setLayoutY(125);
        imgPos2.setLayoutX(259);
        imgPos2.setVisible(false);
        Image wolf2 = new Image(getClass().getResource("resources/wolf2.png").toExternalForm());
        imgPos2.setImage(wolf2);
        imgPos1 = new ImageView();
        imgPos1.setFitHeight(142);
        imgPos1.setFitWidth(127);
        imgPos1.setLayoutY(125);
        imgPos1.setLayoutX(132);
        imgPos1.setVisible(false);
        Image wolf1 = new Image(getClass().getResource("resources/wolf1.png").toExternalForm());
        imgPos1.setImage(wolf1);
        ImageView chicken1 = new ImageView();
        chicken1.setFitHeight(72);
        chicken1.setFitWidth(66);
        chicken1.setLayoutY(53);
        chicken1.setLayoutX(444);
        Image chickenImg1 = new Image(getClass().getResource("resources/chicken1.png").toExternalForm());
        chicken1.setImage(chickenImg1);
        ImageView chicken2 = new ImageView();
        chicken2.setFitHeight(72);
        chicken2.setFitWidth(66);
        chicken2.setLayoutY(170);
        chicken2.setLayoutX(444);
        Image chickenImg2 = new Image(getClass().getResource("resources/chicken1.png").toExternalForm());
        chicken2.setImage(chickenImg2);
        ImageView chicken3 = new ImageView();
        chicken3.setFitHeight(72);
        chicken3.setFitWidth(66);
        chicken3.setLayoutY(53);
        chicken3.setLayoutX(14);
        Image chickenImg3 = new Image(getClass().getResource("resources/chicken2.png").toExternalForm());
        chicken3.setImage(chickenImg3);
        ImageView chicken4 = new ImageView();
        chicken4.setFitHeight(72);
        chicken4.setFitWidth(66);
        chicken4.setLayoutY(170);
        chicken4.setLayoutX(14);
        Image chickenImg4 = new Image(getClass().getResource("resources/chicken2.png").toExternalForm());
        chicken4.setImage(chickenImg4);
        Line line1 = new Line();
        line1.setEndX(12);
        line1.setEndY(44);
        line1.setLayoutX(114);
        line1.setLayoutY(125);
        line1.setStartX(-34);
        line1.setStartY(-0.36);
        line1.setStroke(Paint.valueOf("#803c00"));
        line1.setStrokeWidth(3);
        Line line2 = new Line();
        line2.setEndX(49);
        line2.setEndY(-0.36);
        line2.setLayoutX(31);
        line2.setLayoutY(125);
        line2.setStartX(-31);
        line2.setStartY(-0.36);
        line2.setStroke(Paint.valueOf("#803c00"));
        line2.setStrokeWidth(3);
        Line line3 = new Line();
        line3.setEndX(47);
        line3.setEndY(-3);
        line3.setLayoutX(33);
        line3.setLayoutY(245);
        line3.setStartX(-33);
        line3.setStartY(-2);
        line3.setStroke(Paint.valueOf("#803c00"));
        line3.setStrokeWidth(3);
        Line line4 = new Line();
        line4.setEndX(12);
        line4.setEndY(44);
        line4.setLayoutX(114);
        line4.setLayoutY(245);
        line4.setStartX(-33);
        line4.setStartY(-2);
        line4.setStroke(Paint.valueOf("#803c00"));
        line4.setStrokeWidth(3);
        Line line5 = new Line();
        line5.setEndX(58);
        line5.setEndY(6);
        line5.setLayoutX(476);
        line5.setLayoutY(119);
        line5.setStartX(-30);
        line5.setStartY(6);
        line5.setStroke(Paint.valueOf("#803c00"));
        line5.setStrokeWidth(3);
        Line line6 = new Line();
        line6.setEndX(-4.7);
        line6.setEndY(42);
        line6.setLayoutX(397);
        line6.setLayoutY(126);
        line6.setStartX(47.7);
        line6.setStartY(-0.5);
        line6.setStroke(Paint.valueOf("#803c00"));
        line6.setStrokeWidth(3);
        Line line7 = new Line();
        line7.setEndX(58);
        line7.setEndY(-0.36);
        line7.setLayoutX(476);
        line7.setLayoutY(243);
        line7.setStartX(-31);
        line7.setStartY(-0.36);
        line7.setStroke(Paint.valueOf("#803c00"));
        line7.setStrokeWidth(3);
        Line line8 = new Line();
        line8.setEndX(-4.7);
        line8.setEndY(42);
        line8.setLayoutX(397);
        line8.setLayoutY(245);
        line8.setStartX(47.5);
        line8.setStartY(-1.8);
        line8.setStroke(Paint.valueOf("#803c00"));
        line8.setStrokeWidth(3);
        labelFails = new Label("Fails: 0 / 4");
        labelFails.setLayoutX(263);
        labelFails.setLayoutY(12);
        labelFails.setPrefHeight(35);
        labelFails.setPrefWidth(119);
        labelFails.setTextFill(Paint.valueOf("WHITE"));
        labelFails.setFont(Font.font("System Bold", 24));
        labelTime = new Label("Time: 0");
        labelTime.setLayoutX(132);
        labelTime.setLayoutY(12);
        labelTime.setPrefHeight(35);
        labelTime.setPrefWidth(119);
        labelTime.setTextFill(Paint.valueOf("WHITE"));
        labelTime.setFont(Font.font("System Bold", 24));

        anchorPane.getChildren().addAll(backToMenu, labelScore, imgPos1, imgPos2, imgPos3, imgPos4, chicken1, chicken2, chicken3, chicken4, line1, line2, line3, line4, line5
        , line6, line7, line8, labelFails, labelTime);
        init();
        return scene;
    }

    public void switchToMenu(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to save your result?", ButtonType.YES, ButtonType.NO);

        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

        if (ButtonType.NO.equals(result)) {
            Menu menu = new Menu(stage);
            Scene menuScene = menu.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Menu");
            stage.setScene(menuScene);
            stage.setResizable(false);
            stage.show();
        }
        if (ButtonType.YES.equals(result)) {
            saveBestScore();
            Menu menu = new Menu(stage);
            Scene menuScene = menu.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Menu");
            stage.setScene(menuScene);
            stage.setResizable(false);
            stage.show();
        }
        isPlaying = false;
    }

    public void moveUp(){
        if(!imgPos3.isVisible() || !imgPos4.isVisible()){
            if(imgPos3.isVisible()){
                imgPos1.setVisible(true);
                imgPos3.setVisible(false);
            }else if((imgPos4.isVisible())){
                imgPos2.setVisible(true);
                imgPos4.setVisible(false);
            }
        }
    }

    public void moveDown(){
        if(!imgPos1.isVisible() || !imgPos2.isVisible()){
            if(imgPos1.isVisible()){
                imgPos1.setVisible(false);
                imgPos3.setVisible(true);
            }else if((imgPos2.isVisible())){
                imgPos2.setVisible(false);
                imgPos4.setVisible(true);
            }
        }
    }

    public void moveLeft(){
        if(!imgPos2.isVisible() || !imgPos4.isVisible()){
            if(imgPos2.isVisible()){
                imgPos2.setVisible(false);
                imgPos1.setVisible(true);
            }else if((imgPos4.isVisible())){
                imgPos4.setVisible(false);
                imgPos3.setVisible(true);
            }
        }
    }

    public void moveRight(){
        if(!imgPos1.isVisible() || !imgPos3.isVisible()){
            if(imgPos1.isVisible()){
                imgPos2.setVisible(true);
                imgPos1.setVisible(false);
            }else if((imgPos3.isVisible())){
                imgPos4.setVisible(true);
                imgPos3.setVisible(false);
            }
        }
    }

    public void moveWolf() {
        Scene thisScene = imgPos1.getScene();
        thisScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W ->{
                        moveUp();
                    }
                    case S ->{
                        moveDown();
                    }
                    case A ->{
                        moveLeft();
                    }
                    case D ->{
                        moveRight();
                    }
                }
            }
        });
    }

    public void askWindow(){
        Stage thisStage = (Stage)imgPos4.getScene().getWindow();
        Scene thisScene = imgPos4.getScene();

        thisScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

            public void handle(KeyEvent key) {
                if (keyComb.match(key)) {
                    switchToMenu();
                }
            }
        });
        thisStage.setOnCloseRequest(evt -> {
            isPlaying = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to save your result?", ButtonType.YES, ButtonType.NO);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.NO.equals(result)) {
                evt.consume();
                Menu menu = new Menu(stage);
                Scene menuScene = menu.create();
                Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
                stage.getIcons().add(icon);
                stage.setTitle("Nu Pogodi Game - Menu");
                stage.setScene(menuScene);
                stage.setResizable(false);
                stage.show();
            }
            if (ButtonType.YES.equals(result)) {
                evt.consume();
                saveBestScore();
                Menu menu = new Menu(stage);
                Scene menuScene = menu.create();
                Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
                stage.getIcons().add(icon);
                stage.setTitle("Nu Pogodi Game - Menu");
                stage.setScene(menuScene);
                stage.setResizable(false);
                stage.show();
            }
        });
    }

    public void saveBestScore(){
        String userName = AlertDialog.display();
        String filePath = "BestScore.txt";
        String addString = System.lineSeparator() + userName + " " + labelScore.getText().split(" ")[1];
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

    public void createEgg() throws InterruptedException {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                try {
                    int duration = 3000;
                    if(time < 30){
                        duration = 5000;
                    }else if(time >= 30 && time <  60){
                        duration = 4000;
                    }else  if(time >= 60 && time < 120){
                        duration = 3000;
                    }else if(time >= 120&& time < 180){
                        duration = 2000;
                    }else if(time >= 180){
                        duration = 1000;
                    }
                    Random random = new Random();
                    int rndNum = random.nextInt(4);
                    switch(rndNum){
                        case 0 ->{
                            moveEgg(anchorPane, 78, 102, 40, 40, duration, 1);
                        }
                        case 1 ->{
                            moveEgg(anchorPane, 426, 102, -40, 40, duration, 2);
                        }
                        case 2 ->{
                            moveEgg(anchorPane, 78, 222, 40, 40, duration, 3);
                        }
                        case 3 ->{
                            moveEgg(anchorPane, 426, 222, -40, 40, duration, 4);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void moveEgg(AnchorPane background,int startX, int startY, int targetX, int targetY, int duration, int idChiken) throws InterruptedException {
        Image image = new Image(getClass().getResource("resources/egg.png").toExternalForm());
        ImageView eggImg = new ImageView(image);
        eggImg.setX(startX);
        eggImg.setY(startY);
        eggImg.setFitHeight(30);
        eggImg.setFitWidth(20);
        background.getChildren().add(eggImg);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(eggImg);
        translateTransition.setByX(targetX);
        translateTransition.setByY(targetY);
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.play();
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(eggImg);
        rotateTransition.setDuration(Duration.millis(duration));
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(360);
        if(startX == 78){
            rotateTransition.setByAngle(360);
        }else {
            rotateTransition.setByAngle(-360);
        }
        rotateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            switch(idChiken){
                case 1->{
                    if(imgPos1.isVisible()){
                        int score = 0;
                        if(Game.gameDiff.equals("Easy")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 3;
                        }else if(Game.gameDiff.equals("Medium")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 2;
                        }else if(Game.gameDiff.equals("Hard")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 1;
                        }

                        labelScore.setText("Score: " + score);
                        eggImg.setVisible(false);
                    }else {
                        int fails = Integer.parseInt(labelFails.getText().split(" ")[1]) + 1;
                        labelFails.setText("Fails: " + fails + " / 4");
                        eggImg.setVisible(false);
                    }
                }
                case 2->{
                    if(imgPos2.isVisible()){
                        int score = 0;
                        if(Game.gameDiff.equals("Easy")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 3;
                        }else if(Game.gameDiff.equals("Medium")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 2;
                        }else if(Game.gameDiff.equals("Hard")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 1;
                        }
                        labelScore.setText("Score: " + score);
                        eggImg.setVisible(false);
                    }else {
                        int fails = Integer.parseInt(labelFails.getText().split(" ")[1]) + 1;
                        labelFails.setText("Fails: " + fails + " / 4");
                        eggImg.setVisible(false);
                    }
                }
                case 3->{
                    if(imgPos3.isVisible()){
                        int score = 0;
                        if(Game.gameDiff.equals("Easy")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 3;
                        }else if(Game.gameDiff.equals("Medium")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 2;
                        }else if(Game.gameDiff.equals("Hard")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 1;
                        }
                        labelScore.setText("Score: " + score);
                        eggImg.setVisible(false);
                    }else {
                        int fails = Integer.parseInt(labelFails.getText().split(" ")[1]) + 1;
                        labelFails.setText("Fails: " + fails + " / 4");
                        eggImg.setVisible(false);
                    }
                }
                case 4->{
                    if(imgPos4.isVisible()){
                        int score = 0;
                        if(Game.gameDiff.equals("Easy")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 3;
                        }else if(Game.gameDiff.equals("Medium")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 2;
                        }else if(Game.gameDiff.equals("Hard")){
                            score = Integer.parseInt(labelScore.getText().split(" ")[1]) + 1;
                        }
                        labelScore.setText("Score: " + score);
                        eggImg.setVisible(false);
                    }else {
                        int fails = Integer.parseInt(labelFails.getText().split(" ")[1]) + 1;
                        labelFails.setText("Fails: " + fails + " / 4");
                        eggImg.setVisible(false);
                    }
                }
            }
        });
    }

    public void init() {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                isPlaying = true;
                int fails = 0;
                while (fails < 4 && isPlaying){
                    try {
                        Thread.sleep(1000);
                        fails = Integer.parseInt(labelFails.getText().split(" ")[1]);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                labelTime.setText("Time: " + time);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time += 1;
                    System.out.println(time);
                    int rndNum = new Random().nextInt(2);
                    if(rndNum == 1){
                        try {
                            createEgg();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                time = 0;
                if(fails >= 4){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            saveBestScore();
                            Menu menu = new Menu(stage);
                            Scene menuScene = menu.create();
                            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
                            stage.getIcons().add(icon);
                            stage.setTitle("Nu Pogodi Game - Menu");
                            stage.setScene(menuScene);
                            stage.setResizable(false);
                            stage.show();
                        }
                    });
                }
            }
        });
        thread.start();
    }

}
