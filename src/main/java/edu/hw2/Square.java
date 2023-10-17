package edu.hw2;

public class Square extends Rectangle {
    Square(int side) {
        super(side, side);
    }

    Square setSides(int side) {
        return new Square(side);
    }

    @Override
    Rectangle setWidth(int width) {
        return super.setWidth(width);
    }

    @Override
    Rectangle setHeight(int height) {
        return super.setHeight(height);
    }
}
