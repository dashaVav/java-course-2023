package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void testCountDigitsWithZero() {
        // given
        int number = 0;

        // when
        int actualResult = Task2.countDigits(number);

        // then
        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    void testCountDigitsWithPositiveNumber() {
        // given
        int number = 4666;

        // when
        int actualResult = Task2.countDigits(number);

        // then
        assertThat(actualResult).isEqualTo(4);
    }

    @Test
    void testCountDigitsWithNegativeNumber() {
        // given
        int number = -544;

        // when
        int actualResult = Task2.countDigits(number);

        // then
        assertThat(actualResult).isEqualTo(3);
    }

}
