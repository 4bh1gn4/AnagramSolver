package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

 

public class Main extends Application {
	private String correctWord;
	private String jumbledWord;
	private int wordIndex;
	private int level;
	private int wrong;
	
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
		Button levelOne = new Button("Level 1 (3 letters)");
		Button levelTwo = new Button("Level 2 (4 letters)");
		Button levelThree = new Button("Level 3 (5 letters)");
		Button levelFour = new Button("Level 4 (6 letters)");
		
		HBox levelLayout = new HBox(10, levelOne, levelTwo, levelThree, levelFour);
		levelLayout.setAlignment(Pos.CENTER);
		levelLayout.setStyle("-fx-background-color: pink;");
		
		levelOne.setOnAction(event -> {
			//System.out.println("Level 1 button clicked");
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
		
		levelFour.setOnAction(event -> {
			level = 4;
			primaryStage.setScene(gameScene(primaryStage));
		});
		
		return new Scene(levelLayout, 600, 500);
	}
	
	public Scene gameScene(Stage primaryStage) {
        //primaryStage.setTitle("Anagram Solver");
		wrong = 0;
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
        
        Button hintButton = new Button("Hint");
        Label hint = new Label();
        hint.setPrefWidth(50);
        //hint.setPadding(new Insets(0, 5, 0, 0)); 
        
        HBox hintLayout = new HBox(10, hintButton, hint);
        
        Button submitButton = new Button("Submit");
        
        HBox input = new HBox(10, hintLayout, textField, submitButton);
        input.setAlignment(javafx.geometry.Pos.CENTER);
        
        Button nextButton = new Button("Next");
        Button backButton = new Button("Back to levels");
        
        backButton.setAlignment(javafx.geometry.Pos.BASELINE_RIGHT);
        //backButton.setVisible(true);
        nextButton.setVisible(false);
        
        
        hintButton.setOnAction(event -> {
        	hint.setText(AnagramUtils.getWord(wordIndex, level).substring(0, 1));
        	
        });
        
        submitButton.setOnAction(event -> {
        	//System.out.println(textField.getPromptText());
        	String userInput = textField.getText();
        	//System.out.println(userInput);
        	if (userInput.equals("")) {
        		resultLabel.setText("Please enter a valid answer.");
        	}
        	
        	else if (AnagramUtils.isCorrectWord(userInput, correctWord)) {
        		switch(wrong) {
                case 0: 
                    resultLabel.setText("Correct");
                    break;
                case 1: 
                    resultLabel.setText("Correct");
                    break;
                case 2: 
                    resultLabel.setText("There we go!");
                    break;
                case 3: 
                    resultLabel.setText("There we go!");
                    break;
                default: 
                    resultLabel.setText("Never back down never what 💀");
                    break;
        		}
        		 //System.out.println(resultLabel);
        		 nextButton.setVisible(true);
           	}
        	
        	else {
        		resultLabel.setText(incorrectResponse(wrong));
        		wrong++;
        		nextButton.setVisible(false);
        	}
        });
        
        nextButton.setOnAction(event -> {
        	if (wordIndex < AnagramUtils.getWordCount(level)) {
        		wrong = 0;
        		wordIndex++;
        		correctWord = AnagramUtils.getWord(wordIndex, level);
                jumbledWord = AnagramUtils.jumbleWord(correctWord);
                prompt.setText(AnagramUtils.jumbleWord(correctWord));
                textField.clear();
                resultLabel.setText("");
                hint.setText("");
                nextButton.setVisible(false);
                
        	}
        	else {
        		resultLabel.setText("Congratulations, you've completed all the words!");
        		nextButton.setVisible(false);
        	}
        });
        
        backButton.setOnAction(event -> primaryStage.setScene(levelScene(primaryStage)));
        
        VBox gameLayout = new VBox(10, prompt, input, resultLabel, nextButton);
        gameLayout.setAlignment(Pos.CENTER);
        
        HBox leftBottomLayout = new HBox(hintLayout);
        leftBottomLayout.setAlignment(Pos.BOTTOM_LEFT);
        leftBottomLayout.setPadding(new Insets(20));

        // Align the back button to the bottom-right
        HBox rightBottomLayout = new HBox(backButton);
        rightBottomLayout.setAlignment(Pos.BOTTOM_RIGHT);
        rightBottomLayout.setPadding(new Insets(20));

        BorderPane rootLayout = new BorderPane();
        rootLayout.setCenter(gameLayout);
        BorderPane bottomLayout = new BorderPane();
        bottomLayout.setLeft(leftBottomLayout);
        bottomLayout.setRight(rightBottomLayout);

        rootLayout.setBottom(bottomLayout);

        return new Scene(rootLayout, 600, 500);
    }
    
	public String incorrectResponse(int wrongNumber) {
		//wrongNumber++;
		String[] array = {"Incorrect", "Nope", "Wrong again", "No, sorry", "Try again",
				"...Maybe try again later?", "Taking a break is always a good idea!"};
		
		if (wrongNumber > (array.length - 1)) {
			return "I've given up on you";
		}
		
		return array[wrongNumber];
	}
	
    public static void main(String[] args) {
        launch(args);
    }
}