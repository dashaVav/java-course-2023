package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(ints = {-11211230, 11211230, 13001120, 23336014, 123, 5610})
    public void testIsPalindromeDescendantWithPalindromeDescendant(int input) {
        boolean actualResult = Task5.isPalindromeDescendant(input);
        assertTrue(actualResult);
    }

    @Test
    void testIsPalindromeDescendantWithPalindrome() {
        // given
        int number = 11;

        // when
        boolean actualResult = Task5.isPalindromeDescendant(number);

        // then
        assertTrue(actualResult);
    }

    @Test
    void testIsPalindromeDescendantWithSingleDigit() {
        // given
        int number = 1;

        // when
        boolean actualResult = Task5.isPalindromeDescendant(number);

        // then
        assertFalse(actualResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {8899, 8820, 13001121})
    public void testIsPalindromeDescendantWithNoPalindromeDescendant(int input) {
        boolean actualResult = Task5.isPalindromeDescendant(input);
        assertFalse(actualResult);
    }
}
