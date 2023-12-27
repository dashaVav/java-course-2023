package edu.project4.render.transformation;

import edu.project4.render.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        return point;
    }
}
