package edu.hw10.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CacheProxyTest {
    Path path = Path.of("src/main/resources/hw6/task2");

    @AfterEach
    void tearDown() throws IOException {
        File[] files = path.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
        Files.deleteIfExists(path);
        Files.deleteIfExists(path.getParent());
    }

    @Test
    public void testPersistingToDisk() {
        FibCalculator c = mock(FibCalculator.class);
        when(c.fib(5)).thenReturn(8L);

        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class, path);

        long result1 = proxy.fib(5);

        long result2 = proxy.fib(5);

        assertEquals(result1, result2);
        verify(c, times(1)).fib(5);
    }
}
