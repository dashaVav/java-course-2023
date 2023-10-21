package edu.hw2.task1;

public record Negate(Expr expr) implements Expr {
    public Negate(double expr) {
        this(new Constant(expr));
    }

    @Override
    public double evaluate() {
        return -expr.evaluate();
    }
}
