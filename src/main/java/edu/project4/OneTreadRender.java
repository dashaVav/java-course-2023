package edu.project4;

import java.util.Random;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class OneTreadRender {

    static Random rand = new Random();

    static double X_MIN = -1.777;
    static double X_MAX = 1.777;
    static double Y_MIN = -1;
    static double Y_MAX = 1;

    static int Trunc(double value) {
        return (int) value;
    }

    static double Rand(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    static Coefficient[] generateCoefficients(int eqCount) {
        Coefficient[] coefficients = new Coefficient[eqCount];

        for (int i = 0; i < eqCount; i++) {
            coefficients[i] = new Coefficient();

            coefficients[i].a = Rand(-1.0, 1.0);
            coefficients[i].b = Rand(-1.0, 1.0);
            coefficients[i].c = Rand(-1.0, 1.0);
            coefficients[i].d = Rand(-1.0, 1.0);
            coefficients[i].e = Rand(-1.0, 1.0);
            coefficients[i].f = Rand(-1.0, 1.0);

            coefficients[i].red = Rand(0.0, 1.0);
            coefficients[i].green = Rand(0.0, 1.0);
            coefficients[i].blue = Rand(0.0, 1.0);
        }

        return coefficients;
    }

    static Pixel[][] render(
        int n,
        int eqCount,
        int it,
        int xRes,
        int yRes,
        Coefficient[] coefficients,
        Pixel[][] pixels
    ) {

        for (int num = 0; num < n; num++) {
            double newX = Rand(X_MIN, X_MAX);
            double newY = Rand(Y_MIN, Y_MAX);

            for (int step = -20; step < it; step++) {
                int i = rand.nextInt(eqCount);
                double x = coefficients[i].a * newX + coefficients[i].b * newY + coefficients[i].c;
                double y = coefficients[i].d * newX + coefficients[i].e * newY + coefficients[i].f;
                double r = x * x + y * y;
                newX = x * sin(r) - y * cos(r);
                newY = x * cos(r) + y * sin(r);

                if (step >= 0 && x >= X_MIN && x <= X_MAX && y >= Y_MIN && y <= Y_MAX) {

                    int x1 = xRes - Trunc(((X_MAX - newX) / (X_MAX - X_MIN)) * xRes);
                    int y1 = yRes - Trunc(((Y_MAX - newY) / (Y_MAX - Y_MIN)) * yRes);

                    if (x1 > -1 && x1 < xRes && y1 > -1 && y1 < yRes) {
                        if (pixels[x1][y1].counter == 0) {
                            pixels[x1][y1].red = coefficients[i].red;
                            pixels[x1][y1].green = coefficients[i].green;
                            pixels[x1][y1].blue = coefficients[i].blue;
                        } else {
                            pixels[x1][y1].red = (pixels[x1][y1].red + coefficients[i].red) / 2;
                            pixels[x1][y1].green = (pixels[x1][y1].green + coefficients[i].green) / 2;
                            pixels[x1][y1].blue = (pixels[x1][y1].blue + coefficients[i].blue) / 2;
                        }
                        pixels[x1][y1].counter++;
                    }
                }
            }
        }
        return pixels;
    }

    static Pixel[][] correction(int xRes, int yRes, Pixel[][] pixels) {
        double max = 0.0;
        double gamma = 2.2;

        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row][col].counter != 0) {
                    pixels[row][col].normal = Math.log10(pixels[row][col].counter);
                    if (pixels[row][col].normal > max) {
                        max = pixels[row][col].normal;
                    }
                }
            }
        }

        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                pixels[row][col].normal /= max;
                pixels[row][col].red = pixels[row][col].red * Math.pow(pixels[row][col].normal, (1.0 / gamma)) * 255;
                pixels[row][col].green =
                    pixels[row][col].green * Math.pow(pixels[row][col].normal, (1.0 / gamma)) * 255;
                pixels[row][col].blue = pixels[row][col].blue * Math.pow(pixels[row][col].normal, (1.0 / gamma)) * 255;
            }
        }
        return pixels;
    }
}
