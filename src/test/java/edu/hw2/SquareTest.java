package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SquareTest {
    @Test
    void squareAreaInitialSizes() {
        Square square = new Square(10);

        assertThat(square.area()).isEqualTo(100);
    }

    @Test
    void squareAreaSetWidth() {
        Square square = new Square(10);
        Rectangle newRectangle = square.setWidth(30);

        assertThat(newRectangle.area()).isEqualTo(300);
    }

    @Test
    void squareAreaSetHeight() {
        Square square = new Square(10);
        Rectangle newRectangle = square.setHeight(30);

        assertThat(newRectangle.area()).isEqualTo(300);
    }

    @Test
    void squareAreaSetSides() {
        Square square = new Square(10);
        Square newSquare = square.setSides(20);

        assertThat(newSquare.area()).isEqualTo(400);
    }

}
