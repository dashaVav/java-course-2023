package edu.hw3;

import java.util.stream.Stream;
import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {

    @ParameterizedTest
    @MethodSource("generateData") void parseContacts(String[] inputNames, String inputSortOrder, Contact[] expected) {
        Contact[] result = Task5.parseContacts(inputNames, inputSortOrder);
        assertArrayEquals(expected, result);
    }

    @Test
    void parseContactsEmpty() {
        String[] inputNames = new String[0];
        String inputSortOrder = "DESC";

        Contact[] result = Task5.parseContacts(inputNames, inputSortOrder);
        assertEquals(0, result.length);
    }

    @Test
    void parseContactsNull() {
        String inputSortOrder = "DESC";

        Contact[] result = Task5.parseContacts(null, inputSortOrder);
        assertEquals(0, result.length);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                new Contact[] {new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")}
            ),
            Arguments.of(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC",
                new Contact[] {new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")}
            ),
            Arguments.of(new String[] {"Paul Ainx", "Leonhard Euler", "Carl"}, "ASC",
                new Contact[] {new Contact("Paul", "Ainx"),
                    new Contact("Carl", null),
                    new Contact("Leonhard", "Euler")}
            )
        );
    }
}
