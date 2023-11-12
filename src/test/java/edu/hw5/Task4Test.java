package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @ParameterizedTest
    @ValueSource(strings = {"password~", "password!", "password@", "password#", "password$",
        "password%", "password^", "password&", "password*", "password|", "password@#", "password##"})
    public void testPasswordValidatorTrueResult(String input) {
        boolean actualResult = Task4.passwordValidator(input);
        assertTrue(actualResult);
    }

    @Test
    public void testPasswordValidatorFalseResult() {
        boolean actualResult = Task4.passwordValidator("password");
        assertFalse(actualResult);
    }
}
