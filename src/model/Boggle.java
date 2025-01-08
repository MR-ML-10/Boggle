/**
 * @author Abdulrahman Al Rajhi
 * @FILE: Boggle.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE		: 09/07/2023
 * @PURPOSE: The Boggle class represents a game of Boggle, a word-finding game where players try to find words
 *      in a grid of letters.This class allows the user to play a game of Boggle using standard input and output.
 *      It includes methods for initializing the game, managing user attempts, calculating scores, and checking
 *      the validity of words. The Boggle class also reads a list of valid words from a file and stores them for
 *      reference during the game. The user's attempts are compared against this list to determine their
 *      correctness and score.
 *
 *           USAGE:
 *      Create an instance of the Boggle class to start a new game.
 *      Use the addToUserInputList() method to add the user's attempts to find words.
 *      Call the getScore() method to calculate the user's score based on their correct attempts.
 *      Use the getListOfCorrectAttempts() and getListOfIncorrectWords() methods to get sets of the user's
 *      correct and incorrect attempts, respectively.
 *
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * In Boggle Two you are asked to write a console game that
 *  can be played using standard input and output. To avoid using the same DiceTray every time (boring), 
 *  you must add a 2nd default constructor (no arguments) to the DiceTray class to "roll" 16 dice. 
 */

/**
 * The Boggle class represents a game of Boggle, which involves finding words in
 * a grid of letters.
 */
public class Boggle {
	/**
	 * A list of all valid words that can be used in the game.
	 */
	private final List<String> allStringsOfText = new ArrayList<>();

	/**
	 * A set of words that the user has attempted to find.
	 */
	private final Set<String> userAttempt = new TreeSet<>();

	private DiceTray diceTray;

	private TreeSet<String> correctUserWords;

	private TreeSet<String> incorrectUserWords;

	/**
	 * Constructs a new Boggle game and initializes the list of valid words.
	 */
	public Boggle(DiceTray diceTray) {
		this.diceTray = diceTray;
		allStringsOfText.addAll(getDataFromDict());
	}

	public List<String> getAllStringsOfText() {
		return allStringsOfText;
	}

	public Set<String> getUserAttempt() {
		return userAttempt;
	}

	/**
	 * Adds a user's attempt to find words to the list of attempted words.
	 *
	 * @param attempt the user's attempt to find words, separated by spaces
	 */
	public void addToUserInputList(String attempt) {
		String[] listOfWorld = attempt.split(" ");
		for (String word : listOfWorld) {
			userAttempt.add(word);
		}
	}

	/**
	 * Reads a list of valid words from a file and returns them as a list.
	 *
	 * @return a list of valid words
	 */
	public static List<String> getDataFromDict() {
		List<String> listOfWords = new ArrayList<>();
		try {
			String fileName = "BoggleWords.txt";
			BufferedReader textReader = new BufferedReader(new FileReader(fileName));
			String line = textReader.readLine();

			while (line != null) {
				listOfWords.add(line);
				line = textReader.readLine();
			}
			textReader.close();
		} catch (Exception e) {
			System.err.println("there was a problem reading the file :/");
			e.printStackTrace();
		}
		return listOfWords;
	}

	/**
	 * Calculates and returns the score for the user's correct attempts.
	 *
	 * @return the score for the user's correct attempts
	 */
	public int getScore() {
		int score = 0;
		for (String correctWord : getListOfCorrectAttempts()) {
			if (correctWord.length() == 3 || correctWord.length() == 4) {
				score++;
			} else if (correctWord.length() == 5) {
				score += 2;
			} else if (correctWord.length() == 6) {
				score += 3;
			} else if (correctWord.length() == 7) {
				score += 5;
			} else if (correctWord.length() >= 8) {
				score += 11;
			}
		}
		return score;
	}

	/**
	 * Returns a set of the user's correct attempts.
	 * 
	 * @param diceTray
	 *
	 * @return a set of the user's correct attempts
	 */
	public Set<String> getListOfCorrectAttempts() {
		correctUserWords = new TreeSet<>();
		for (String userWord : userAttempt) {
			userWord = userWord.toLowerCase();
			if (allStringsOfText.contains(userWord) && diceTray.found(userWord)) {
				correctUserWords.add(userWord); // valid word
			}
		}
		return correctUserWords;
	}

	/**
	 * Returns a set of the user's incorrect attempts.
	 *
	 * @return a set of the user's incorrect attempts
	 */
	public Set<String> getListOfIncorrectWords() {
		incorrectUserWords = new TreeSet<>();
		for (String userWord : userAttempt) {
			userWord = userWord.toLowerCase();
			if (!(allStringsOfText.contains(userWord) && diceTray.found(userWord))) {
				incorrectUserWords.add(userWord);
			}
		}
		return incorrectUserWords;
	}
}
