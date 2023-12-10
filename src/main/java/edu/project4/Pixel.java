package edu.project4;

public class Pixel {
    public int counter;
    public double red;
    public double green;
    public double blue;
    public double normal;

    public static Pixel[][] generatePixels(int xRes, int yRes) {
        Pixel[][] pixels = new Pixel[xRes][yRes];

        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return pixels;
    }
}
