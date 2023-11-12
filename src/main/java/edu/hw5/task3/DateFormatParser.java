package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateFormatParser extends Parser {
    @Override
    public Optional<LocalDate> parse(String date) {
        try {
            if (date.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                return Optional.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d")));
            }

            if (date.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
                return Optional.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy")));
            }

            if (date.matches("^\\d{1,2}/\\d{1,2}/\\d{2}$")) {
                return Optional.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yy")));
            }
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

}
