package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @ParameterizedTest
    @ValueSource(strings = {"1", "101", "010", "101010011"})
    public void testTask1TrueResult(String input) {
        boolean result = Task8.task1(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "1110", "103", "1010"})
    public void testTask1FalseResult(String input) {
        boolean result = Task8.task1(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"110", "1110", "011", "10101001"})
    public void testTask4TrueResult(String input) {
        boolean result = Task8.task4(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111", "123", "34"})
    public void testTask4FalseResult(String input) {
        boolean result = Task8.task4(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "1", "10", "10101", "111011"})
    public void testTask5TrueResult(String input) {
        boolean result = Task8.task5(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "011", "123", "1100", ""})
    public void testTask5FalseResult(String input) {
        boolean result = Task8.task5(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "1", "01", "10101001"})
    public void testTask7TrueResult(String input) {
        boolean result = Task8.task7(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111", "123", "010101100"})
    public void testTask7FalseResult(String input) {
        boolean result = Task8.task7(input);
        assertFalse(result);
    }
}
