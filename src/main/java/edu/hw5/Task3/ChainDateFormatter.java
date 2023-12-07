package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import lombok.Getter;

@Getter
public abstract class ChainDateFormatter {

    private ChainDateFormatter nextFormatter;

    public void linkWith(ChainDateFormatter nextFormatter) {
        this.nextFormatter = nextFormatter;
    }

    public Optional<LocalDate> formatDate(String date) {
        if (date.isEmpty() || nextFormatter == null) {
            return Optional.empty();
        }
        return nextFormatter.parse(date);
    }

    public abstract Optional<LocalDate> parse(String date);

}
