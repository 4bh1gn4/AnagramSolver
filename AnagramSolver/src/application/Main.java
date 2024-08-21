package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anagram Solver");
        Label temp = new Label("yept");
        Label resultLabel = new Label();
        TextField textField = new TextField();
        
        textField.setPromptText("Type the word here");
        textField.setStyle("-fx-prompt-text-fill: gray;");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
        	System.out.println(textField.getPromptText());
        	String userInput = textField.getText();
        	if (AnagramUtils.isAnagram(userInput, "type")) {
        		resultLabel.setText("Correct");
           	}
        	else {
        		resultLabel.setText("Incorrect");
        	}
        });
        Label welcomeLabel = new Label("Welcome to JavaFX!");
        Button readyButton = new Button("Ready");

        VBox layout = new VBox(10, temp, textField, submitButton, resultLabel);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
