package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HangmanGame {
    private AnswerManager answerManager;
    private static final Logger LOGGER = LogManager.getLogger();

    private boolean playAgain = true;

    public HangmanGame(String word) {
        answerManager = new AnswerManager(word);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Welcome to Hangman!");
        while (isPlayAgain()) {
            while (!answerManager.isGameOver()) {
                LOGGER.info("Current word: " + answerManager.getCurrentWord());
                LOGGER.info("Remaining attempts: " + answerManager.getRemainingAttempts());
                LOGGER.info("Enter a letter (enter an empty string to surrender): ");
                String input = scanner.nextLine();
                checkChar(input);
                if (!isPlayAgain()) {
                    return;
                }
            }

            if (answerManager.isLost()) {
                LOGGER.info("You ran out of attempts. The word was: " + answerManager.getTargetWord());
            } else {
                LOGGER.info("Congratulations! You guessed the word: " + answerManager.getTargetWord());
            }

            LOGGER.info("Play again? (y/n): ");
            char playAgainChoice = Character.toLowerCase(scanner.next().charAt(0));
            restartGame(playAgainChoice);
            scanner.nextLine();
        }
        LOGGER.info("Thank you for playing Hangman!");
    }

    private void checkChar(String input) {
        if (input.isEmpty()) {
            LOGGER.info("You are surrendered! The word was: " + answerManager.getTargetWord());
            playAgain = false;
            return;
        }
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            LOGGER.info("Please enter a valid letter.");
        } else {
            char guess = Character.toLowerCase(input.charAt(0));
            if (answerManager.makeGuess(guess)) {
                LOGGER.info("Good guess!");
            } else {
                LOGGER.info("Incorrect guess!");
            }
        }
    }

    private void restartGame(char input) {
        playAgain = (input == 'y');
        if (isPlayAgain()) {
            answerManager = new AnswerManager();
        }
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public AnswerManager getAnswerManager() {
        return answerManager;
    }

//    public static void main(String[] args) {
//        HangmanGame game = new HangmanGame();
//        game.play();
//    }
}
