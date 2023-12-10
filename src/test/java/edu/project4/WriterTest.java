package edu.project4;

import edu.project4.writer.ImageFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriterTest {
    @Test
    public void testCreateWithValidInput() {
        String testFilename = "testImage";
        int n = 10;
        int eqCount = 3;
        int iter = 100;
        int xRes = 800;
        int yRes = 600;
        int symmetry = 5;
        String function = "disk";
        ImageFormat imageFormat = ImageFormat.PNG;

        assertDoesNotThrow(() -> {
            FractalFlame.create(n, eqCount, iter, xRes, yRes, symmetry, function, testFilename, imageFormat);
            Path filePath = Path.of(testFilename + "." + imageFormat.name().toLowerCase());
            assertTrue(Files.exists(filePath));
        });

        try {
            Files.deleteIfExists(Path.of(testFilename + "." + ImageFormat.PNG.name().toLowerCase()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
