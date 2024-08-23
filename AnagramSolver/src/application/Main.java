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
        
        textField.setPrefWidth(200);  // Set preferred width
        textField.setPrefHeight(30);  // Set preferred height
        textField.setMaxWidth(200);   // Set maximum width
        textField.setMaxHeight(30);   // Set maximum height
        textField.setMinWidth(200);   // Set minimum width
        textField.setMinHeight(30);   // Set minimum height
        
        //textField.setPromptText("Type the word here");
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
