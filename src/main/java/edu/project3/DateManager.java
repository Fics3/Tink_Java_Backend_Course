package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

final class DateManager {

    private DateManager() {

    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    public static OffsetDateTime formatDate(String date) {
        return OffsetDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public static boolean isDateInRange(OffsetDateTime date, OffsetDateTime startDate, OffsetDateTime endDate) {
        return (startDate == null || date.isAfter(startDate)) && (endDate == null || date.isBefore(endDate));
    }

}
