package application;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	Label word = new Label("ytep");
    	Label correctWord = new Label("type");
    	
        // Create a TextField for user input
        TextField textField = new TextField();
        textField.setPromptText("Type the word here");

        // Create a Button to submit the input
        Button submitButton = new Button("Submit");

        // Handle the button click event
        submitButton.setOnAction(e -> {
            String userInput = textField.getText();
            System.out.println("User typed: " + userInput);
            
            success(userInput, correctWord);
            // You can add more logic here to handle the input
        });

        // Layout the components in a vertical box
        VBox vbox = new VBox(10, word, textField, submitButton);
        vbox.setPadding(new javafx.geometry.Insets(20));

        // Set up the Scene and Stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Anagram Solver Game");
        primaryStage.show();
    }
    
    private void success(String user, Label text) {
    	if (text.getText().equalsIgnoreCase(user)) {
    		System.out.println("Correct! Words match!");
    	}
    	else {
    		System.out.println("Wrong.");
    	}
    }
    public static void main(String[] args) {
        launch(args);
    }
}
