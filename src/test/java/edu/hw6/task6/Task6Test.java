package edu.hw6.task6;

import java.net.ServerSocket;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    void scanPortsReturnNonEmptyList() {
        List<String> result = Task6.scanPorts();
        assertFalse(result.isEmpty());
    }

    @Test
    void testPortIsClosed() {
        try (ServerSocket serverSocket = new ServerSocket(134)) {
            List<String> result = Task6.scanPorts();
            assertTrue(result.toString().contains("TCP      134"));

        } catch (Exception e) {
        }
    }

    @Test
    void testPortIsNotClosed() {

        List<String> result = Task6.scanPorts();
        System.out.println(result);
        assertFalse(result.toString().contains("TCP      134"));
        assertFalse(result.toString().contains("UDP      134"));

    }
}
