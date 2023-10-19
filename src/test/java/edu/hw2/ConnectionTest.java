package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.StableConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConnectionTest {
    @Test
    public void testStableConnectionExecute() {
        assertDoesNotThrow(() -> (new StableConnection()).execute("Test command"));
    }

    @Test
    public void testStableConnectionClose() {
        Connection connection = new StableConnection();
        assertDoesNotThrow(connection::close);
    }

    @Test
    public void testFaultyConnectionClose() {
        Connection connection = new FaultyConnection();
        assertDoesNotThrow(connection::close);
    }
}
