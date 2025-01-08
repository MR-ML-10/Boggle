/**
 * @author Abdulrahman Al Rajhi
 * @FILE: BoggleTest.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE		: 08/31/2023
 * @PURPOSE: The DiceTrayTest class is responsible for testing the 
 * functionality of the Boggle class, which represents the game of Boggle.
 * This class test different scenarios to check the functionality of the methods.
 *
 *           USAGE: Boggle Object
 * 
 *           Model the tray of dice in the game Boggle. A DiceTray can be
 *           constructed with a 4x4 array of characters for testing.
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Boggle;
import model.DiceTray;

public class BoggleTest {
	DiceTray tray = new DiceTray();

	@Test
	void checkScore() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("CAT");
		boggle.addToUserInputList("DOG");
		boggle.addToUserInputList("RAT");

		assertEquals(3, boggle.getScore());
		boggle.addToUserInputList("RAT");
		boggle.addToUserInputList("RAT");
		boggle.addToUserInputList("RAT");

		assertEquals(3, boggle.getScore());
	}

	@Test
	void addToUserInputList() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry");
		assertEquals("[apple, banana, cherry]", boggle.getUserAttempt().toString());
	}

	@Test
	void getScore() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry grape melon orange watermelon");
		assertEquals(26, boggle.getScore());
		boggle.addToUserInputList("IamNotCorrectWord");
		assertEquals(26, boggle.getScore());
		boggle.addToUserInputList("ant");
		assertEquals(27, boggle.getScore());
		boggle.addToUserInputList("ant Quite queen ten");
		assertEquals(32, boggle.getScore());
	}

	@Test
	void getListOfCorrectAttempts() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry grape melon orange watermelon");
		assertEquals(26, boggle.getScore());

		boggle.addToUserInputList("IMNOTHERE DON'TCOUNTME CSC335");
		assertEquals("[apple, banana, cherry, grape, melon, orange, watermelon]",
				boggle.getListOfCorrectAttempts().toString());

		boggle.addToUserInputList("WHAT_ABOUT_NOW?!?!?");
		assertEquals("[apple, banana, cherry, grape, melon, orange, watermelon]",
				boggle.getListOfCorrectAttempts().toString());

	}

	@Test
	void getListOfInorrectWords() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry grape melon orange watermelon");
		assertEquals(26, boggle.getScore());

		boggle.addToUserInputList("IMNOTHERE DON'TCOUNTME CSC335");
		assertEquals("[csc335, don'tcountme, imnothere]", boggle.getListOfIncorrectWords().toString());

		boggle.addToUserInputList("WHAT_ABOUT_NOW?!?!?");
		assertEquals("[csc335, don'tcountme, imnothere, what_about_now?!?!?]",
				boggle.getListOfIncorrectWords().toString());
	}

	@Test
	public void testAddToUserInputList() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry");
		Set<String> userAttempt = boggle.getUserAttempt();
		assertTrue(userAttempt.contains("apple"));
		assertTrue(userAttempt.contains("banana"));
		assertTrue(userAttempt.contains("cherry"));
	}

	@Test
	public void testGetScore() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry");
		int score = boggle.getScore();
		assertEquals(8, score);
	}

	@Test
	public void testGetListOfCorrectAttempts() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry");
		Set<String> correctAttempts = boggle.getListOfCorrectAttempts();
		assertTrue(correctAttempts.contains("apple"));
		assertTrue(correctAttempts.contains("banana"));
		assertTrue(correctAttempts.contains("cherry"));
	}

	@Test
	public void testGetListOfIncorrectWords() {
		Boggle boggle = new Boggle(tray);
		boggle.addToUserInputList("apple banana cherry");
		Set<String> incorrectWords = boggle.getListOfIncorrectWords();
		assertTrue(incorrectWords.isEmpty());
	}

	@Test
	public void testGetData() {
		List<String> listOfWords = Boggle.getDataFromDict();
		assertFalse(listOfWords.isEmpty());
	}

}
