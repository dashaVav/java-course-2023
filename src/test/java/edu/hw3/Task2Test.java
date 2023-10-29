package edu.hw3;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("validData")
    void validDataClusterize(String input, List<String> expected) {
        assertEquals(expected, Task2.clusterize(input));
    }

    static Stream<Arguments> validData() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {")(", "())", "(()(()", "(()()()", "())()("})
    void invalidDataClusterize(String input) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(input));
    }
}
