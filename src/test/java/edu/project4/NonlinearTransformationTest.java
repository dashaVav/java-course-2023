package edu.project4;

import edu.project4.render.NonlinearTransformation;
import edu.project4.render.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NonlinearTransformationTest {
    @Test
    public void testLinearTransformation() {
        Point result = NonlinearTransformation.transformXY("linear", 2.0, -3.0);
        assertEquals(new Point(2.0, -3.0), result);
    }

    @Test
    public void testUnknownFunction() {
        assertThrows(RuntimeException.class, () -> NonlinearTransformation.transformXY("unknownFunction", 1.0, 1.0));
    }
}
