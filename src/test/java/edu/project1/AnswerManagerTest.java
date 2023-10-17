package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerManagerTest {
    private static AnswerManager answerManager;

    @BeforeAll public static void setUp() {
        answerManager = new AnswerManager();
    }

    @Test @DisplayName("Test for check word that see player not null") public void getTargetWord_shouldBeNotNull() {
        // Act
        String targetWord = answerManager.getTargetWord();

        // Assert
        assertThat(targetWord).isNotEqualTo(null);
    }

    @Test @DisplayName("Test for check guessing word not null") public void getCurrentWord_shouldBeNotNull() {
        // Act
        String currentWord = answerManager.getCurrentWord();

        // Assert
        assertThat(currentWord).isNotEqualTo(null);
    }

    @Test @DisplayName("Test for making guess of word (target word should be same with current)")
    public void makeGuess_wordShouldBeTheSameAfterGuessing() {
        // Arrange
        answerManager = new AnswerManager("test");

        // Act
        answerManager.makeGuess('t');
        answerManager.makeGuess('e');
        answerManager.makeGuess('s');

        // Assert
        assertThat(answerManager.getTargetWord()).isEqualTo(answerManager.getCurrentWord());
        assertThat(answerManager.isGameOver()).isTrue();
    }

    @Test @DisplayName("Test for making guess of word (attempts should not reduce if answer correct)")
    public void makeGuess_attemptsNotReduceIfAnswerCorrect() {
        // Arrange
        answerManager = new AnswerManager("test");

        // Act
        answerManager.makeGuess('t');

        // Assert
        assertThat(answerManager.getRemainingAttempts()).isEqualTo(answerManager.getMaxAttempts());
    }

    @Test @DisplayName("Test for making guess of word (attempts should not reduce if answer incorrect)")
    public void makeGuess_attemptsNotReduceIfAnswerIncorrect() {
        // Arrange
        answerManager = new AnswerManager("test");

        // Act
        answerManager.makeGuess('x');

        // Assert
        assertThat(answerManager.getRemainingAttempts()).isNotEqualTo(answerManager.getMaxAttempts());
    }

    @Test @DisplayName("Test for wasting attempts") public void makeGuess_attemptsShouldWastes() {
        // Arrange
        answerManager = new AnswerManager("test");

        // Act
        for (int i = 0; i < 5; i++) {
            answerManager.makeGuess('x');
        }

        // Assert
        assertThat(answerManager.getRemainingAttempts()).isEqualTo(0);
    }

    @Test @DisplayName("Test for changing game state when attempts zero or below")
    public void makeGuess_isGameOver_shouldReturnTrueWhenAttemptsZeroOrBelow() {
        // Arrange
        answerManager = new AnswerManager("test");

        // Act
        for (int i = 0; i < 5; i++) {
            answerManager.makeGuess('x');
        }

        // Assert
        assertThat(answerManager.isGameOver()).isEqualTo(true);
    }

    @Test @DisplayName("At the start remaining attempts equals maxAttempts")
    public void getRemainingAttempts_shouldEqualsMaxAttemptsAtTheStart() {
        // Arrange
        int remainingAttempts = answerManager.getRemainingAttempts();
        // Act
        int maxAttempts = answerManager.getMaxAttempts();

        // Assert
        assertThat(remainingAttempts).isNotEqualTo(maxAttempts);
    }
}
