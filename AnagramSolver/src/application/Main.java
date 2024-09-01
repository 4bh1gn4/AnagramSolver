package application;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Slider;

 

public class Main extends Application {
	private MediaPlayer mediaPlayer;
	private String correctWord;
	private String jumbledWord;
	private int wordIndex;
	private int level;
	private int wrong;
	private int hintNum;
	private Button settingsButton;
	private static final String BG_COLOR = "#FFC0CB";
	private String selectedBackgroundColorClass = "root";
	
	
@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Unscrabble");
		settingsButton = createSettingsButton();
		Scene welcomeScene = welcomeScene(primaryStage);
	    welcomeScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

		//Scene gameScene = gameScene(primaryStage);
		primaryStage.setScene(welcomeScene);
		primaryStage.show();
	}
	
	private Button createSettingsButton() {
		Image settingsIcon = new Image(getClass().getResourceAsStream("/application/settings.png"));
		ImageView settingsImageView = createImageView(settingsIcon, 30);
		
	    Button settingsButton = new Button();
	    settingsButton.setGraphic(settingsImageView);
	    pushSettingsButton(settingsButton);
	    
	    // Add any additional settings button configuration here
	    //settingsButton.setOnAction(event -> openPopup());
	    return settingsButton;
	}
	
	private void openPopup() {
		Stage settingsStage = new Stage();
		settingsStage.setTitle("Settings");
		//prevents interaction with other windows until pop up is closed
		//popupStage.initModality(Modality.APPLICATION_MODAL);
		//
		
		Label volumeLabel = new Label("Music Volume:");
		Slider volumeSlider = new Slider(0, 100, mediaPlayer.getVolume() * 100);
		volumeSlider.setShowTickLabels(true);
		volumeSlider.setShowTickMarks(true);
		volumeSlider.setMajorTickUnit(50);
		//volumeSlider.setBlockIncrement(5);
		
		volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			mediaPlayer.setVolume(newVal.doubleValue() / 100.0);
		});
		
		Label colorsLabel = new Label("Background colors:");
		Button skyBlue = createBackgroundColorButton("#87CEEB", "bg-skyBlue");
		Button paleGreen = createBackgroundColorButton("#98FB98", "bg-paleGreen");
		Button lavender = createBackgroundColorButton("#E6E6FA", "bg-lavender");
		Button lightPink = createBackgroundColorButton("#FFB6C1", "bg-lightPink");
		Button lightCoral = createBackgroundColorButton("#F08080", "bg-lightCoral");
		Button lightYellow = createBackgroundColorButton("#FAFAD2", "bg-lightYellow");
		Button lightSeaGreen = createBackgroundColorButton("#20B2AA", "bg-lightSeaGreen");
		Button mistyRose = createBackgroundColorButton("#FFE4E1", "bg-mistyRose");
		HBox colorsList = new HBox(10, skyBlue, paleGreen, lavender, lightPink, lightCoral, lightYellow, lightSeaGreen, mistyRose);
		
		
		VBox settingsLayout = new VBox(10, volumeLabel, volumeSlider, colorsLabel, colorsList);
		settingsLayout.setPadding(new Insets(20));
		Scene settingsScene = new Scene(settingsLayout, 400, 300);
		settingsStage.setScene(settingsScene);
		applyBackgroundColor(settingsStage.getScene());
		settingsStage.show();
		
	}
	
	public void changeBackgroundColor(String colorClass) {
	    selectedBackgroundColorClass = colorClass; // Store the selected color class
	    applyBackgroundColor(settingsButton.getScene()); // Apply to the current scene
	}
	
	public Button createBackgroundColorButton(String colorCode, String className) {
		Button colorButton = new Button();
		colorButton.setStyle("-fx-background-color: " + colorCode + ";");
		colorButton.setOnAction(event -> changeBackgroundColor(className));
		colorButton.setMinWidth(30);
		colorButton.setMinHeight(20);
		return colorButton;
	}
	
	public void applyBackgroundColor(Scene scene) {
		//Scene scene = settingsButton.getScene();
		scene.getRoot().getStyleClass().removeAll("bg-skyBlue", "bg-paleGreen", "bg-lavender", "bg-lightPink", "bg-lightCoral", "bg-lightYellow", "bg-lightSeaGreen", "bg-mistyRose");
		scene.getRoot().getStyleClass().add(selectedBackgroundColorClass);
	}
	
	private BorderPane createBaseLayout() {
	    BorderPane rootLayout = new BorderPane();
	    
	    // Create and configure top layout with settings button
	    BorderPane topLayout = new BorderPane();
	    topLayout.setRight(settingsButton);
	    BorderPane.setAlignment(settingsButton, Pos.TOP_RIGHT);
	    topLayout.setPadding(new Insets(10, 10, 0, 0));
	    rootLayout.getStyleClass().add("root");
	    rootLayout.setTop(topLayout);
	    return rootLayout;
	}

	public Scene welcomeScene(Stage primaryStage) {
		Media bgMusic = new Media(new File("src/application/background music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(bgMusic);
        mediaPlayer.setAutoPlay(true);
        
        mediaPlayer.setOnEndOfMedia(() -> {
        	mediaPlayer.seek(mediaPlayer.getStartTime());
        	mediaPlayer.play();
        });
        
        //Button settingsButton = new Button("Settings");
        //VBox settingsLayout = new VBox(settingsButton);
        //settingsLayout.setAlignment(Pos.TOP_RIGHT);
        
		Label welcomeLabel = new Label("Welcome to Unscrabble! Ready to play?");
		Button readyButton = new Button("Ready");
		//readyButton.setOnAction(event -> primaryStage.setScene(gameScene(primaryStage)));
		readyButton.setOnAction(event -> {
			Scene nextScene = levelScene(primaryStage);
		    applyBackgroundColor(nextScene); // Apply color to the new scene
		    primaryStage.setScene(nextScene);
		});
		
		VBox welcomeLayout = new VBox(10, welcomeLabel, readyButton);
		welcomeLayout.setAlignment(Pos.CENTER);
		//welcomeLayout.setStyle("-fx-background-color: " + BG_COLOR + ";");
		
		BorderPane rootLayout = createBaseLayout();
	    rootLayout.setCenter(welcomeLayout);
	    
	    Scene scene = new Scene(rootLayout, 600, 500);
	    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Add the CSS file
	    applyBackgroundColor(scene);
	    return scene;
	}
	public Scene levelScene(Stage primaryStage) {
		Button levelOne = new Button("Level 1 (3 letters)");
		Button levelTwo = new Button("Level 2 (4 letters)");
		Button levelThree = new Button("Level 3 (5 letters)");
		Button levelFour = new Button("Level 4 (6 letters)");
		
		HBox levelLayout = new HBox(10, levelOne, levelTwo, levelThree, levelFour);
		levelLayout.setAlignment(Pos.CENTER);
		//levelLayout.setStyle("-fx-background-color: " + BG_COLOR + ";");
		
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
		
		BorderPane rootLayout = createBaseLayout();
	    rootLayout.setCenter(levelLayout);
	    
	    Scene scene = new Scene(rootLayout, 600, 500);
	    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Add the CSS file
	    applyBackgroundColor(scene);
	    return scene;
	}
	
	public Scene gameScene(Stage primaryStage) {
		
		//primaryStage.setTitle("Anagram Solver");
		wrong = 0;
        wordIndex = 0;
        hintNum = 0;
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
        
        Button hintButton = new Button("Hint (3)");
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
        
        
        hintButton.setOnAction	(event -> {
        	//hintNum;
        	hintNum++;
        	if (hintNum == 1) {
        		hintButton.setText("Hint (2)");
        		hint.setText(AnagramUtils.getWord(wordIndex, level).substring(0, 1));
        	}
        	else if (hintNum == 2) {
        		hintButton.setText("Hint (1)");
        		hint.setText(AnagramUtils.getWord(wordIndex, level).substring(0, 2));
        	}
        	else {
        		hintButton.setText("Hint (0)");
        		hint.setText(AnagramUtils.getWord(wordIndex, level).substring(0, 3));
        	}
        	
        	
        	
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
                hintButton.setText("Hint (3)");
                nextButton.setVisible(false);
                hintNum = 0;
                
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

        // Add the settings button to the top of the root layout
        BorderPane topLayout = new BorderPane();
        topLayout.setRight(settingsButton);
        BorderPane.setAlignment(settingsButton, Pos.TOP_RIGHT);
        topLayout.setPadding(new Insets(10, 10, 0, 0));

        rootLayout.setTop(topLayout);

        Scene scene = new Scene(rootLayout, 600, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // Add the CSS file
        applyBackgroundColor(scene);
        return scene;
    }
    public void pushSettingsButton(Button button) {
    	button.setStyle("-fx-background-color: transparent; -fx-border-color:transparent;");
    	button.setOnMousePressed(event -> {
    		ImageView imageView = (ImageView) button.getGraphic();
    		imageView.setScaleX(0.9);
    		imageView.setScaleY(0.9);
    	});
    	
    	button.setOnMouseReleased(event -> {
    		ImageView imageView = (ImageView) button.getGraphic();
    		imageView.setScaleX(1.0);
    		imageView.setScaleY(1.0);
    		openPopup();
    	});
    }
	public ImageView createImageView(Image image, int length) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(length);
		imageView.setFitHeight(length);
		imageView.setPreserveRatio(true);
		
		return imageView;
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