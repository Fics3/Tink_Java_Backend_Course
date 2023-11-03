package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

final class Task2 {

    private static final int MAX_MONTHS = 12;
    private static final int THIRTEEN = 13;

    private Task2() {

    }

    public static List<LocalDate> findAllFridays13th(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year can't be negative");
        }
        List<LocalDate> result = new ArrayList<>();
        for (int month = 1; month <= MAX_MONTHS; month++) {
            LocalDate date = LocalDate.of(year, month, THIRTEEN);

            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                result.add(date);
            }
        }
        return result;
    }

    public static LocalDate findNext13thFriday(LocalDate date) {
        LocalDate result = date;
        while (true) {
            result = result.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (result.getDayOfMonth() == THIRTEEN) {
                return result;
            }
        }
    }

}
