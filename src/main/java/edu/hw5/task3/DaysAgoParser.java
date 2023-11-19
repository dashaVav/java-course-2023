package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoParser extends Parser {
    @Override
    public Optional<LocalDate> parse(String date) {
        if (date.matches("(^\\d+ days ago$)|(^1 day ago$)")) {
            int daysAgo = Integer.parseInt(date.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(daysAgo));
        }
        return Optional.empty();
    }
}
