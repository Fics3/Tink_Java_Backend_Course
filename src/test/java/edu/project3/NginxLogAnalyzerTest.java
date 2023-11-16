package edu.project3;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;

class NginxLogAnalyzerTest {

    private NginxLogAnalyzer nginxLogAnalyzer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final StringBuilder logOutput = new StringBuilder();

    private Appender mockAppender;

    @BeforeEach
    void setUp() {
        nginxLogAnalyzer = new NginxLogAnalyzer();

        LoggerContext ctx = LoggerContext.getContext(false);

        mockAppender = new AbstractAppender("MockAppender", null, null) {
            @Override
            public void append(LogEvent event) {
                logOutput(event.getMessage().getFormattedMessage());
            }
        };
        mockAppender.start();
        ctx.getRootLogger().addAppender(mockAppender);
    }

    @AfterEach
    void tearDown() {
        LoggerContext.getContext(false).getRootLogger().removeAppender(mockAppender);
    }

    @Test
    @DisplayName("Analyze Logs - Valid Command")
    void analyzeLogs_ValidCommand_shouldAnalyzeLogs() {
        // Arrange
        String[] command =
            {"--path", "src/test/resources/project3/test.log", "--from", "01/Jan/2023:00:00:00 +0000", "--end",
                "01/Feb/2023:23:59:59 +0000",
                "--format", "adoc", "--save", "src/test/resources/project3/output"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        String expectedOutput = "= Nginx Log Analysis Report\n" +
            "\n" +
            "== Общая информация\n" +
            "\n" +
            "|===\n" +
            "| Метрика               | Значение         \n" +
            "|Файл(-ы)|src/test/resources/project3/test.log\n" +
            "|Начальная дата|2023-01-01T00:00Z\n" +
            "|Конечная дата|2023-02-01T23:59:59Z\n" +
            "|Количество запросов|0\n" +
            "|Средний размер ответа|0b\n" +
            "|Количество уникальных ip адресов|0\n" +
            "|===\n" +
            "\n" +
            "== Запрашиваемые ресурсы\n" +
            "\n" +
            "|===\n" +
            "| Ресурс | Количество\n" +
            "|===\n" +
            "\n" +
            "== Коды ответа\n" +
            "\n" +
            "|===\n" +
            "| Код | Имя                   | Количество\n" +
            "|===\n" +
            "\n" +
            "== Запросы по времени суток\n" +
            "\n" +
            "|===\n" +
            "| Время суток | Количество\n" +
            "|===\n";

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains(expectedOutput);

    }

    @Test
    @DisplayName("Analyze Logs - Valid Command (Invalid Format)")
    void analyzeLogs_ValidCommandInvalidFormat_shouldUsePlainTextFormat() {
        // Arrange
        String[] command = {
            "--path", "src/test/resources/project3/test.log", "--from", "01/Jan/2023:00:00:00 +0000", "--end",
            "01/Feb/2023:23:59:59 +0000",
            "--format", "invalid_format", "--save", "src/test/resources/project3/output"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        String expectedOutput = "= Nginx Log Analysis Report =\n" +
            "\n" +
            "= Общая информация\n" +
            "\n" +
            "= Метрика               = Значение         \n" +
            "|Файл(-ы)|src/test/resources/project3/test.log\n" +
            "|Начальная дата|2023-01-01T00:00Z\n" +
            "|Конечная дата|2023-02-01T23:59:59Z\n" +
            "|Количество запросов|0\n" +
            "|Средний размер ответа|0b\n" +
            "|Количество уникальных ip адресов|0\n" +
            "\n" +
            "= Запрошенные ресурсы\n" +
            "\n" +
            "= Ресурс = Количество\n" +
            "\n" +
            "= Коды ответов\n" +
            "\n" +
            "= Код = Имя                   = Количество\n" +
            "\n" +
            "= Запросы по времени суток\n" +
            "\n" +
            "= Время суток = Количество\n";

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains(expectedOutput);
    }

    @Test
    @DisplayName("Analyze Logs - Valid Command (Markdown Format)")
    void analyzeLogs_ValidCommandMarkdownFormat_shouldAnalyzeLogs() {
        // Arrange
        String[] command = {
            "--path", "src/test/resources/project3/test.log", "--from", "01/Jan/2023:00:00:00 +0000", "--end",
            "01/Feb/2023:23:59:59 +0000",
            "--format", "markdown", "--save", "src/test/resources/project3/output"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        String expectedOutput = "##### Nginx Log Analysis Report\n" +
            "\n" +
            "#### Общая информация\n" +
            "| Метрика               | Значение    |\n" +
            "|:---------------------:|:-----------:|\n" +
            "|Файл(-ы)|src/test/resources/project3/test.log|\n" +
            "|Начальная дата|2023-01-01T00:00Z|\n" +
            "|Конечная дата|2023-02-01T23:59:59Z|\n" +
            "|Количество запросов|0|\n" +
            "|Средний размер ответа|0b|\n" +
            "|Количество уникальных ip адресов|0|\n" +
            "\n" +
            "#### Запрашиваемые ресурсы\n" +
            "| Ресурс           | Количество  |\n" +
            "|:----------------:|:-----------:|\n" +
            "\n" +
            "#### Коды ответа\n" +
            "| Код | Имя                   | Количество  |\n" +
            "|:---:|:----------------------:|:-----------:|\n" +
            "\n" +
            "#### Запросы по времени суток\n" +
            "| Время суток | Количество  |\n" +
            "|:-----------:|:-----------:|\n";

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains(expectedOutput);
    }

    @Test
    @DisplayName("Analyze Logs - Valid Command (Logs from File)")
    void analyzeLogs_ValidCommandLogsFromFile_shouldAnalyzeLogs() {
        // Arrange
        String[] command = {
            "--path", "src/test/resources/project3/test.log", "--from", "01/Jan/2015:00:00:00 +0000",
            "--format", "plain", "--save", "src/test/resources/project3/output"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        String expectedOut = "= Nginx Log Analysis Report =\n" +
            "\n" +
            "= Общая информация\n" +
            "\n" +
            "= Метрика               = Значение         \n" +
            "|Файл(-ы)|src/test/resources/project3/test.log\n" +
            "|Начальная дата|2015-01-01T00:00Z\n" +
            "|Конечная дата|-\n" +
            "|Количество запросов|10\n" +
            "|Средний размер ответа|164b\n" +
            "|Количество уникальных ip адресов|3\n" +
            "\n" +
            "= Запрошенные ресурсы\n" +
            "\n" +
            "= Ресурс = Количество\n" +
            "|/downloads/product_1|8\n" +
            "|/downloads/product_2|2\n" +
            "\n" +
            "= Коды ответов\n" +
            "\n" +
            "= Код = Имя                   = Количество\n" +
            "|304|Not modified|6\n" +
            "|404|Not Found|2\n" +
            "|200|OK|2\n" +
            "\n" +
            "= Запросы по времени суток\n" +
            "\n" +
            "= Время суток = Количество\n" +
            "|8|10\n";

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains(expectedOut);
    }

    @Test
    @DisplayName("Analyze Logs - Invalid File Path")
    void analyzeLogs_InvalidFilePath_shouldPrintErrorMessage() {
        // Arrange
        String[] command = {"--path", "invalid_path.log", "--format", "plain"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        // Act
        try {
            nginxLogAnalyzer.analyzeLogs(command);
        } catch (Exception e) {
            // Assert
            assertThat(logOutput.toString()).contains("Could not access a local resource: invalid_path.log");
        }
    }

    private void logOutput(String logMessage) {
        logOutput.append(logMessage).append(System.lineSeparator());
    }

    @Test
    @DisplayName("Analyze Logs - Missing Format Option")
    void analyzeLogs_MissingFormatOption_shouldUseDefaultFormat() {
        // Arrange
        String[] command = {"--path", "src/test/resources/project3/test.log"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains("= Nginx Log Analysis Report");
    }

    @Test
    @DisplayName("Analyze Logs - Save Output to File")
    void analyzeLogs_SaveOutputToFile_shouldSaveFile() {
        // Arrange
        String[] command = {
            "--path", "src/test/resources/project3/test.log",
            "--format", "adoc",
            "--save", "src/test/resources/project3/output.adoc"
        };
        nginxLogAnalyzer = new NginxLogAnalyzer();

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(new File("src/test/resources/project3/output.adoc")).exists();
    }

    @Test
    @DisplayName("Analyze Logs - Path Only")
    void analyzeLogs_PathOnly_shouldAnalyzeLogs() {
        // Arrange
        String[] command = {"--path", "src/test/resources/project3/test.log"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains("= Nginx Log Analysis Report");
    }

    @Test
    @DisplayName("Analyze Logs - Without From and End")
    void analyzeLogs_WithoutFromAndAnd_shouldAnalyzeLogs() {
        // Arrange
        String[] command = {"--path", "src/test/resources/project3/test.log"};
        nginxLogAnalyzer = new NginxLogAnalyzer();

        // Act
        nginxLogAnalyzer.analyzeLogs(command);

        // Assert
        assertThat(logOutput.toString()).contains("|Начальная дата|-");
        assertThat(logOutput.toString()).contains("|Конечная дата|-");
    }

}
