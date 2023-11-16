package edu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2 final class IOUtil {

    private IOUtil() {

    }

    public static List<String> getRemoteLogs(String urlString) {
        try {
            return new BufferedReader(new InputStreamReader(new URL(urlString).openStream()))
                .lines()
                .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Could not access a remote resource: {}", urlString, e);
            throw new RuntimeException("Error accessing remote resource: " + urlString, e);
        }
    }

    public static List<String> getLocalLogs(String filepath) {
        try {
            return Files.readAllLines(Path.of(filepath));
        } catch (IOException e) {
            log.error("Could not access a local resource: {}", filepath, e);
            throw new RuntimeException("Error accessing local resource: " + filepath, e);
        }
    }

    public static void saveFormat(String text, String path, String format) {
        File file = switch (format) {
            case "markdown" -> new File(path + ".md");
            case "adoc" -> new File(path + ".adoc");
            default -> new File(path + ".txt");
        };
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file))) {
            outputStreamWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
