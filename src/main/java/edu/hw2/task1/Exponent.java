package edu.hw2.task1;

public record Exponent(Expr expr, Expr power) implements Expr {
    public Exponent(Expr expr, double power) {
        this(expr, new Constant(power));
    }

    public Exponent(double expr, double power) {
        this(new Constant(expr), new Constant(power));
    }

    public Exponent(double expr, Expr power) {
        this(new Constant(expr), power);
    }

    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), power.evaluate());
    }
}
