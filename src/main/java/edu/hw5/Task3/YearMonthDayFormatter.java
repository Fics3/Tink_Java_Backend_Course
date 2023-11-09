package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class YearMonthDayFormatter extends ChainDateFormatter {
    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate result = LocalDate.parse(date, dateTimeFormatter);
            return Optional.of(result);
        } catch (Exception e) {
            return formatDate(date);
        }
    }
}
