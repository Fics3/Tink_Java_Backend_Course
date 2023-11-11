package edu.hw6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import static edu.hw6.Task2.cloneFile;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    final static File EXISTING_FILE = new File("src/test/resources/Task2/existingFile.txt");
    static final File NON_EXISTING_FILE = new File("src/test/resources/Task2/nonExistingFile.txt");
    static final File EXISTING_COPY = new File("src/test/resources/Task2/existingFile - копия.txt");

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(EXISTING_FILE.toPath());
        PrintWriter printWriter = new PrintWriter(EXISTING_FILE);
        printWriter.write("This is some content.");
        printWriter.close();
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.deleteIfExists(NON_EXISTING_FILE.toPath());
        File test2Directory = new File("src/test/resources/Task2");
        File[] files = test2Directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("existingFile - копия")) {
                    file.delete();
                }
            }
        }
    }

    @Test
    @DisplayName("Test for cloning an existing file")
    void cloneFile_shouldCloneFileWithoutCopy() {
        // Act
        cloneFile(EXISTING_FILE.toPath());

        // Assert
        assertThat(Files.exists(EXISTING_FILE.toPath())).isTrue();
    }

    @Test
    @DisplayName("Test for cloning an existing file with copy")
    void cloneFile_shouldCloneFileWithCopy() {
        // Act
        cloneFile(EXISTING_FILE.toPath());

        // Assert
        assertThat(Files.exists(EXISTING_COPY.toPath())).isTrue();
    }

    @Test
    @DisplayName("Test for cloning a file that already has multiple copies")
    void cloneFile_shouldCloneFileWithMultipleCopies() {
        // Arrange
        File existingCopy1 = new File("src/test/resources/Task2/existingFile - копия(1).txt");
        File existingCopy2 = new File("src/test/resources/Task2/existingFile - копия(2).txt");
        try {
            existingCopy1.createNewFile();
            existingCopy2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Act
        cloneFile(EXISTING_FILE.toPath());

        // Assert
        assertThat(Files.exists(new File("src/test/resources/Task2/existingFile - копия(3).txt").toPath())).isTrue();
    }

}
