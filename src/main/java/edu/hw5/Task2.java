package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public final class Task2 {
    private Task2() {
    }

    private final static int NUMBER_OF_MONTHS = 12;
    private final static int THIRTEENTH = 13;

    public static ArrayList<LocalDate> fridayThirteen(int year) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_MONTHS; i++) {
            LocalDate date = LocalDate.of(year, i, THIRTEENTH);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(date);
            }
        }

        return dates;
    }

    public static LocalDate findNextFridayThirteen(LocalDate currentDate) {
        LocalDate nextFriday = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday.getDayOfMonth() != THIRTEENTH) {
            nextFriday = nextFriday.plusWeeks(1);
        }

        return nextFriday;
    }

}
