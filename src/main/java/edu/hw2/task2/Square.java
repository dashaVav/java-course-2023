package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    public Square setSides(int side) {
        return new Square(side);
    }

    @Override
    public Rectangle setWidth(int width) {
        return super.setWidth(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return super.setHeight(height);
    }
}
