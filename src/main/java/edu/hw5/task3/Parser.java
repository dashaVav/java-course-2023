package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Parser {
    protected Parser next;

    public void setNext(Parser next) {
        this.next = next;
    }

    public Optional<LocalDate> parseDate(String date) {
        Optional<LocalDate> parsedDate = parse(date);

        if (parsedDate.isEmpty() && next != null) {
            parsedDate = next.parseDate(date);
        }

        return parsedDate;
    }

    public abstract Optional<LocalDate> parse(String date);
}
