package edu.hw3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @ParameterizedTest
    @MethodSource("generateData") <T> void freqDict(List<T> input, Map<T, Integer> expected) {
        assertEquals(expected, Task3.freqDict(input));
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.ofEntries(
                entry("a", 2),
                entry("bb", 2)
            )),
            Arguments.of(List.of("this", "and", "that", "and"), Map.ofEntries(
                entry("that", 1),
                entry("and", 2),
                entry("this", 1)
            )),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.ofEntries(
                entry("код", 3),
                entry("bug", 1)
            )),
            Arguments.of(List.of(1, 1, 2, 2), Map.ofEntries(
                entry(1, 2),
                entry(2, 2)
            ))
        );
    }
}
