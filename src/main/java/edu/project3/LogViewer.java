package edu.project3;

import java.util.Map;

public final class LogViewer {

    private static final String TABLE_SEPARATOR = "|===\n";
    private static final String TABLE_SEPARATOR_WITH_NEWLINE = "|===\n\n";
    private static final String TABLE_CELL_SEPARATOR = "|";
    private static final String NEWLINE = "\n";

    private LogViewer() {
    }

    public static String generatePlainTextReport(NginxLogAnalyzer nginxLogAnalyzer) {
        StringBuilder plainTextReport = new StringBuilder();

        plainTextReport.append("= Nginx Log Analysis Report =\n\n");

        plainTextReport.append("= Общая информация\n\n");
        plainTextReport.append("= Метрика               = Значение         \n");
        generateGeneralInformation(nginxLogAnalyzer, plainTextReport);
        plainTextReport.append("\n");

        plainTextReport.append("= Запрошенные ресурсы\n\n");
        plainTextReport.append("= Ресурс = Количество\n");
        generateSection(plainTextReport, nginxLogAnalyzer.getResourceCount(), nginxLogAnalyzer.getFormat());
        plainTextReport.append("\n");

        plainTextReport.append("= Коды ответов\n\n");
        plainTextReport.append("= Код = Имя                   = Количество\n");
        generateRequestCodeSection(
            plainTextReport,
            nginxLogAnalyzer.getRequestCodeCount(),
            nginxLogAnalyzer.getFormat()
        );
        plainTextReport.append("\n");

        plainTextReport.append("= Запросы по времени суток\n\n");
        plainTextReport.append("= Время суток = Количество\n");
        generateSection(plainTextReport, nginxLogAnalyzer.getRequestsByTime(), nginxLogAnalyzer.getFormat());
        plainTextReport.append("\n");

        return plainTextReport.toString();
    }

    public static String generateAdocReport(NginxLogAnalyzer nginxLogAnalyzer) {
        StringBuilder adocReport = new StringBuilder();

        adocReport.append("= Nginx Log Analysis Report\n\n");

        adocReport.append("== Общая информация\n\n");
        adocReport.append(TABLE_SEPARATOR);
        adocReport.append("| Метрика               | Значение         \n");
        generateGeneralInformation(nginxLogAnalyzer, adocReport);
        adocReport.append(TABLE_SEPARATOR_WITH_NEWLINE);

        adocReport.append("== Запрашиваемые ресурсы\n\n");
        adocReport.append(TABLE_SEPARATOR);
        adocReport.append("| Ресурс | Количество\n");
        generateSection(adocReport, nginxLogAnalyzer.getResourceCount(), nginxLogAnalyzer.getFormat());
        adocReport.append(TABLE_SEPARATOR_WITH_NEWLINE);

        adocReport.append("== Коды ответа\n\n");
        adocReport.append(TABLE_SEPARATOR);
        adocReport.append("| Код | Имя                   | Количество\n");
        generateRequestCodeSection(adocReport, nginxLogAnalyzer.getRequestCodeCount(), nginxLogAnalyzer.getFormat());
        adocReport.append(TABLE_SEPARATOR_WITH_NEWLINE);

        adocReport.append("== Запросы по времени суток\n\n");
        adocReport.append(TABLE_SEPARATOR);
        adocReport.append("| Время суток | Количество\n");
        generateSection(adocReport, nginxLogAnalyzer.getRequestsByTime(), nginxLogAnalyzer.getFormat());
        adocReport.append(TABLE_SEPARATOR_WITH_NEWLINE);

        return adocReport.toString();
    }

