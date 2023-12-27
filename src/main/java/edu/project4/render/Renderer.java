package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.ThreadLocalRandom;

public interface Renderer {
    double X_MIN = -1.777;
    double X_MAX = 1.777;
    double Y_MIN = -1;
    double Y_MAX = 1;

    @SuppressWarnings("ParameterNumber")
    Pixel[][] render(
        int n, int eqCount, int iter, int xRes, int yRes, Coefficient[] coefficients,
        Pixel[][] pixels, int symmetry, String function
    );

    default double rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
