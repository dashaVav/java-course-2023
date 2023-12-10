package edu.project4.writer;

import edu.project4.Pixel;
import edu.project4.writer.ImageFormat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import javax.imageio.ImageIO;

public final class Writer {
    private Writer() {
    }

    public static void writeImage(Pixel[][] pixels, String filename, ImageFormat format) {
        int xRes = pixels.length;
        int yRes = pixels[0].length;
        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < xRes; i++) {
            for (int j = 0; j < yRes; j++) {
                int rgb = ((int) pixels[i][j].red << 16) | ((int) pixels[i][j].green << 8) | (int) pixels[i][j].blue;
                image.setRGB(i, j, rgb);
            }
        }

        try {
            ImageIO.write(image,  format.name().toLowerCase(), new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
