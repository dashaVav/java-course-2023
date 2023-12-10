package edu.project4;

import edu.project4.render.Coefficient;
import edu.project4.render.Correction;
import edu.project4.render.MultiThreadedRenderer;
import edu.project4.writer.ImageFormat;
import edu.project4.writer.Writer;

public final class FractalFlame {
    private FractalFlame() {}
    @SuppressWarnings("ParameterNumber")
    public static void create(
        int n,
        int eqCount,
        int iter,
        int xRes,
        int yRes,
        int symmetry,
        String function,
        String filename,
        ImageFormat imageFormat
    ) {
        Coefficient[] coefficients = Coefficient.generateCoefficients(eqCount);
        Pixel[][] pixels = Pixel.generatePixels(xRes, yRes);
        Pixel[][] pixelsForImage =
            new MultiThreadedRenderer().render(n, eqCount, iter, xRes, yRes, coefficients, pixels, symmetry, function);
        Pixel[][] pixelsForImageAfterCorrection = Correction.correct(xRes, yRes, pixelsForImage);
        Writer.writeImage(pixelsForImageAfterCorrection, filename, imageFormat);
    }
}
