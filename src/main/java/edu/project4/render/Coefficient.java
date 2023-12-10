package edu.project4.render;

import java.util.concurrent.ThreadLocalRandom;

public class Coefficient {

    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public double f;
    public double red, green, blue;

    private static double rand(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static Coefficient[] generateCoefficients(int eqCount) {
        Coefficient[] coefficients = new Coefficient[eqCount];

        for (int i = 0; i < eqCount; i++) {
            coefficients[i] = new Coefficient();

            coefficients[i].a = rand(-1.0, 1.0);
            coefficients[i].b = rand(-1.0, 1.0);
            coefficients[i].c = rand(-1.0, 1.0);
            coefficients[i].d = rand(-1.0, 1.0);
            coefficients[i].e = rand(-1.0, 1.0);
            coefficients[i].f = rand(-1.0, 1.0);
            coefficients[i].red = rand(0.0, 1);
            coefficients[i].green = rand(0.0, 1);
            coefficients[i].blue = rand(0.0, 1);
        }
        return coefficients;
    }
}
