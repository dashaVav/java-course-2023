package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task8Test {
    @Test
    public void testBackwardIterator() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(Arrays.asList(3, 2, 1), result);
    }

    @Test
    public void testEmptyList() {
        List<Integer> list = new ArrayList<>();
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);

        assertFalse(iterator.hasNext());
    }
}
