package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    @ParameterizedTest
    @ValueSource(strings = {"000", "110", "0101111"})
    public void testTask1TrueResult(String input) {
        boolean result = Task7.task1(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "11", "00", "001", "0002", "230"})
    public void testTask1FalseResult(String input) {
        boolean result = Task7.task1(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "11", "00", "010", "1000001", "101010101"})
    public void testTask2TrueResult(String input) {
        boolean result = Task7.task2(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "10", "01", "001", "2002", "34443"})
    public void testTask2FalseResult(String input) {
        boolean result = Task7.task2(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "11", "00", "010", "100"})
    public void testTask3TrueResult(String input) {
        boolean result = Task7.task3(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1011", "011111", "123"})
    public void testTask3FalseResult(String input) {
        boolean result = Task7.task3(input);
        assertFalse(result);
    }
}
