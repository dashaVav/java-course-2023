package edu.project4.render;

public final class NonlinearTransformation {
    private NonlinearTransformation() {
    }

    public static Point transformXY(String func, double x, double y) {
        final double sqrt = Math.sqrt(x * x + y * y);
        Point point;
        switch (func) {
            case "linear" -> {
                point = new Point(x, y);
            }
            case "sinusoidal" -> {
                point = new Point(Math.sin(x), Math.sin(y));
            }
            case "spherical" -> {
                double r = 1.0 / (x * x + y * y);
                point = new Point(r * x, r * y);
            }
            case "polar" -> {
                point = new Point(Math.atan2(y, x) / Math.PI, sqrt - 1.0);
            }
            case "heart" -> {
                double theta = Math.atan2(y, x);
                point = new Point(sqrt * Math.sin(theta * sqrt), -sqrt * Math.cos(theta * sqrt));
            }
            case "disk" -> {
                double r = sqrt * Math.PI;
                double theta = Math.atan2(y, x) / Math.PI;
                point = new Point(theta * Math.sin(r), theta * Math.cos(r));
            }
            case "spiral" -> {
                double theta = Math.atan2(y, x);
                point = new Point(
                    (1.0 / sqrt) * (Math.cos(theta) + Math.sin(sqrt)),
                    (1.0 / sqrt) * (Math.sin(theta) - Math.cos(sqrt))
                );
            }
            default -> throw new RuntimeException("Incorrect nonlinear function");
        }
        return point;
    }
}
