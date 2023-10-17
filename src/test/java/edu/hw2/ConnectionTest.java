package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConnectionTest {
    @Test
    public void testStableConnectionExecute() {
        assertDoesNotThrow(() -> (new Connection.StableConnection()).execute("Test command"));
    }

    @Test
    public void testStableConnectionClose() {
        Connection connection = new Connection.StableConnection();
        assertDoesNotThrow(connection::close);
    }

    @Test
    public void testFaultyConnectionClose() {
        Connection connection = new Connection.FaultyConnection();
        assertDoesNotThrow(connection::close);
    }
}
