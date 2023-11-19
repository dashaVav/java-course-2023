package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        Parser wordParser = new WordParser();
        Parser daysAgoParser = new DaysAgoParser();
        Parser dateFormatParser = new DateFormatParser();

        wordParser.setNext(daysAgoParser);
        daysAgoParser.setNext(dateFormatParser);

        return wordParser.parseDate(string);
    }
}
