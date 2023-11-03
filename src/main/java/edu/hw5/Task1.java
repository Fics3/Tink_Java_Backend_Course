package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class Task1 {

    private Task1() {

    }

    public static String calculateSessionDuration(String session) {
        try {
            String[] parsedSession = session.split(" - ");
            String start = parsedSession[0];
            String end = parsedSession[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);

            Duration time = Duration.between(startTime, endTime);

            if (time.isNegative()) {
                time = Duration.ZERO;
            }

            return time.toHours() + "ч" + time.toMinutesPart() + "м";
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect date format, should be 'yyyy-MM-dd, HH:mm'");
        }

    }

}
