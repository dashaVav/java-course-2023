package edu.project4;

import edu.project4.render.Coefficient;
import edu.project4.render.Correction;
import edu.project4.render.MultiThreadedRenderer;
import edu.project4.writer.ImageFormat;
import edu.project4.writer.Writer;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
//        int n = 100000;
//        int eqCount = 6;
//        int it = 13;
//        int xRes = 1920;
//        int yRes = 1080;
//        int symmetry = 5;
//
//        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
//        Pixel[][] pixels = new Pixel[xRes][yRes];
//
//        for (int i = 0; i < xRes; i++) {
//            for (int j = 0; j < yRes; j++) {
//                pixels[i][j] = new Pixel();
//            }
//        }
//        var start = System.nanoTime();
//
//
//        Pixel[][] r = new MultiThreadedRenderer().render(n, eqCount, it, xRes, yRes, coefficients, pixels, symmetry, "linear");
//        Pixel[][] r2 = Correction.correct(xRes, yRes, r);
//        Writer.writeImage(r2, "fractal_flame");
//        var end = System.nanoTime();
//        System.out.println((end - start)/100000);
    }
}
