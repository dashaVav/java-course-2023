package edu.project4.render.transformation;

import edu.project4.render.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double sqrt = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        return new Point(Math.atan2(point.y(), point.x()) / Math.PI, sqrt - 1.0);
    }
}
