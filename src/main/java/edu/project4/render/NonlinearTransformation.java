package edu.project4.render;

import edu.project4.render.transformation.DiskTransformation;
import edu.project4.render.transformation.HeartTransformation;
import edu.project4.render.transformation.LinearTransformation;
import edu.project4.render.transformation.PolarTransformation;
import edu.project4.render.transformation.SinusoidalTransformation;
import edu.project4.render.transformation.SphericalTransformation;
import edu.project4.render.transformation.SpiralTransformation;

public final class NonlinearTransformation {
    private NonlinearTransformation() {
    }

    public static Point transformXY(String func, double x, double y) {
        Point point = new Point(x, y);
        Point newPoint;
        switch (func) {
            case "linear" -> {
                LinearTransformation linearTransformation = new LinearTransformation();
                newPoint = linearTransformation.transform(point);
            }
            case "sinusoidal" -> {
                SinusoidalTransformation sinusoidalTransformation = new SinusoidalTransformation();
                newPoint = sinusoidalTransformation.transform(point);
            }
            case "spherical" -> {
                SphericalTransformation sphericalTransformation = new SphericalTransformation();
                newPoint = sphericalTransformation.transform(point);
            }
            case "polar" -> {
                PolarTransformation polarTransformation = new PolarTransformation();
                newPoint = polarTransformation.transform(point);
            }
            case "heart" -> {
                HeartTransformation heartTransformation = new HeartTransformation();
                newPoint = heartTransformation.transform(point);
            }
            case "disk" -> {
                DiskTransformation diskTransformation = new DiskTransformation();
                newPoint = diskTransformation.transform(point);
            }
            case "spiral" -> {
                SpiralTransformation spiralTransformation = new SpiralTransformation();
                newPoint = spiralTransformation.transform(point);
            }
            default -> throw new RuntimeException("Incorrect nonlinear function");
        }
        return newPoint;
    }
}
