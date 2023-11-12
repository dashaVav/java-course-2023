package edu.hw5;

import edu.hw5.task3.Task3;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Task3Test {
    @Test
    public void testWordParser() {
        Optional<LocalDate> resultToday = Task3.parseDate("today");
        assertEquals(resultToday.get(), LocalDate.now());

        Optional<LocalDate> resultYesterday = Task3.parseDate("yesterday");
        assertEquals(resultYesterday.get(), LocalDate.now().minusDays(1));

        Optional<LocalDate> resultTomorrow = Task3.parseDate("tomorrow");
        assertEquals(resultTomorrow.get(), LocalDate.now().plusDays(1));
    }

    @Test
    public void testDaysParser() {
        Optional<LocalDate> resultDayAgo = Task3.parseDate("1 day ago");
        assertEquals(resultDayAgo.get(), LocalDate.now().minusDays(1));

        Optional<LocalDate> resultDaysAgo = Task3.parseDate("2234 days ago");
        assertEquals(resultDaysAgo.get(), LocalDate.now().minusDays(2234));
    }

    @Test
    public void testDateFormatParser() {
        Optional<LocalDate> date1 = Task3.parseDate("2020-10-10");
        assertEquals(date1.get(), LocalDate.of(2020, 10, 10));

        Optional<LocalDate> date2 = Task3.parseDate("2020-12-2");
        assertEquals(date2.get(), LocalDate.of(2020, 12, 2));

        Optional<LocalDate> date3 = Task3.parseDate("1/3/1976");
        assertEquals(date3.get(), LocalDate.of(1976, 1, 3));

        Optional<LocalDate> date4 = Task3.parseDate("1/3/20");
        assertEquals(date4.get(),  LocalDate.of(2020, 1, 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-22-10",
        "2020 12-2",
        "1//3/1976",
        "112/3/20",
        "tomo",
        "wordword",
        "-1 day ago"
    })
    public void testInvalidData(String date) {
        Optional<LocalDate> result = Task3.parseDate(date);
        assertTrue(result.isEmpty());
    }
}
