package edu.project4.render.transformation;

import edu.project4.render.Point;

public class DiskTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double sqrt = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double r = sqrt * Math.PI;
        double theta = Math.atan2(point.y(), point.x()) / Math.PI;
        return new Point(theta * Math.sin(r), theta * Math.cos(r));
    }
}
