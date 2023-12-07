package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DaysAgoFormatter extends ChainDateFormatter {


    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            String[] parsedDate = date.split(" ");
            LocalDate result = null;
            if (checkDaysAgo(date)) {
                result = LocalDate.now().minusDays(Long.parseLong(parsedDate[0]));
            }
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return formatDate(date);
        }
    }

    private boolean checkDaysAgo(String date) {
        Pattern pattern = Pattern.compile("^\\d+\\s+((day)|(days))\\s+ago$");
        return pattern.matcher(date).matches();
    }

}
