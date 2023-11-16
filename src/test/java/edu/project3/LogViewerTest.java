package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogViewerTest {
    NginxLogAnalyzer nginxLogAnalyzer;

    @BeforeEach
    void setUp() {
        nginxLogAnalyzer = new NginxLogAnalyzer();
    }

    @Test
    @DisplayName("Generate Plain Text Report")
    void generatePlainTextReport_shouldGeneratePlainTextReport() {
        // Act
        String plainTextReport = LogViewer.generatePlainTextReport(nginxLogAnalyzer);

        // Assert
        assertThat(plainTextReport).isNotNull();
        assertThat(plainTextReport).contains("Nginx Log Analysis Report");
    }

    @Test
    @DisplayName("Generate AsciiDoc Report")
    void generateAdocReport_shouldGenerateAdocReport() {
        // Act
        String adocReport = LogViewer.generateAdocReport(nginxLogAnalyzer);

        // Assert
        assertThat(adocReport).isNotNull();
        assertThat(adocReport).contains("Nginx Log Analysis Report");
    }

    @Test
    @DisplayName("Generate Markdown Report")
    void generateMarkdownReport_shouldGenerateMarkdownReport() {
        // Act
        String markdownReport = LogViewer.generateMarkdownReport(nginxLogAnalyzer);

        // Assert
        assertThat(markdownReport).isNotNull();
        assertThat(markdownReport).contains("Nginx Log Analysis Report");
    }
}
