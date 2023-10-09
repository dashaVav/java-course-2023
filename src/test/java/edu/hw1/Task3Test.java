package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    void testIsNestableEmptyArrays() {
        // given
        int[] a1 = {};
        int[] a2 = {};

        // when
        boolean actualResult = Task3.isNestable(a1, a2);

        // then
        assertFalse(actualResult);
    }

    @Test
    void testIsNestableNull() {
        assertThrows(NullPointerException.class, () -> Task3.isNestable(null, null));
    }

    @Test
    void testIsNestableCorrectDataSortedInDirectOrder() {
        // given
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {0, 6};

        // when
        boolean actualResult = Task3.isNestable(a1, a2);

        // then
        assertTrue(actualResult);
    }

    @Test
    void testIsNestableCorrectDataSortedInReverseOrder() {
        // given
        int[] a1 = new int[] {3, 1};
        int[] a2 = new int[] {4, 0};

        // when
        boolean actualResult = Task3.isNestable(a1, a2);

        // then
        assertTrue(actualResult);
    }

    @Test
    void testIsNestableIncorrectDataSortedInReverseOrder() {
        // given
        int[] a1 = new int[] {9, 9, 8};
        int[] a2 = new int[] {8, 9};

        // when
        boolean actualResult = Task3.isNestable(a1, a2);

        // then
        assertFalse(actualResult);
    }

    @Test
    void testIsNestableIncorrectDataSortedInDirectOrder() {
        // given
        int[] a1 = new int[] {1, 2, 3, 4};
        int[] a2 = new int[] {2, 3};

        // when
        boolean actualResult = Task3.isNestable(a1, a2);

        // then
        assertFalse(actualResult);
    }
}
