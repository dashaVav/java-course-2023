package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @ParameterizedTest
    @CsvSource({
        "8, 1, 4",
        "10, 0, 10"
    })
    public void testRotateRightValidData(int n, int shift, int expectedResult) {
        int actualResult = Task7.rotateRight(n, shift);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "10, 0, 10"
    })
    public void testRotateLeftValidData(int n, int shift, int expectedResult) {
        int actualResult = Task7.rotateLeft(n, shift);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testRotateRightIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(-100, 1));
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateRight(100, -1));
    }

    @Test
    public void testRotateLeftIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(-100, 1));
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(100, -1));
    }
}
