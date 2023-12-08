package edu.hw9.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearcherTest {

    private static final String TEST_DIRECTORY = "src/test/resources/hw9";

    @AfterEach
    void tearDown() {
        deleteTempFiles();
    }

    @Test
    @DisplayName("findDirectories - No Directories more than 1000")
    void findDirectories_ResultShouldBeEmpty() {
        //Arrange
        DirectorySearcher directorySearcher = new DirectorySearcher(new File("src/test/resources/hw9/"));

        createTempFiles(999, TEST_DIRECTORY);

        //Act
        var result = directorySearcher.findDirectories();

        //Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("findDirectories - One directory more than 1000")
    void findDirectories_ResultShouldBeOne() {
        //Arrange
        DirectorySearcher directorySearcher = new DirectorySearcher(new File("src/test/resources/hw9/"));

        createTempFiles(1000, TEST_DIRECTORY);

        //Act
        var result = directorySearcher.findDirectories();

        //Assert
        assertThat(result.size()).isOne();
    }

    @Test
    @DisplayName("findDirectories - Mush directory more than 1000")
    void findDirectories_ResultBeNotZero() throws IOException {
        //Arrange
        Files.createDirectory(Path.of(TEST_DIRECTORY));
        DirectorySearcher directorySearcher = new DirectorySearcher(new File("src/test/resources/hw9/"));

        createTempFiles(1000, TEST_DIRECTORY + "/test1/");
        createTempFiles(1000, TEST_DIRECTORY + "/test2/");
        createTempFiles(100, TEST_DIRECTORY + "/test3/");
        createTempFiles(1000, TEST_DIRECTORY + "/test4/");

        //Act
        var result = directorySearcher.findDirectories();

        //Assert
        assertThat(result.size()).isEqualTo(3);
    }

    private void createTempFiles(int filesCount, String dir) {
        try {
            Files.createDirectory(Path.of(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < filesCount; i++) {
            try {
                Files.createTempFile(Path.of(dir), String.valueOf(i), ".txt");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
