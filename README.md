# **Boggle IO Game**

A console- and GUI-based implementation of the classic **Boggle** word game, where players find words in a grid of letters. This project is designed to allow players to test their vocabulary and logic skills while demonstrating strong programming principles, such as recursion, object-oriented design, and JavaFX GUI development.

---

## **Overview**

The Boggle game includes:
1. **Console Version**: A text-based interface for playing the game through standard input and output.
2. **Graphical User Interface (GUI)**: A modern interface for playing the game with an interactive grid.
3. **Game Logic**: Validates words, calculates scores, and provides detailed feedback to the player.

---

## **Features**

### **Gameplay**
- Enter words found in a 4x4 grid of letters.
- Validate words based on:
  - Presence in a dictionary.
  - Following Boggle rules (letters must be adjacent, and dice cannot be reused).
- Scoring:
  - Words of length 3 or 4: 1 point.
  - Words of length 5: 2 points.
  - Words of length 6: 3 points.
  - Words of length 7: 5 points.
  - Words of length 8 or more: 11 points.
- Identify correct, incorrect, and missed words.

### **Console Version**
- Type words directly into the terminal.
- Enter `ZZ` to end the game and display results.

### **GUI Version**
- Interactive grid display using JavaFX.
- Text areas for entering words and viewing results.
- Buttons for starting a new game and ending the current session.

### **Additional Features**
- Recursive backtracking for word validation.
- Dynamic dice tray generation for a new game experience each time.
- Detailed feedback on words found, missed, and incorrect.

---

## **Technologies Used**
- **Java**: Core programming language for implementing game logic.
- **JavaFX**: Framework for building the graphical user interface.
- **JUnit**: For testing game functionality and edge cases.

---

## **How to Run**

### **Prerequisites**
1. **JDK** 11 or later.
2. **JavaFX SDK** (not bundled with JDK after version 11).

### **Steps**
1. Clone the repository or download the project files.
2. Compile and run the appropriate class:
   - **Console Version**:
     ```bash
     java view.BoggleConsole
     ```
   - **GUI Version**:
     ```bash
     java --module-path <path-to-javafx-sdk> --add-modules javafx.controls,javafx.fxml view.BoggleGUI
     ```

3. Replace `<path-to-javafx-sdk>` with the path to your JavaFX `lib` folder.