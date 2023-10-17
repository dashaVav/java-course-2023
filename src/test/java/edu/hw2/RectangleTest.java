package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    @Test
    void rectangleAreaInitialSizes() {
        Rectangle rectangle = new Rectangle(20, 10);

        assertThat(rectangle.area()).isEqualTo(200);
    }

    @Test
    void rectangleAreaSetWidth() {
        Rectangle rectangle = new Rectangle(20, 10);
        Rectangle newRectangle = rectangle.setWidth(30);

        assertThat(newRectangle.area()).isEqualTo(300);
    }

    @Test
    void rectangleAreaSetHeight() {
        Rectangle rectangle = new Rectangle(20, 10);
        Rectangle newRectangle = rectangle.setHeight(30);

        assertThat(newRectangle.area()).isEqualTo(600);
    }

    @Test
    public void rectangleConstructorIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-20, 10));
    }
}
