package edu.hw5;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    void testFridayThirteenReturnCorrectListFor1985() {
        int year = 1925;
        ArrayList<LocalDate> expectedList = new ArrayList<>();

        expectedList.add(LocalDate.of(1925, 2, 13));
        expectedList.add(LocalDate.of(1925, 3, 13));
        expectedList.add(LocalDate.of(1925, 11, 13));

        ArrayList<LocalDate> actualList = Task2.fridayThirteen(year);

        assertEquals(expectedList, actualList);
    }

    @Test
    void testFridayThirteenReturnCorrectListFor2024() {
        int year = 2024;
        ArrayList<LocalDate> expectedList = new ArrayList<>();

        expectedList.add(LocalDate.of(2024, 9, 13));
        expectedList.add(LocalDate.of(2024, 12, 13));

        ArrayList<LocalDate> actualList = Task2.fridayThirteen(year);

        assertEquals(expectedList, actualList);
    }

    @Test
    void testFindNextFridayThirteen() {
        LocalDate currentDate = LocalDate.of(2023, 11, 12);
        LocalDate expectedDate = LocalDate.of(2024, 9, 13);

        LocalDate actualDate = Task2.findNextFridayThirteen(currentDate);

        assertEquals(expectedDate, actualDate);
    }
}

