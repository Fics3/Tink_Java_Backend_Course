package edu.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task4.chainPrintInFile;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    private final Path testFilePath = Path.of("src/test/resources/Task4/test.txt");

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    @Test
    @DisplayName("Test for not empty file")
    void chainPrintInFile_fileShouldNotBeEmpty() throws IOException {
        // Arrange
        String filePath = testFilePath.toString();

        // Act
        chainPrintInFile(filePath);

        // Assert
        try (BufferedReader reader = Files.newBufferedReader(testFilePath)) {
            assertThat(reader.lines().collect(Collectors.joining())).isNotEmpty();

        }
    }

    @Test
    @DisplayName("Test for correct information in file")
    void chainPrintInFile_fileShouldContainCorrectData() throws IOException {
        // Arrange
        String filePath = testFilePath.toString();
        String expectedData = "Programming is learned by writing programs. ― Brian Kernighan";

        // Act
        chainPrintInFile(filePath);

        // Assert
        try (BufferedReader reader = Files.newBufferedReader(testFilePath)) {
            String content = reader.lines().collect(Collectors.joining());
            assertThat(content).isEqualTo(expectedData);
        }
    }
}
