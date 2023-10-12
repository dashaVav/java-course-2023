package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestConsoleHangman {

    @Mock Dictionary dictionary = mock(Dictionary.class);

    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("t\n".getBytes());

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(inputStreamCaptor);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testRunStartGameWithValidWordLen() {
        when(dictionary.randomWord()).thenReturn("test");
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary);

        consoleHangman.run();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid word length!"));
    }

    @Test
    void testRunStartGameWithValidWordLen1() {
        when(dictionary.randomWord()).thenReturn("longtest");
        ConsoleHangman consoleHangman = new ConsoleHangman(dictionary);

        consoleHangman.run();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Guess a letter:"));
    }

}
