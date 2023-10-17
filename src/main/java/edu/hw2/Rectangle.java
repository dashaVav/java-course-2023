package edu.hw2;

public class Rectangle {
    private final int width;
    private final int height;

    Rectangle(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Sides cannot be less than or equal to 0");
        }
        this.width = width;
        this.height = height;
    }

    Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    double area() {
        return width * height;
    }
}
