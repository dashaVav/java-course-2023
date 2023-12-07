package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "5, 120",
        "10, 3628800"
    })
    void testFactorialOfNumber(long input, long expected) {
        assertEquals(expected, Task2.factorialOfNumber(input));
    }
}
