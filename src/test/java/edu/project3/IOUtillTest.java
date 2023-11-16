package edu.project3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static edu.project3.IOUtil.getLocalLogs;
import static edu.project3.IOUtil.getRemoteLogs;
import static edu.project3.IOUtil.saveFormat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IOUtillTest {

    @Test
    @DisplayName("Get Remote Logs - Valid URL")
    void getRemoteLogs_ValidURL_shouldReturnListOfLogs() {
        // Arrange
        String validURL = "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        // Act
        List<String> logs = getRemoteLogs(validURL);

        // Assert
        assertThat(logs).isNotNull();
        assertThat(logs).isNotEmpty();
    }

    @Test
    @DisplayName("Get Remote Logs - Invalid URL")
    void getRemoteLogs_InvalidURL_shouldThrowRuntimeException() {
        // Arrange
        String invalidURL = "https://example.invalid/logs.txt";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> getRemoteLogs(invalidURL));
    }

    @Test
    @DisplayName("Get Local Logs - Valid Filepath")
    void getLocalLogs_ValidFilepath_shouldReturnListOfLogs() throws IOException {
        // Arrange
        String content = "Sample log entry\nAnother log entry";
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, content.getBytes());

        // Act
        List<String> logs = getLocalLogs(tempFile.toString());

        // Assert
        assertThat(logs).isNotNull();
        assertThat(logs).containsExactly("Sample log entry", "Another log entry");

        // Clean up
        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("Get Local Logs - Invalid Filepath")
    void getLocalLogs_InvalidFilepath_shouldThrowRuntimeException() {
        // Arrange
        String invalidFilepath = "invalid/filepath.txt";

        // Act and Assert
        assertThrows(RuntimeException.class, () -> getLocalLogs(invalidFilepath));
    }

    @Test
    @DisplayName("Save Format - Markdown")
    void saveFormat_Markdown_shouldSaveFileWithMdExtension() throws IOException {
        // Arrange
        String text = "Markdown content";
        String path = "testFile";
        String format = "markdown";

        // Act
        saveFormat(text, path, format);

        // Assert
        File mdFile = new File(path + ".md");
        assertThat(mdFile).exists();

        // Clean up
        Files.deleteIfExists(Path.of(mdFile.toURI()));
    }

    @Test
    @DisplayName("Save Format - AsciiDoc")
    void saveFormat_AsciiDoc_shouldSaveFileWithAdocExtension() throws IOException {
        // Arrange
        String text = "AsciiDoc content";
        String path = "testFile";
        String format = "adoc";

        // Act
        saveFormat(text, path, format);

        // Assert
        File adocFile = new File(path + ".adoc");
        assertThat(adocFile).exists();

        // Clean up
        Files.deleteIfExists(Path.of(adocFile.toURI()));
    }

    @Test
    @DisplayName("Save Format - Default")
    void saveFormat_Default_shouldSaveFileWithTxtExtension() throws IOException {
        // Arrange
        String text = "Default content";
        String path = "testFile";
        String format = "unknown";

        // Act
        saveFormat(text, path, format);

        // Assert
        File txtFile = new File(path + ".txt");
        assertThat(txtFile).exists();

        // Clean up
        Files.deleteIfExists(Path.of(txtFile.toURI()));
    }
}
