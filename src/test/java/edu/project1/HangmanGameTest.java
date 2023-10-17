package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanGameTest {
    private HangmanGame game;

    @BeforeEach public void setUp() {
        game = new HangmanGame();
    }

    @Test @DisplayName("Test for surrender") public void checkChar_playAgainShouldBeFalse() {
        //Arrange
        String emptyChar = "";

        //Act
        game.checkChar(emptyChar);

        //Assert
        assertThat(game.isPlayAgain()).isFalse();
    }

    @Test @DisplayName("Test for Invalid Input (not a character)")
    public void checkChar_shouldHandleInvalidInputAndNotReduceAttempts() {
        //Arrange
        String invalidInput = "!";

        //Act
        game.checkChar(invalidInput);

        //Assert
        assertThat(game.getAnswerManager().getRemainingAttempts()).isEqualTo(game.getAnswerManager().getMaxAttempts());
    }

    @Test @DisplayName("Test for Invalid Input (several symbols)")
    public void checkChar_shouldHandleInvalidInputWithSeveralCharsAndNotReduceAttempts() {
        //Arrange
        String invalidInput = "aaa";

        //Act
        game.checkChar(invalidInput);

        //Assert
        assertThat(game.getAnswerManager().getRemainingAttempts()).isEqualTo(game.getAnswerManager().getMaxAttempts());
    }

    @Test @DisplayName("Test for winning game")
    public void checkChar_gameShouldBeWinWhenWordGuessed() {//есть ли вообще смысл тестировать такое 2 раза, логика в обоих программах по сути одинаковая
        //Arrange
        game = new HangmanGame("test");

        //Act
        game.checkChar("t");
        game.checkChar("e");
        game.checkChar("s");

        //Assert
        assertThat(game.getAnswerManager().isWin()).isTrue();
    }

    @Test @DisplayName("Test for losing game") public void checkChar_gameShouldBeLostWhenAttemptsWasted() {
        //Arrange
        game = new HangmanGame("test");

        //Act
        for (int i = 0; i < 5; i++) {
            game.checkChar("x");
        }

        //Assert
        assertThat(game.getAnswerManager().isLost()).isTrue();
    }

    @Test @DisplayName("After reset word should be not null") public void resetGame_changedWordNotNull() {
        //Arrange
        String startWord = "test";
        game = new HangmanGame(startWord);

        //Act
        game.restartGame('y');

        //Assert
        assertThat(game.getAnswerManager().getTargetWord()).isNotEqualTo(null);
    }

    @Test @DisplayName("after reset word changing and attempts reset")
    public void resetGame_guessedWordShouldChangedAndAttemptsReset() {
        //Arrange
        String startWord = "test";
        game = new HangmanGame(startWord);

        //Act
        game.restartGame('y');

        //Assert
        assertThat(game.getAnswerManager().getTargetWord()).isNotEqualTo(startWord);
        assertThat(game.getAnswerManager().getRemainingAttempts()).isEqualTo(game.getAnswerManager().getMaxAttempts());
    }

    @Test @DisplayName("test for not resetting game") public void resetGame_playAgainShouldBeFalse() {
        //Arrange
        boolean gameStateAtTheStart = game.isPlayAgain();

        //Act
        game.restartGame('n');

        //Arrange
        assertThat(gameStateAtTheStart).isNotEqualTo(game.isPlayAgain());
    }
}
