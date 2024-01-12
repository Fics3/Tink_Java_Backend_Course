package edu.hw9.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileFinderTest {

    private static final String TEST_DIRECTORY = "src/test/resources/hw9/";

    @AfterEach
    void tearDown() {
        deleteTempFiles();
    }

    @Test
    @DisplayName("findFile - No matching files")
    void findFile_NoMatchingFiles_ShouldReturnEmptyList() {
        // Arrange
        FileFinder fileFinder = new FileFinder(new File(TEST_DIRECTORY), file -> false);

        // Act
        List<File> result = fileFinder.findFile();

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("findFile - Matching files present")
    void findFile_MatchingFilesPresent_ShouldReturnMatchingFiles() {
        // Arrange
        createTestFiles(TEST_DIRECTORY, 3);
        Predicate<File> filePredicate = file -> file.getName().startsWith("testFile");

        // Act
        FileFinder fileFinder = new FileFinder(new File(TEST_DIRECTORY), filePredicate);
        List<File> result = fileFinder.findFile();

        // Assert
        assertThat(result).hasSize(3);
        assertThat(result).allSatisfy(file -> assertThat(file.getName()).startsWith("testFile"));
    }

    private void createTestFiles(String directory, int numberOfFiles) {
        try {
            Files.createDirectories(Path.of(directory));
            for (int i = 1; i <= numberOfFiles; i++) {
                Files.createTempFile(Path.of(directory), "testFile" + i, ".txt");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTempFiles() {
        File testDirectory = new File(TEST_DIRECTORY);
        if (testDirectory.exists()) {
            try (var pathStream = Files.walk(testDirectory.toPath())) {
                pathStream.sorted((path1, path2) -> -path1.compareTo(path2))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
