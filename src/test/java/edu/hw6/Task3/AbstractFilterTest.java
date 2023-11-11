package edu.hw6.Task3;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {
    String testFilePath = "src/test/resources/Task3/testPng.png";

    @Test
    @DisplayName("Test is regular file") void REGULAR_FILE_shouldReturnTrueIfFileRegular() {
        //Arrange
        Path path = Paths.get(testFilePath);

        //Act
        AbstractFilter filter = AbstractFilter.REGULAR_FILE;

        //Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test is readable file") void READABLE_shouldReturnTrueIfFileReadable() {
        //Arrange
        Path path = Paths.get(testFilePath);

        //Act
        AbstractFilter filter = AbstractFilter.READABLE;

        //Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test for unreadableFile")
    void READABLE_shouldReturnFalseIfFileUnreadable() {
        //Arrange
        Path path = Paths.get("src/test/resources/Task3/unreadablePng.png");

        //Act
        AbstractFilter filter = AbstractFilter.READABLE;

        //Assert
        assertThat(filter.accept(path)).isFalse();
    }

    @Test
    @DisplayName("Test if file larger than input")
    void largerThan_shouldReturnTrueIfFileLargerThanInput() {
        //Arrange
        Path path = Paths.get(testFilePath);

        //Act
        AbstractFilter filter = AbstractFilter.largerThan(1000);

        //Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test if file lesser than input")
    void largerThan_shouldReturnFalseIfFileNotLargerThanInput() {
        //Arrange
        Path path = Paths.get(testFilePath);

        //Act
        AbstractFilter filter = AbstractFilter.largerThan(1000000);

        //Assert
        assertThat(filter.accept(path)).isFalse();
    }

    @Test
    @DisplayName("Test for globMatches matching")
    void globMatches_shouldReturnTrueIfFileMatchesGlobPattern() {
        // Arrange
        Path path = Paths.get(testFilePath);

        // Act
        AbstractFilter filter = AbstractFilter.globMatches(".png");

        // Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test for globMatches not matching")
    void globMatches_shouldReturnFalseIfFileDoesNotMatchGlobPattern() {
        // Arrange
        Path path = Paths.get(testFilePath);

        // Act
        AbstractFilter filter = AbstractFilter.globMatches(".jpg"); // This should not match a PNG file

        // Assert
        assertThat(filter.accept(path)).isFalse();
    }

    @Test
    @DisplayName("Test for regexContains contains")
    void regexContains_shouldReturnTrueIfFileMatchesRegexPattern() {
        // Arrange
        Path path = Paths.get(testFilePath);

        // Act
        AbstractFilter filter = AbstractFilter.regexContains("t.*\\.png");

        // Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test for regexContains not contains")
    void regexContains_shouldReturnFalseIfFileDoesNotMatchRegexPattern() {
        // Arrange
        Path path = Paths.get(testFilePath);

        // Act
        AbstractFilter filter = AbstractFilter.regexContains("P.*\\.jpg"); // This should not match a PNG file

        // Assert
        assertThat(filter.accept(path)).isFalse();
    }

    @Test
    @DisplayName("Test for magicNumber are not in file")
    void magicNumber_shouldReturnTrueIfFileMatchesMagicNumber() {
        // Arrange
        Path path = Paths.get(testFilePath);

        // Act
        AbstractFilter filter = AbstractFilter.magicNumber(0x89, 'P', 'N', 'G');

        // Assert
        assertThat(filter.accept(path)).isTrue();
    }

    @Test
    @DisplayName("Test for magicNumbers are not in file")
    void magicNumber_shouldReturnFalseIfFileDoesNotMatchMagicNumber() {
        // Arrange
        Path path = Paths.get("src/test/resources/Task3/testJpg.jpg");

        // Act
        AbstractFilter filter = AbstractFilter.magicNumber(0x89, 'P', 'N', 'G');

        // Assert
        assertThat(filter.accept(path)).isFalse();
    }

}

