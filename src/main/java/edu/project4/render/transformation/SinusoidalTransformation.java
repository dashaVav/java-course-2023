package edu.project4.render.transformation;

import edu.project4.render.Point;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
