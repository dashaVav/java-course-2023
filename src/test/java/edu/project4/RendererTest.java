package edu.project4;

import edu.project4.render.Coefficient;
import edu.project4.render.MultiThreadedRenderer;
import edu.project4.render.SingleThreadedRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RendererTest {
    @Test
    public void testMultiThreadedRenderer() {
        MultiThreadedRenderer renderer = new MultiThreadedRenderer();
        int n = 10;
        int eqCount = 3;
        int iter = 100;
        int xRes = 800;
        int yRes = 600;
        int symmetry = 5;
        String function = "disk";
        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
        Pixel[][] pixels = Pixel.generatePixels(xRes, yRes);

        Pixel[][] result = renderer.render(n, eqCount, iter, xRes, yRes, coefficients, pixels, symmetry, function);
        assertNotNull(result);
    }

    @Test
    public void testSingleThreadedRenderer() {
        SingleThreadedRenderer renderer = new SingleThreadedRenderer();
        int n = 10;
        int eqCount = 3;
        int iter = 100;
        int xRes = 800;
        int yRes = 600;
        int symmetry = 5;
        String function = "disk";
        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
        Pixel[][] pixels = Pixel.generatePixels(xRes, yRes);

        Pixel[][] result = renderer.render(n, eqCount, iter, xRes, yRes, coefficients, pixels, symmetry, function);
        assertNotNull(result);
    }
}
