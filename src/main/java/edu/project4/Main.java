package edu.project4;

public class Main {
    public static void main(String[] args) {
        int n = 5000000;
        int eqCount = 3;
        int it = 100;
        int xRes = 1920;
        int yRes = 1080;

        Coefficient[] coefficients = OneTreadRender.generateCoefficients(eqCount);
        Pixel[][] pixels = new Pixel[xRes][yRes];

        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                pixels[i][j] = new Pixel();
            }
        }

        Pixel[][] r = OneTreadRender.render(n, eqCount, it, xRes, yRes, coefficients, pixels);
        Pixel[][] r2 = OneTreadRender.correction(xRes, yRes, r);
        Writer.writeImage(r2, "fractal_flame.png");
    }
}
