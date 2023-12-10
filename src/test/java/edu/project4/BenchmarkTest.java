package edu.project4;

import edu.project4.render.Coefficient;
import edu.project4.render.MultiThreadedRenderer;
import edu.project4.render.SingleThreadedRenderer;
import org.junit.jupiter.api.Test;

public class BenchmarkTest {
    private final static int N = 1000000;

    @Test
    void SingleThread() {
        var start = System.nanoTime();

        int eqCount = 5;
        int iter = 10;
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 5;
        String function = "spiral";
        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
        Pixel[][] pixels = Pixel.generatePixels(xRes, yRes);
        Pixel[][] pixelsForImage =
            new SingleThreadedRenderer().render(N, eqCount, iter, xRes, yRes, coefficients, pixels, symmetry, function);

        var end = System.nanoTime();
        System.out.println("SingleThreadedRenderer " + (end - start) / 1_000_000_000.0 + "s");
    }

    @Test
    void MultiThread() {
        var start = System.nanoTime();

        int n = 100000;
        int eqCount = 5;
        int iter = 10;
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 5;
        String function = "spiral";
        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
        Pixel[][] pixels = Pixel.generatePixels(xRes, yRes);
        Pixel[][] pixelsForImage =
            new MultiThreadedRenderer().render(n, eqCount, iter, xRes, yRes, coefficients, pixels, symmetry, function);

        var end = System.nanoTime();
        System.out.println("MultiThreadedRenderer " + (end - start) / 1_000_000_000.0 + "s");
    }
}
