package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionManagerTest {
    @Test
    public void testDefaultConnectionManager() {
        ConnectionManager manager = new ConnectionManager.DefaultConnectionManager();

        Connection connection = manager.getConnection();

        assertNotNull(connection);
    }

    @Test
    public void testFaultyConnectionManager() {
        ConnectionManager manager = new ConnectionManager.FaultyConnectionManager();

        Connection connection = manager.getConnection();

        assertNotNull(connection);
        assertTrue(connection instanceof Connection.FaultyConnection);
    }
}
