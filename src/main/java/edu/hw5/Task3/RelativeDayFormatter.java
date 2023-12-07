package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class RelativeDayFormatter extends ChainDateFormatter {
    @Override
    public Optional<LocalDate> parse(String date) {
        return switch (date) {
            case "today" -> Optional.of(LocalDate.now());
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> formatDate(date);
        };
    }
}
