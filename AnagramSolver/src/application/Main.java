package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

 

public class Main extends Application {
	private String correctWord;
	private String jumbledWord;
	private int wordIndex;
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anagram Solver");
        wordIndex = 0;
        correctWord = AnagramUtils.getRandomWord(wordIndex);
        jumbledWord = AnagramUtils.jumbleWord(correctWord);
        
        Label prompt = new Label(jumbledWord);
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
        Button nextButton = new Button("Next");
        nextButton.setVisible(false);
        
        submitButton.setOnAction(event -> {
        	System.out.println(textField.getPromptText());
        	String userInput = textField.getText();
        	if (AnagramUtils.isCorrectWord(userInput, correctWord)) {
        		resultLabel.setText("Correct");
        		nextButton.setVisible(true);
        		
           	}
        	else {
        		resultLabel.setText("Incorrect");
        		nextButton.setVisible(false);
        	}
        });
        
        nextButton.setOnAction(event -> {
        	if (wordIndex < AnagramUtils.getWordCount()) {
        		wordIndex++;
        		correctWord = AnagramUtils.getRandomWord(wordIndex);
                jumbledWord = AnagramUtils.jumbleWord(correctWord);
                prompt.setText(AnagramUtils.jumbleWord(correctWord));
                textField.clear();
                resultLabel.setText("");
                nextButton.setVisible(false);
                
        	}
        	else {
        		resultLabel.setText("Congratulations, you've completed all the words!");
        		nextButton.setVisible(false);
        	}
        });
        
        VBox layout = new VBox(10, prompt, textField, submitButton, resultLabel, nextButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
