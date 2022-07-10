package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;

public class AlertDialog {
    static String name;

    public static String display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField textName = new TextField();

        Button button = new Button("Submit");
        button.setOnAction(e -> {
            name = textName.getText();
            stage.close();
        });

        Label label1 = new Label("Enter your name before exit");
        Label label2 = new Label("Your name: ");

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(textName, 1,1);
        layout.add(button, 1,2);
        layout.add(label1, 1,0);
        layout.add(label2, 0,1);

        Scene scene = new Scene(layout, 250, 100);
        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.showAndWait();

        return name;
    }
}
