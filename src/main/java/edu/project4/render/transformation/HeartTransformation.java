package edu.project4.render.transformation;

import edu.project4.render.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double sqrt = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        return new Point(sqrt * Math.sin(theta * sqrt), -sqrt * Math.cos(theta * sqrt));
    }
}
