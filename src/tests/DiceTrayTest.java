/**
 * @author Abdulrahman Al Rajhi
 * @FILE: DiceTrayTest.java
 * @ASSIGNMENT: Programming Assignment 3 - Boggle IO Game
 * @COURSE: CSC 335; Fall 2023
 * @DATE		: 08/31/2023
 * @PURPOSE: The DiceTrayTest class is responsible for testing the 
 * functionality of the DiceTray class, which represents the game of Boggle.
 * This class test different scenarios to check the functionality of the methods.
 *
 *           USAGE: DiceTray Object
 * 
 *           Model the tray of dice i n the game Boggle. A DiceTray can be
 *           constructed with a 4x4 array of characters for testing.
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.DiceTray;

class DiceTrayTest {
	@Test
	/**
	 * Test the found() method with valid words.
	 */
	public void testFound() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);

		// Test valid words
		assertTrue(tray.found("ABCFE"));
		assertTrue(tray.found("MNO"));
		assertTrue(tray.found("KONJ"));

		// Test invalid words
		assertFalse(tray.found("ABZ"));
		assertFalse(tray.found("MNOPQ"));
		assertFalse(tray.found("AAB"));
	}

	@Test
	/**
	 * Test the found() method with lowercase words.
	 */
	public void testFoundLowerCase() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);

		// Test valid words
		assertTrue(tray.found("abcdhg"));
		assertTrue(tray.found("mnopl"));
		assertTrue(tray.found("kon"));

		// Test invalid words
		assertFalse(tray.found("cghloQQ"));
		assertFalse(tray.found("nothere"));
		assertFalse(tray.found("123doesn't"));
	}

	@Test
	/**
	 * Test the found() method with invalid input.
	 */
	public void testFoundInvalidInput() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };
		DiceTray tray = new DiceTray(board);

		assertFalse(tray.found("IM_MORE_THAN_16_LENGTH")); // word length greater than 16
		assertFalse(tray.found("")); // empty string
		assertFalse(tray.found("23")); // word length less than 3
	}

	@Test
	/**
	 * Test the found() method with multiple occurrences of a word.
	 */
	public void testFoundWithMultipleOccurrences() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);
		assertTrue(tray.found("ABCD"));
		assertTrue(tray.found("DCBA"));
		assertTrue(tray.found("EFGH"));
		assertTrue(tray.found("HGFE"));
		assertTrue(tray.found("IJKL"));
		assertTrue(tray.found("LKJI"));
		assertTrue(tray.found("MNOP"));
		assertTrue(tray.found("PONM"));
	}

	@Test
	/**
	 * Test the found() method with diagonal words.
	 */
	public void testFoundWithDiagonalWords() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);

		// Test diagonal words
		assertTrue(tray.found("AFK"));
		assertTrue(tray.found("AFKP"));
		assertTrue(tray.found("BGH"));
		assertTrue(tray.found("DGJm"));
	}

	@Test
	/**
	 * Test the found() method with non-diagonal words.
	 */
	public void testFoundWithNonDiagonalWords() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);

		// Test non-diagonal words
		assertTrue(tray.found("ABE"));
		assertTrue(tray.found("BFCG"));
		assertTrue(tray.found("CDHL"));
		assertTrue(tray.found("GKLP"));
	}

	@Test
	/**
	 * Test the found() method with non-diagonal words.
	 */
	public void testFoundWithRandomWords() {
		char[][] board = { { 'A', 'B', 'C', 'D' }, { 'E', 'F', 'G', 'H' }, { 'I', 'J', 'K', 'L' },
				{ 'M', 'N', 'O', 'P' } };

		DiceTray tray = new DiceTray(board);

		// Test non-diagonal words
		assertTrue(tray.found("abcghlkop"));
		assertTrue(tray.found("mnjkgcdhl"));
		assertTrue(tray.found("ijklhgfeabcd"));
		assertTrue(tray.found("abcdhgfeijklponm"));
	}
}
