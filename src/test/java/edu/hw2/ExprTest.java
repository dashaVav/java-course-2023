package edu.hw2;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Division;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Expr;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import edu.hw2.task1.Subtraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExprTest {
    private final static double DELTA = 0.000001;

    @Test
    public void testConstant() {
        var expr = new Constant(4);
        assertEquals(4, expr.evaluate(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
        "1, -1",
        "-1, 1",
        "1.5, -1.5"
    })
    public void testNegate(Double input, Double output) {
        var expr = new Negate(input);
        assertEquals(output, expr.evaluate(), DELTA);
    }

    @Test
    public void testExponent() {
        Expr expr = new Exponent(4, -2);
        assertEquals(0.0625, expr.evaluate(), DELTA);
    }

    @Test
    public void testAddition() {
        var expr = new Addition(1, 2);
        assertEquals(3, expr.evaluate(), DELTA);
    }

    @Test
    public void testMultiplication() {
        Expr expr = new Multiplication(6, 3);
        assertEquals(18, expr.evaluate(), DELTA);
    }

    @Test
    public void testDivision() {
        Expr expr = new Division(6, 2);
        assertEquals(3, expr.evaluate(), DELTA);
    }

    @Test
    public void testSubtraction() {
        Expr expr = new Subtraction(5, 2);
        assertEquals(3, expr.evaluate(), DELTA);
    }
}
