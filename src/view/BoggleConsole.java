/**
 * @author Abdulrahman Al Rajhi
 * @FILE: BoggleConsole.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE		: 09/07/2023
 * @PURPOSE: The Boggle class represents a game of Boggle, a word-finding game where players try to find words in a grid of letters. 
 * This class allows the user to play a game of Boggle using standard input and output. It includes methods for initializing the game
 * , managing user attempts, calculating scores, and checking the validity of words. The Boggle class also reads a list of valid words
 *  from a file and stores them for reference during the game. The user's attempts are compared against this list to determine their
 *   correctness and score.

 *
 *           USAGE:
 *				 	Create an instance of the Boggle class to start a new game.
 *				  	Use the addToUserInputList() method to add the user's attempts to find words.
 *				  	Call the getScore() method to calculate the user's score based on their correct attempts.
 *				  	Use the getListOfCorrectAttempts() and getListOfIncorrectWords() methods to get sets of the user's correct 
 *				  	and incorrect attempts, respectively.
 * 
 */

package view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import model.Boggle;
import model.DiceTray;

public class BoggleConsole {
	private static char[][] longWords = { { 'Q', 'E', 'E', 'B' }, { 'I', 'T', 'T', 'N' }, { 'N', 'D', 'E', 'D' },
			{ 'A', 'S', 'T', 'N' } };
	static DiceTray tray = new DiceTray(longWords);
	// static DiceTray randomTray = new DiceTray();

	public static void main(String[] args) {
		Boggle boggle = new Boggle(tray);
		boardDisplay();
		getInput(boggle);
		gameConsole(boggle);
	}

	private static void getInput(Boggle boggle) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String attempt = scanner.next();
			if (!attempt.equalsIgnoreCase("zz")) {
				boggle.addToUserInputList(attempt);
			} else {
				break;
			}
		}
		scanner.close();
	}

	/**
	 * This method is responsible for show the IO to the user
	 * 
	 * @param boggle object
	 */
	private static void gameConsole(Boggle boggle) {
		System.out.println();
		System.out.println("Your score: " + boggle.getScore());
		System.out.println();
		System.out.println("Words you found:");
		System.out.println("================");
		int count = 0;
		for (String attempt : boggle.getListOfCorrectAttempts()) {
			System.out.print(attempt + " ");
			count++;
			if (count % 10 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
		printMissedWords(boggle);

	}

	/**
	 * This is a helper method for gameConsole() to gain a better visibility and
	 * readability
	 * 
	 * @param boggle object
	 */
	private static void printMissedWords(Boggle boggle) {
		System.out.println("Incorrect words:");
		System.out.println("================");
		for (String wrongWords : boggle.getListOfIncorrectWords()) {
			System.out.print(wrongWords + " ");
		}
		System.out.println();
		System.out.println();
		Set<String> listOfMissedWords = getListOfMissedWords(boggle);
		System.out.println("You could have found these " + listOfMissedWords.size() + " more words:");
		System.out.println("===============================================");
		int wordsCounter = 0;
		for (String mword : listOfMissedWords) {
			System.out.print(mword + " ");
			wordsCounter++;
			if (wordsCounter % 10 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}

	private static Set<String> getListOfMissedWords(Boggle boggle) {
		Set<String> userFoundWords = boggle.getListOfCorrectAttempts();
		Set<String> missedWords = new TreeSet<>();
		List<String> dictionaryList = Boggle.getDataFromDict();
		for (String dword : dictionaryList) {
			if (!userFoundWords.contains(dword) && tray.found(dword)) {
				missedWords.add(dword);
			}
		}
		return missedWords;
	}

	/*
	 * This method displays the board
	 */
	public static void boardDisplay() {
		System.out.println("Play one game of Boggle");

		for (int row = 0; row < tray.getBoard().length; row++) {
			System.out.println();
			for (int col = 0; col < tray.getBoard()[row].length; col++) {
				char letter = tray.getBoard()[row][col];
				if (letter == 'Q') {
					System.out.print("Qu");
				} else {
					System.out.print(letter);
				}
				// to remove the last whitespace
				// if it's not last letter, we add space
				if (col < tray.getBoard()[row].length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("Enter words or ZZ to quit:");
		System.out.println();
	}
}