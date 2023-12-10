package edu.project4.render;

import java.util.concurrent.ThreadLocalRandom;

public class Coefficient {

    public double a, b, c, d, e, f;
    public double red, green, blue;

    private static double Rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static Coefficient[] generateCoefficients(int eqCount) {
        Coefficient[] coefficients = new Coefficient[eqCount];

        for (int i = 0; i < eqCount; i++) {
            coefficients[i] = new Coefficient();

            coefficients[i].a = Rand(-1.0, 1.0);
            coefficients[i].b = Rand(-1.0, 1.0);
            coefficients[i].c = Rand(-1.0, 1.0);
            coefficients[i].d = Rand(-1.0, 1.0);
            coefficients[i].e = Rand(-1.0, 1.0);
            coefficients[i].f = Rand(-1.0, 1.0);
            coefficients[i].red = Rand(0.0, 1);
            coefficients[i].green = Rand(0.0, 1);
            coefficients[i].blue = Rand(0.0, 1);
        }
        return coefficients;
    }
}
