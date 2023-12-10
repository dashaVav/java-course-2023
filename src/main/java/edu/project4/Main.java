package edu.project4;

import edu.project4.writer.ImageFormat;

public class Main {
    public static void main(String[] args) {
        int n = 100000;
        int eqCount = 5;
        int iter = 10;
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 5;
        String function = "spiral";
        String filename = "fractal_flame";
        ImageFormat imageFormat = ImageFormat.PNG;
        FractalFlame.create(n, eqCount, iter, xRes, yRes, symmetry, function, filename, imageFormat);
    }
}
