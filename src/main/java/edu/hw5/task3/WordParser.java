package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class WordParser extends Parser {
    @Override
    public Optional<LocalDate> parse(String date) {
        if (date.equalsIgnoreCase("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        }

        if (date.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        }

        if (date.equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        return Optional.empty();
    }
}
