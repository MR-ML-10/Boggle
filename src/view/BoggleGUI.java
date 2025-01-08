/**
 * @author Abdulrahman Al Rajhi
 * @FILE: BoggleConsole.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE		: 09/07/2023
 * @PURPOSE: 	The BoggleGUI class represents a game of Boggle, a word-finding game where players try 
 * 						to find words in a grid of letters. This class allows the user to play a game of Boggle using GUI,
 * 						where player can enter and guess words .It includes methods for initializing the game managing
 * 						user attempts, calculating scores, and checking the validity of words. The Boggle class also readD
 * 						a list of valid words from a file and stores them for reference during the game. The user's attempts
 * 						are compared against this list to determine their correctness and score.

 *
 *           USAGE:
 *							Create an instance of the Boggle class to start a new game.
 *				  			Use the addToUserInputList() method to add the user's attempts to find words.
 *				  			Call the getScore() method to calculate the user's score based on their correct attempts.
 *				  			Use the getListOfCorrectAttempts() and getListOfIncorrectWords() methods to get sets of the user's correct 
 *				  			and incorrect attempts, respectively.
 * 
 */
package view;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Boggle;
import model.DiceTray;

/**
 * BoggleGUI class represents the graphical user interface for the Boggle game.
 */
public class BoggleGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	// Class variables and instance variables...

	private static DiceTray diceTray;
	private static Boggle game;
	private TextArea diceTrayArea;
	private TextArea resultsArea;
	private Label resultLabel;
	private Button newGameButton = new Button("New Game");
	private Button quitButton = new Button("End Game");

	private TextArea userInputArea = new TextArea();
	private GridPane everything = new GridPane();

	/**
	 * Initializes the stage and sets up the model, layout, and handlers.
	 *
	 * @param stage the primary stage for this application
	 */
	public void start(Stage stage) {
		setUpModel();
		layoutWindow();
		setupHandlers();
		Scene scene = new Scene(everything, 1080, 333);
		stage.setTitle("Boggle Game");
		stage.setScene(scene);
		stage.show();
		startNewGame();
		displayGameBoard();
	}

	/**
	 * Starts a new game by creating a new Boggle object and a new DiceTray object.
	 */
	private void startNewGame() {
		diceTray = new DiceTray();
		game = new Boggle(diceTray);
	}

	/**
	 * Sets up the model for the game and adds a listener to the userInputArea.
	 */
	private void setUpModel() {
		userLayout();
	}

	/**
	 * Sets up the layout for the game window.
	 */
	private void layoutWindow() {
		// Set up the dice tray area
		diceTrayArea = new TextArea();

		Font font = Font.font("Courier New", FontWeight.BOLD, 24);
		diceTrayArea.setFont(font);

		diceTrayArea.setMaxHeight(260);
		diceTrayArea.setMaxWidth(260);
		diceTrayArea.setEditable(false);
		diceTrayArea.setMouseTransparent(true);
		everything.setVgap(12);
		everything.setHgap(12);
		everything.add(diceTrayArea, 1, 2);

		// Set up the button pane
		GridPane buttonPane = new GridPane();
		buttonPane.setHgap(7);
		buttonPane.add(newGameButton, 3, 3);
		buttonPane.add(quitButton, 5, 3);
		everything.add(buttonPane, 1, 1);

		// Set up the results layout
		resultsLayout();
	}

	/**
	 * Sets up the user input area layout.
	 */
	private void userLayout() {
		// Set up the user input area
		Font font = Font.font("ChalkBoard", FontWeight.BOLD, 18);
		userInputArea.setFont(font);
		userInputArea.setMaxHeight(260);
		userInputArea.setMaxWidth(260);
		userInputArea.setEditable(true);
		userInputArea.setMouseTransparent(false);
		everything.setVgap(12);
		everything.setHgap(12);
		everything.add(userInputArea, 3, 2);

		// Set up the user attempt label
		Label userAttemptLabel = new Label();
		userAttemptLabel.setText("Enter attempts below:");
		everything.add(userAttemptLabel, 3, 1);
	}

	/**
	 * Retrieves a set of missed words that the user did not find.
	 *
	 * @param boggle the Boggle object
	 * @return a set of missed words
	 */
	private static Set<String> getListOfMissedWords(Boggle boggle) {
		Set<String> userFoundWords = boggle.getListOfCorrectAttempts();
		Set<String> missedWords = new TreeSet<>();
		List<String> dictionaryList = Boggle.getDataFromDict();
		for (String dword : dictionaryList) {
			if (!userFoundWords.contains(dword) && diceTray.found(dword)) {
				missedWords.add(dword);
			}
		}
		return missedWords;
	}

	/**
	 * Prints the missed words to the provided StringBuilder.
	 *
	 * @param boggle             the Boggle object
	 * @param missedWordsBuilder the StringBuilder to append the missed words to
	 */
	private static void printMissedWords(Boggle boggle, StringBuilder missedWordsBuilder) {
		// Get the list of missed words
		Set<String> listOfMissedWords = getListOfMissedWords(boggle);
		int wordsCounter = 0;
		// Iterate through the missed words and append them to the StringBuilder
		for (String mword : listOfMissedWords) {
			missedWordsBuilder.append(mword).append(" ");
			wordsCounter++;
			if (wordsCounter % 15 == 0) {
				missedWordsBuilder.append("\n");
			}
		}
		missedWordsBuilder.append("\n");
	}

	/**
	 * Sets up the results area layout.
	 */
	private void resultsLayout() {
		// Set up the results area
		resultsArea = new TextArea();
		resultsArea.setMaxHeight(260);
		resultsArea.setMaxWidth(560);
		resultsArea.setEditable(true);
		resultsArea.setMouseTransparent(true);
		everything.setVgap(12);
		everything.setHgap(12);
		everything.add(resultsArea, 4, 2);

		// Set up the result label
		resultLabel = new Label();
		resultLabel.setText("Results:");
		everything.add(resultLabel, 4, 1);
	}

	/**
	 * Sets up the event handlers for the buttons.
	 */
	private void setupHandlers() {
		newGameButton.setOnAction(new NewGameButtonListener());
		quitButton.setOnAction(new QuitButtonListener());
	}

	/**
	 * Displays the initial game board in the diceTrayArea.
	 */
	private void displayGameBoard() {
		StringBuilder boardBuilder = new StringBuilder();
		boardBuilder.append("\n");

		for (int row = 0; row < diceTray.getBoard().length; row++) {
			boardBuilder.append("  ");
			for (int col = 0; col < diceTray.getBoard()[row].length; col++) {
				char letter = diceTray.getBoard()[row][col];
				if (letter == 'Q') {
					boardBuilder.append("Qu ");
				} else {
					boardBuilder.append(letter).append("  ");
				}
			}
			if (row < diceTray.getBoard().length - 1) {
				boardBuilder.append("\n"); // Add "\n" except for the last row
				boardBuilder.append("\n"); // Add "\n" except for the last row
			}
		}
		diceTrayArea.setText(boardBuilder.toString());
	}

	/**
	 * Handles the New Game button click event.
	 */
	private class NewGameButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			startNewGame();
			displayGameBoard();
			userInputArea.clear();
			resultsArea.clear();

			// Enable the input area
			userInputArea.setEditable(true);
			userInputArea.setMouseTransparent(false);
		}
	}

	/**
	 * Handles the Quit button click event.
	 */
	private class QuitButtonListener implements EventHandler<ActionEvent> {
		/**
		 * Handles the action event triggered by the user input. Disables the user input
		 * area, gets the game results, and displays them in the results area.
		 * 
		 * @param arg0 the action event
		 */
		@Override
		public void handle(ActionEvent arg0) {
			// Disable the input area
			disableUserInputArea();

			String userInput = userInputArea.getText().trim();
			// Split the new input text into words
			String[] words = userInput.split(" ");
			// Iterate through the words and add them to game.addToUserInputList(word)
			for (String word : words) {
				game.addToUserInputList(word);
			}

			displayResults();
		}

		/**
		 * Displays game's results into the result area
		 * 
		 */
		private void displayResults() {
			// Get the game results
			String correctWords = getCorrectWords();
			String incorrectWords = getIncorrectWords();
			String missedWords = getMissedWords();
			int score = game.getScore();

			// Display the results in the results area
			displayResults(score, correctWords, incorrectWords, missedWords);
		}

		/**
		 * Disables the user input area and makes it mouse-transparent.
		 */
		private void disableUserInputArea() {
			userInputArea.setEditable(false);
			userInputArea.setMouseTransparent(true);
		}

		/**
		 * Retrieves the correct words found by the user during the game and returns
		 * them as a string.
		 *
		 * @return a string containing the correct words separated by spaces
		 */
		private String getCorrectWords() {
			Set<String> correctWords = game.getListOfCorrectAttempts();
			return joinWords(correctWords);
		}

		/**
		 * Gets the incorrect words entered by the user during the game.
		 * 
		 * @return a string containing the incorrect words separated by spaces
		 */
		private String getIncorrectWords() {
			Set<String> incorrectWords = game.getListOfIncorrectWords();
			return joinWords(incorrectWords);
		}

		/**
		 * Gets the missed words that the user could have found during the game.
		 * 
		 * @return a string containing the missed words separated by spaces
		 */
		private String getMissedWords() {
			StringBuilder missedWordsBuilder = new StringBuilder();
			printMissedWords(game, missedWordsBuilder);
			return missedWordsBuilder.toString().trim();
		}

		/**
		 * Joins the words in a set into a single string separated by spaces.
		 * 
		 * @param words the set of words to join
		 * @return a string containing the words separated by spaces
		 */
		private String joinWords(Set<String> words) {
			StringBuilder wordsBuilder = new StringBuilder();
			for (String word : words) {
				wordsBuilder.append(word).append(" ");
			}
			return wordsBuilder.toString().trim();
		}

		/**
		 * Displays the game results in the results area.
		 * 
		 * @param score          the score obtained by the user
		 * @param correctWords   the correct words found by the user
		 * @param incorrectWords the incorrect words entered by the user
		 * @param missedWords    the missed words that the user could have found
		 */
		private void displayResults(int score, String correctWords, String incorrectWords, String missedWords) {
			resultsArea.setText("Your Score: " + score + "\n" + "\n" + "Words You found:\n" + correctWords + "\n" + "\n"
					+ "Incorrect Words:\n" + incorrectWords + "\n" + "\nYou could have found " + missedWords.length()
					+ " more words:" + "\n" + missedWords);
		}
	}
}
