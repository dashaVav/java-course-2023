package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @ParameterizedTest
    @CsvSource({
        "13:56, 836",
        "00:00, 0",
        "01:00, 60",
        "999:59, 59999"
    })
    public void testMinutesToSecondsCorrectTime(String input, int expected) {
        int result = Task1.minutesToSeconds(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10:60, -1",
        "10:100, -1"
    })
    public void testMinutesToSecondsIncorrectTime(String input, int expected) {
        int result = Task1.minutesToSeconds(input);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "-01:00, -1",
        "00:-00, -1",
        "1:2, -1",
        "invalid, -1",
        "11:22:33, -1"
    })
    public void testMinutesToSecondsInvalidString(String input, int expected) {
        int result = Task1.minutesToSeconds(input);

        assertEquals(expected, result);
    }

    @Test
    public void testMinutesToSecondsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Task1.minutesToSeconds(null));
    }
}
