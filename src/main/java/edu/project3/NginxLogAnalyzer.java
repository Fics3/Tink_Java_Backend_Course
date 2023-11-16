package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import static edu.project3.DateManager.formatDate;
import static edu.project3.DateManager.isDateInRange;
import static edu.project3.IOUtil.saveFormat;
import static edu.project3.LogViewer.generateAdocReport;
import static edu.project3.LogViewer.generateMarkdownReport;
import static edu.project3.LogViewer.generatePlainTextReport;
import static edu.project3.StatManager.calculateAverageResponseSize;
import static edu.project3.StatManager.countRequestsByTimeOfDay;
import static edu.project3.StatManager.countResources;
import static edu.project3.StatManager.countResponses;
import static edu.project3.StatManager.countUniqueIPAddresses;

@Log4j2
@Getter
public class NginxLogAnalyzer implements LogAnalyzer {

    private static final int TIME_GROUP = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    List<NginxLog> nginxLogList;

    private Map<String, Integer> resourceCount;
    private Map<Integer, Integer> requestCodeCount;
    private Map<Integer, Integer> requestsByTime;
    private int ipAddressesCount;
    private int totalRequests;
    private int averageResponseSize;

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String format = "plain";
    private String path;
    private String pathToSave;

    @Override
    public void analyzeLogs(String[] command) {
        parseCommand(command);

        nginxLogList = new ArrayList<>();

        parseLogs();

        getStatsParameter(nginxLogList);

        String parsedLogs;
        switch (format) {
            case "markdown" -> {
                parsedLogs = generateMarkdownReport(this);
                log.info(parsedLogs);
            }
            case "adoc" -> {
                parsedLogs = generateAdocReport(this);
                log.info(parsedLogs);
            }
            default -> {
                parsedLogs = generatePlainTextReport(this);
                log.info(parsedLogs);
            }
        }
        if (pathToSave != null) {
            saveFormat(parsedLogs, pathToSave, format);
        }
    }

    private void parseCommand(String[] command) {
        if (command.length < 2) {
            log.error("Usage: --path <path> [--from <from-date>] [--end <end-date>] [--format <format>]");
            throw new IllegalArgumentException("Invalid command parameters.");
        }

        for (int i = 0; i < command.length; i++) {
            switch (command[i]) {
                case "--path":
                    path = getNextCommandParameter(command, i);
                    break;
                case "--from":
                    startDate = formatDate(getNextCommandParameter(command, i));
                    break;
                case "--end":
                    endDate = formatDate(getNextCommandParameter(command, i));
                    break;
                case "--format":
                    format = getNextCommandParameter(command, i);
                    break;
                case "--save":
                    pathToSave = getNextCommandParameter(command, i);
                    break;
                default:
            }
        }
    }

    private String getNextCommandParameter(String[] command, int currentIndex) {
        if (currentIndex + 1 < command.length) {
            return command[currentIndex + 1];
        }
        throw new IllegalArgumentException("Missing value for command option: " + command[currentIndex]);
    }

    private void parseLogs() {
        List<String> logs = getLogs(path);
        Pattern nginxLogPattern =
            Pattern.compile("^(\\S+) - (\\S+) \\[(.*?)] (\\S+) (\\S+) (\\S+) (\\d+) (\\d+) (.*?) (.*?)$");

        for (String line : logs) {
            Matcher matcher = nginxLogPattern.matcher(line);
            if (matcher.matches()) {
                OffsetDateTime date = formatDate(matcher.group(TIME_GROUP));
                if (isDateInRange(date, startDate, endDate)) {
                    nginxLogList.add(new NginxLog(matcher, date));
                }
            }
        }

    }

    private void getStatsParameter(List<NginxLog> nginxLogList) {
        totalRequests = nginxLogList.size();
        averageResponseSize = calculateAverageResponseSize(nginxLogList);
        resourceCount = countResources(nginxLogList);
        requestCodeCount = countResponses(nginxLogList);
        requestsByTime = countRequestsByTimeOfDay(nginxLogList);
        ipAddressesCount = countUniqueIPAddresses(nginxLogList);
    }

    private List<String> getLogs(String path) {
        List<String> logs;
        if (path.startsWith("http")) {
            logs = IOUtil.getRemoteLogs(path);
        } else {
            logs = IOUtil.getLocalLogs(path);
        }
        return logs;
    }

}
