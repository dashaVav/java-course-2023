package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "А000АА000"})
    public void testPasswordValidatorTrueResult(String input) {
        boolean actualResult = Task5.carNumberValidator(input);
        assertTrue(actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"А123АВЕ777", "А123ВГ77", "А123ВЕ7777", "Z123АА125", "Я124ЯЯ123", "123AB123"})
    public void testPasswordValidatorFalseResult(String input) {
        boolean actualResult = Task5.carNumberValidator(input);
        assertFalse(actualResult);
    }
}
