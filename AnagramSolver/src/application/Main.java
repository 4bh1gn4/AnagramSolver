package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

 

public class Main extends Application {
	private String correctWord;
	private String jumbledWord;
	private int wordIndex;
	private int level;
	
@Override
public void start(Stage primaryStage) {
	primaryStage.setTitle("Anagram Solver");
	
	Scene welcomeScene = welcomeScene(primaryStage);
	//Scene gameScene = gameScene(primaryStage);
	
	primaryStage.setScene(welcomeScene);
	primaryStage.show();
}

public Scene welcomeScene(Stage primaryStage) {
	Label welcomeLabel = new Label("Welcome to Anagram Solver! Ready to play?");
	Button readyButton = new Button("Ready");
	//readyButton.setOnAction(event -> primaryStage.setScene(gameScene(primaryStage)));
	readyButton.setOnAction(event -> primaryStage.setScene(levelScene(primaryStage)));
	
	VBox welcomeLayout = new VBox(10, welcomeLabel, readyButton);
	welcomeLayout.setAlignment(Pos.CENTER);
	welcomeLayout.setStyle("-fx-background-color: beige;");
	
	return new Scene(welcomeLayout, 600, 500);
	
}
public Scene levelScene(Stage primaryStage) {
	Button levelOne = new Button("Level 1");
	Button levelTwo = new Button("Level 2");
	Button levelThree = new Button("Level 3");
	
	HBox levelLayout = new HBox(10, levelOne, levelTwo, levelThree);
	levelLayout.setAlignment(Pos.CENTER);
	levelLayout.setStyle("-fx-background-color: pink;");
	
	levelOne.setOnAction(event -> {
		System.out.println("Level 1 button clicked");
		level = 1;
		primaryStage.setScene(gameScene(primaryStage));
	});
	
	levelTwo.setOnAction(event -> {
		level = 2;
		primaryStage.setScene(gameScene(primaryStage));
	});
	
	levelThree.setOnAction(event -> {
		level = 3;
		primaryStage.setScene(gameScene(primaryStage));
	});
	
	return new Scene(levelLayout, 600, 500);
}
public Scene gameScene(Stage primaryStage) {
        //primaryStage.setTitle("Anagram Solver");
        wordIndex = 0;
        //level = 0;
        correctWord = AnagramUtils.getWord(wordIndex, level);
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
        
        textField.setPromptText("Type the word here");
        textField.setStyle("-fx-prompt-text-fill: gray;");

        Button submitButton = new Button("Submit");
        
        HBox input = new HBox(10, textField, submitButton);
        input.setAlignment(javafx.geometry.Pos.CENTER);
        
        Button nextButton = new Button("Next");
        nextButton.setVisible(false);
        
        submitButton.setOnAction(event -> {
        	//System.out.println(textField.getPromptText());
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
        	if (wordIndex < AnagramUtils.getWordCount(level)) {
        		wordIndex++;
        		correctWord = AnagramUtils.getWord(wordIndex, level);
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
        
        VBox layout = new VBox(10, prompt, input, resultLabel, nextButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        return new Scene(layout, 600, 400);
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
