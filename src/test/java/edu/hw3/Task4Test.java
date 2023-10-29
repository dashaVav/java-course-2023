package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @ParameterizedTest
    @CsvSource({
        "1, I",
        "2, II",
        "4, IV",
        "9, IX",
        "10, X",
        "40, XL",
        "50, L",
        "90, XC",
        "100, C",
        "400, CD",
        "500, D",
        "900, CM",
        "1000, M",
        "12, XII",
        "17, XVII",
        "19, XIX",
        "25, XXV",
        "30, XXX",
        "3999, MMMCMXCIX",
    })
    public void testConvertToRoman(int input, String expected) {
        assertEquals(expected, Task4.convertToRoman(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 4000, 5000})
    void invalidDataClusterize(int input) {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(input));
    }
}
