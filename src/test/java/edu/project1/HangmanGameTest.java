package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanGameTest {
    private HangmanGame game;

    @BeforeEach
    public void setUp() {
        game = new HangmanGame("test");
    }

    @Test
    @DisplayName("Test for surrender")
    void play_afterSurrenderPlayAgainShouldBeFalse() {
        //Arrange
        String input = "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        try {
            game.play();
        }

        //Assert
        catch (NoSuchElementException e) {
            assertThat(game.isPlayAgain()).isFalse();
        }
    }

    @Test
    @DisplayName("Test for Invalid Input (not a character)")
    public void play_shouldHandleInvalidInputAndNotReduceAttempts() {
        //Arrange
        int remainAttemptsAtStart = game.getAnswerManager().getRemainingAttempts();
        String input = "!\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        try {
            game.play();
        }

        //Assert
        catch (NoSuchElementException e) {
            assertThat(remainAttemptsAtStart).isEqualTo(game.getAnswerManager()
                .getMaxAttempts());
        }
    }

    @Test
    @DisplayName("Test for Invalid Input (several symbols)")
    public void play_shouldHandleInvalidInputWithSeveralCharsAndNotReduceAttempts() {
        //Arrange
        game = new HangmanGame("test");
        int remainAttemptsAtStart = game.getAnswerManager().getRemainingAttempts();
        String input = "aaa\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        try {
            game.play();
        }

        //Assert
        catch (NoSuchElementException e) {
            assertThat(remainAttemptsAtStart).isEqualTo(game.getAnswerManager()
                .getMaxAttempts());
        }
    }

    @Test
    @DisplayName("Test for winning game")
    public void play_gameShouldBeWinWhenWordGuessed() {
        //Arrange
        String input = "t\ne\ns\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        try {
            game.play();
        }

        //Assert
        catch (NoSuchElementException e) {
            assertThat(game.getAnswerManager().isWin()).isTrue();
        }
    }

    @Test
    @DisplayName("Test for losing game")
    public void play_gameShouldBeLostWhenAttemptsWasted() {
        //Arrange
        int maxAttempts = game.getAnswerManager().getMaxAttempts() + 1;
        String input = "";
        for (int i = 0; i < maxAttempts; i++) {
            input += "n\n";
        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Act
        try {
            game.play();
        }//Assert
        catch (NoSuchElementException e) {
            assertThat(game.getAnswerManager().isLost()).isTrue();
        }
    }

    @Test
    @DisplayName("After reset word should be not null")
    public void play_changedWordNotNull() {
        //Arrange
        int maxAttemptsAndRestart = game.getAnswerManager().getMaxAttempts() + 1;
        String input = "";
        for (int i = 0; i < maxAttemptsAndRestart; i++) {
            input += "n\n";

        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Act
        try {
            game.play();
        }
        //Assert
        catch (NoSuchElementException E) {
            assertThat(game.getAnswerManager().getTargetWord()).isNotNull();
        }
    }

    @Test
    @DisplayName("Test for resetting game")
    public void play_gameStateShouldNotChange() {
        //Arrange
        boolean gameStateAtTheStart = game.isPlayAgain();

        int maxAttemptsAndRestart = game.getAnswerManager().getMaxAttempts() + 1;
        String input = "";

        for (int i = 0; i < maxAttemptsAndRestart; i++) {
            input += "y\n";

        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Act
        try {
            game.play();
        }
        //Assert
        catch (
            NoSuchElementException E) {//Игру не закончить, зато если больше ничего не вывожу выбрасывается эксепшен, другого варинта проверки не нашел
            assertThat(game.isPlayAgain()).isEqualTo(gameStateAtTheStart);
        }
    }

    @Test
    @DisplayName("after reset word changing and attempts reset")
    public void play_guessedWordShouldChangedAndAttemptsReset() {
        //Arrange
        game = new HangmanGame("test");
        String startWord = game.getAnswerManager().getTargetWord();

        int maxAttemptsAndRestart = game.getAnswerManager().getMaxAttempts() + 1;
        String input = "";

        for (int i = 0; i < maxAttemptsAndRestart; i++) {
            input += "y\n";
        }
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Act
        try {
            game.play();
        }
        //Assert
        catch (
            NoSuchElementException E) {//Игру не закончить, зато если больше ничего не вывожу выбрасывается эксепшен, другого варинта проверки не нашел

            assertThat(game.getAnswerManager().getTargetWord()).isNotEqualTo(startWord);
            assertThat(game.getAnswerManager().getRemainingAttempts()).isEqualTo(game.getAnswerManager()
                .getMaxAttempts());
        }
    }

    @Test
    @DisplayName("test for not resetting game")
    public void play_playAgainShouldBeFalseAfterNotResetting() {
        //Arrange
        boolean gameStateAtTheStart = game.isPlayAgain();
        game = new HangmanGame("test");

        int maxAttemptsAndRestart = game.getAnswerManager().getMaxAttempts() + 1;
        String input = "";

        for (int i = 0; i < maxAttemptsAndRestart; i++) {
            input += "n\n";
        }

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        game.play();

        //Arrange
        assertThat(gameStateAtTheStart).isNotEqualTo(game.isPlayAgain());
    }

}
