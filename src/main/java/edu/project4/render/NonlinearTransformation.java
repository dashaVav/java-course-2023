package edu.project4.render;

public final class NonlinearTransformation {
    private NonlinearTransformation() {}
    public static Point transformXY(String func, double x, double y) {
        final double sqrt = Math.sqrt(x * x + y * y);
        switch (func) {
            case "linear" -> {
                return new Point(x, y);
            }
            case "sinusoidal" -> {
                return new Point(Math.sin(x), Math.sin(y));
            }
            case "spherical" -> {
                double r = 1.0 / (x * x + y * y);
                return new Point(r * x, r * y);
            }
            case "polar" -> {
                return new Point(Math.atan2(y, x) / Math.PI, sqrt - 1.0);
            }
            case "heart" -> {
                double theta = Math.atan2(y, x);
                return new Point(sqrt * Math.sin(theta * sqrt), -sqrt * Math.cos(theta * sqrt));
            }
            case "disk" -> {
                double r = sqrt * Math.PI;
                double theta = Math.atan2(y, x) / Math.PI;
                return new Point(theta * Math.sin(r), theta * Math.cos(r));
            }
            case "spiral" -> {
                double theta = Math.atan2(y, x);
                return new Point(
                    (1.0 / sqrt) * (Math.cos(theta) + Math.sin(sqrt)),
                    (1.0 / sqrt) * (Math.sin(theta) - Math.cos(sqrt))
                );
            }
            default -> throw new RuntimeException("Incorrect nonlinear function");
        }
    }
}
