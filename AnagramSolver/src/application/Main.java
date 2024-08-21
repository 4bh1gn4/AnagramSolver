package application;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a TextField for user input
        TextField textField = new TextField();
        textField.setPromptText("Type your word here");

        // Create a Button to submit the input
        Button submitButton = new Button("Submit");

        // Handle the button click event
        submitButton.setOnAction(e -> {
            String userInput = textField.getText();
            System.out.println("User typed: " + userInput);
            // You can add more logic here to handle the input
        });

        // Layout the components in a vertical box
        VBox vbox = new VBox(10, textField, submitButton);
        vbox.setPadding(new javafx.geometry.Insets(20));

        // Set up the Scene and Stage
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Anagram Solver Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
