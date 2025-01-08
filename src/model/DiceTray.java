package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Abdulrahman Al Rajhi
 * @FILE: DiceTray.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE : 09/07/2023
 * 
 * @PURPOSE: This class represents the game of Boggle. A player can enter a
 *           string (Word) and by a recursive algorithm if the word exists, it
 *           will return true. This class has the found() method, which checks
 *           if the word was found and dfsSearchWord() takes in the word and
 *           iterates through each dice and checks if current dice matches the
 *           next char of the word
 *
 *           USAGE: 2D char array
 * 
 *           Model the tray of dice i n the game Boggle. A DiceTray can be
 *           constructed with a 4x4 array of characters for testing.
 */
public class DiceTray {
	private char[][] theBoard;

	/**
	 * Construct a DiceTray object using a hard-coded 2D array of chars. One is
	 * provided in the given unit test. You can create others.
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	/**
	 * initializes a 2D character array boggleDiceArr with a set of characters
	 * representing dice faces.
	 */
	public DiceTray() {
		char[][] boggleDiceArr = { { 'L', 'R', 'Y', 'T', 'T', 'E' }, { 'V', 'T', 'H', 'R', 'W', 'E' },
				{ 'E', 'G', 'H', 'W', 'N', 'E' }, { 'S', 'E', 'O', 'T', 'I', 'S' }, { 'A', 'N', 'A', 'E', 'E', 'G' },
				{ 'I', 'D', 'S', 'Y', 'T', 'T' }, { 'O', 'A', 'T', 'T', 'O', 'W' }, { 'M', 'T', 'O', 'I', 'C', 'U' },
				{ 'A', 'F', 'P', 'K', 'F', 'S' }, { 'X', 'L', 'D', 'E', 'R', 'I' }, { 'H', 'C', 'P', 'O', 'A', 'S' },
				{ 'E', 'N', 'S', 'I', 'E', 'U' }, { 'Y', 'L', 'D', 'E', 'V', 'R' }, { 'Z', 'N', 'R', 'N', 'H', 'L' },
				{ 'N', 'M', 'I', 'H', 'U', 'Q' }, { 'O', 'B', 'B', 'A', 'O', 'J' } };

		theBoard = new char[4][4];
		shuffleDice(boggleDiceArr);
	}

	/**
	 * This method It creates a Random object to generate random numbers and It
	 * iterates over each die in boggleDiceArr using a for loop. Inside the loop, it
	 * generates random row and column indices within the range of the board size
	 * (4x4).It checks if the randomly chosen position in the theBoard array is
	 * unoccupied by comparing it to the null character ('\u0000'). If the position
	 * is occupied, it generates new random row and column indices until an
	 * unoccupied position is found. Once an unoccupied position is found, it
	 * assigns a random face of the dice to the chosen position in the theBoard
	 * array. The loop continues until all dice have been placed in the tray.
	 * 
	 * @param boggleDiceArr represents the 16 dice with their faces
	 */
	private void shuffleDice(char[][] boggleDiceArr) {
		Random random = new Random();
		int diceCount = boggleDiceArr.length;
		int diceSize = boggleDiceArr[0].length;

		for (int i = 0; i < diceCount; i++) {
			int randomRow = random.nextInt(4);
			int randomCol = random.nextInt(4);

			// Find an unoccupied position in the board
			// comparing it to the null character
			while (theBoard[randomRow][randomCol] != '\u0000') {
				randomRow = random.nextInt(4);
				randomCol = random.nextInt(4);
			}
			// Assign a random face of the dice to the chosen position in the board
			theBoard[randomRow][randomCol] = boggleDiceArr[i][random.nextInt(diceSize)];
		}
	}

	/**
	 * This method return the board
	 * 
	 * @return
	 */
	public char[][] getBoard() {
		return this.theBoard;
	}

	/**
	 * Return true if attempt can be found on the DiceTray following the rules of
	 * Boggle, like a die can only be used once.
	 *
	 * @param attempt A word that may be in the DiceTray by connecting consecutive
	 *                letters
	 *
	 * @return True if search is found in the DiceTray or false if not. You need not
	 *         check the dictionary now.
	 */

	public boolean found(String attempt) {
		if (attempt.length() < 3) {
			return false;
		}
		// Convert the attempt to upper case for case-insensitive comparison
		attempt = attempt.toUpperCase().replace("QU", "Q");
		// Iterate over each die in the tray
		for (int i = 0; i < theBoard.length; i++) {
			for (int j = 0; j < theBoard[i].length; j++) {
				// Check if the current die matches the first letter of the attempt
				if (theBoard[i][j] == attempt.charAt(0) && dfsSearchWord(attempt, i, j, new HashSet<>())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This is a helper method for the search method to search for the word in the
	 * dice tray recursively. The method uses the Recursive Backtracking approach,
	 * which has three famous steps. 1. Choose, 2. Explore and 3. Unchoose. The
	 * algorithm works as follows: First, the base case is when the word is empty,
	 * which means that the word was found. Second, it returns false if the current
	 * position is out of bounds or has been visited. Third, if the current die
	 * (letter) matches the following letter of the word, we choose or mark this
	 * position as visited. Fourth, we explore that position's directions: up, down,
	 * right, left, and diagonally. If any recursive calls return true, it proceeds
	 * to check the other diagonals of that position (Letter); however, if not, it
	 * unchooses that position (letter) by removing it from the visited set.
	 *
	 * @param attempt       A word that may be in the DiceTray by connecting
	 *                      consecutive letters
	 * @param row           Current row
	 * @param col           Current column
	 * @param visitedCoords A set of all visited positions
	 *
	 * @return True if search is found in the DiceTray or false if not.
	 */
	private boolean dfsSearchWord(String attempt, int row, int col, Set<String> visitedCoords) {
		// the Base Case if the word has been found
		if (attempt.isEmpty()) {
			return true;
		}
		// checking if the current position (letter) is out of bounds or has already
		// been visited
		if (row >= theBoard.length || row < 0 || col >= theBoard[row].length || col < 0
				|| visitedCoords.contains(row + " " + col)) {
			return false;
		}
		// checking if the current position (die) matches the next letter of the word
		if (theBoard[row][col] == attempt.charAt(0)) {
			// doing 1st step of recursive backtracking choosing the current position as
			// visited
			visitedCoords.add(row + " " + col);
			// doing 2nd step: Recursively searching and exploring all adjacent positions
			boolean found = dfsSearchWord(attempt.substring(1), row + 1, col, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row - 1, col, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row, col + 1, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row, col - 1, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row + 1, col + 1, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row + 1, col - 1, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row - 1, col - 1, visitedCoords)
					|| dfsSearchWord(attempt.substring(1), row - 1, col + 1, visitedCoords);
			// Unchoose the current position
			visitedCoords.remove(row + " " + col);
			return found;
		}
		return false;
	}
}