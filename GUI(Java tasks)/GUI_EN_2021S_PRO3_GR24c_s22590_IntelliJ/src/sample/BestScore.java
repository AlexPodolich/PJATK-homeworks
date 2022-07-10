package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BestScore {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnchorPane anchorPane;
    private ListView<Person> bestScoreList;


    BestScore(Stage stage){
        this.stage = stage;
    }

    public Scene create() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(292);
        anchorPane.setPrefWidth(291);
        Button backToMenu = new Button("Back to Menu");
        backToMenu.setLayoutY(14);
        backToMenu.setLayoutX(83);
        backToMenu.setStyle("-fx-background-color: purple;");
        backToMenu.setFont(Font.font("System Bold", 16));
        backToMenu.setOnAction(actionEvent -> {
            Menu menu = new Menu(stage);
            Scene scene = menu.create();
            Image icon = new Image(getClass().getResource("resources/icon.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Nu Pogodi Game - Menu");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        bestScoreList = new ListView();
        bestScoreList.setLayoutX(46);
        bestScoreList.setLayoutY(58);
        bestScoreList.setPrefHeight(200);
        bestScoreList.setPrefWidth(200);
        ArrayList<Person> peopleList = loadFromFile();
        Collections.sort(peopleList);
        bestScoreList.getItems().addAll(peopleList);
        anchorPane.getChildren().addAll(backToMenu, bestScoreList);
        Scene scene = new Scene(anchorPane);
        return scene;
    }

    public ArrayList<Person> loadFromFile(){
        ArrayList<Person> people = new ArrayList<>();
        try {
            File file = new File("BestScore.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] splitedLine = line.split(" ");
                String name = splitedLine[0];
                int score = Integer.parseInt(splitedLine[1]);
                people.add(new Person(name, score));
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }

}
