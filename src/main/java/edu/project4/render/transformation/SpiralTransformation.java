package edu.project4.render.transformation;

import edu.project4.render.Point;

public class SpiralTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double sqrt = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        return new Point(
            (1.0 / sqrt) * (Math.cos(theta) + Math.sin(sqrt)),
            (1.0 / sqrt) * (Math.sin(theta) - Math.cos(sqrt))
        );
    }
}
