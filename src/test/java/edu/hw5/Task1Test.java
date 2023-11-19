package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    void testCalculateAverageTwoTimesCorrectDuration() {
        String[] testData = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        Duration expectedDuration = Duration.ofHours(3).plusMinutes(40);
        Duration actualDuration = Task1.calculateAverageTime(testData);

        assertEquals(expectedDuration, actualDuration);
    }

    @Test
    void calculateAverageTimeSingleTimeCorrectDuration() {
        String[] singleEntryData = {"2022-03-12, 20:20 - 2022-03-12, 23:50"};
        Duration actualDuration = Task1.calculateAverageTime(singleEntryData);

        assertEquals(Duration.ofHours(3).plusMinutes(30), actualDuration);
    }

    @Test
    void calculateAverageTimeZeroForEmptyData() {
        String[] emptyData = {};
        Duration actualDuration = Task1.calculateAverageTime(emptyData);

        assertEquals(Duration.ZERO, actualDuration);
    }

    @Test
    void calculateAverageTimeThreeTimesCorrectDuration() {
        String[] differentFormatsData = {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-05-03, 10:10 - 2022-05-03, 15:45"
        };

        Duration expectedDuration = Duration.ofMinutes(775).dividedBy(3);
        Duration actualDuration = Task1.calculateAverageTime(differentFormatsData);

        assertEquals(expectedDuration, actualDuration);
    }

    @Test
    void calculateAverageTimeInvalidData() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task1.calculateAverageTime(new String[] {"2022-03-12, 20:20 - 2022-03-12, 23:88"}));

        assertThrows(
            IllegalArgumentException.class,
            () -> Task1.calculateAverageTime(new String[] {"2022-03-12, 20:20"}));

        assertThrows(
            IllegalArgumentException.class,
            () -> Task1.calculateAverageTime(new String[] {"2022-11-31, 20:20 - 2022-03-12, 23:88"}));
    }
}