    public static String generateMarkdownReport(NginxLogAnalyzer nginxLogAnalyzer) {
        StringBuilder markdownReport = new StringBuilder();
        markdownReport.append("##### Nginx Log Analysis Report\n\n");

        markdownReport.append("#### Общая информация\n");
        markdownReport.append("| Метрика               | Значение    |\n");
        markdownReport.append("|:---------------------:|:-----------:|\n");
        generateGeneralInformation(nginxLogAnalyzer, markdownReport);

        markdownReport.append("\n#### Запрашиваемые ресурсы\n");
        markdownReport.append("| Ресурс           | Количество  |\n");
        markdownReport.append("|:----------------:|:-----------:|\n");
        generateSection(markdownReport, nginxLogAnalyzer.getResourceCount(), nginxLogAnalyzer.getFormat());
        markdownReport.append("\n");

        markdownReport.append("#### Коды ответа\n");
        markdownReport.append("| Код | Имя                   | Количество  |\n");
        markdownReport.append("|:---:|:----------------------:|:-----------:|\n");
        generateRequestCodeSection(
            markdownReport,
            nginxLogAnalyzer.getRequestCodeCount(),
            nginxLogAnalyzer.getFormat()
        );

        markdownReport.append("\n#### Запросы по времени суток\n");
        markdownReport.append("| Время суток | Количество  |\n");
        markdownReport.append("|:-----------:|:-----------:|\n");
        generateSection(markdownReport, nginxLogAnalyzer.getRequestsByTime(), nginxLogAnalyzer.getFormat());
        markdownReport.append("\n");

        return markdownReport.toString();
    }

    private static <T> void generateSection(StringBuilder report, Map<T, Integer> data, String format) {
        if (data != null) {
            for (var entry : data.entrySet()) {
                report.append(TABLE_CELL_SEPARATOR).append(entry.getKey()).append(TABLE_CELL_SEPARATOR)
                    .append(entry.getValue());
                appendEnd(report, format);
            }
        }
    }

    private static void generateRequestCodeSection(StringBuilder report, Map<Integer, Integer> data, String format) {
        if (data != null) {
            for (var entry : data.entrySet()) {
                report.append(TABLE_CELL_SEPARATOR)
                    .append(entry.getKey())
                    .append(TABLE_CELL_SEPARATOR)
                    .append(getRequestCodeName(entry.getKey()))
                    .append(TABLE_CELL_SEPARATOR)
                    .append(entry.getValue());
                appendEnd(report, format);
            }
        }
    }

    private static void generateGeneralInformation(NginxLogAnalyzer nginxLogAnalyzer, StringBuilder report) {
        appendTableRow(report, "Файл(-ы)", nginxLogAnalyzer.getPath(), nginxLogAnalyzer.getFormat());
        appendTableRow(
            report,
            "Начальная дата",
            nginxLogAnalyzer.getStartDate() != null ? nginxLogAnalyzer.getStartDate().toString() : "-",
            nginxLogAnalyzer.getFormat()
        );
        appendTableRow(
            report,
            "Конечная дата",
            nginxLogAnalyzer.getEndDate() != null ? nginxLogAnalyzer.getEndDate().toString() : "-",
            nginxLogAnalyzer.getFormat()
        );
        appendTableRow(
            report,
            "Количество запросов",
            String.valueOf(nginxLogAnalyzer.getTotalRequests()),
            nginxLogAnalyzer.getFormat()
        );
        appendTableRow(
            report,
            "Средний размер ответа",
            nginxLogAnalyzer.getAverageResponseSize() + "b",
            nginxLogAnalyzer.getFormat()
        );
        appendTableRow(
            report,
            "Количество уникальных ip адресов",
            String.valueOf(nginxLogAnalyzer.getIpAddressesCount()),
            nginxLogAnalyzer.getFormat()
        );
    }

    private static void appendTableRow(StringBuilder report, String metric, String value, String format) {
        report.append(TABLE_CELL_SEPARATOR).append(metric).append(TABLE_CELL_SEPARATOR).append(value);

        appendEnd(report, format);
    }

    private static void appendEnd(StringBuilder report, String format) {
        switch (format) {
            case "adoc" -> report.append(NEWLINE);
            case "markdown" -> report.append(TABLE_CELL_SEPARATOR).append(NEWLINE);
            default -> report.append(NEWLINE);
        }
    }

    @SuppressWarnings("MagicNumber")
    private static String getRequestCodeName(int code) {
        return switch (code) {
            case 200 -> "OK";
            case 206 -> "Partial content";
            case 304 -> "Not modified";
            case 403 -> "Forbidden";
            case 416 -> "Range not satisfiable";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
