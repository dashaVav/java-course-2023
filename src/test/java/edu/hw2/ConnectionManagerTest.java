package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.FaultyConnectionManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionManagerTest {
    @Test
    public void testDefaultConnectionManager() {
        ConnectionManager manager = new DefaultConnectionManager();

        Connection connection = manager.getConnection();

        assertNotNull(connection);
    }

    @Test
    public void testFaultyConnectionManager() {
        ConnectionManager manager = new FaultyConnectionManager();

        Connection connection = manager.getConnection();

        assertNotNull(connection);
        assertTrue(connection instanceof FaultyConnection);
    }
}
