package edu.project4.render.transformation;

import edu.project4.render.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double r = 1.0 / (point.x() * point.x() + point.y() * point.y());
        return new Point(r * point.x(), r * point.y());
    }
}
