package edu.hw2.task1;

public record Addition(Expr first, Expr second) implements Expr {
    public Addition(Expr first, double second) {
        this(first, new Constant(second));
    }

    public Addition(double first, double second) {
        this(new Constant(first), new Constant(second));
    }

    public Addition(double first, Expr second) {
        this(new Constant(first), second);
    }

    @Override
    public double evaluate() {
        return first.evaluate() + second.evaluate();
    }
}
