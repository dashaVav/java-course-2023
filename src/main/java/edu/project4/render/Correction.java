package edu.project4.render;

import edu.project4.Pixel;

public class Correction {
    private Correction() {}
    private static final int MAX_RGB = 255;
    private static final double GAMMA = 2.2;

    public static Pixel[][] correct(int xRes, int yRes, Pixel[][] pixels) {
        double max = 0.0;

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
                pixels[row][col].red =
                    pixels[row][col].red * Math.pow(pixels[row][col].normal, (1.0 / GAMMA)) * MAX_RGB;
                pixels[row][col].green =
                    pixels[row][col].green * Math.pow(pixels[row][col].normal, (1.0 / GAMMA)) * MAX_RGB;
                pixels[row][col].blue =
                    pixels[row][col].blue * Math.pow(pixels[row][col].normal, (1.0 / GAMMA)) * MAX_RGB;
            }
        }
        return pixels;
    }
}
