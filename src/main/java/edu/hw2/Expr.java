package edu.hw2;

public sealed interface Expr {
    double evaluate();

    record Constant(double expr) implements Expr {
        @Override
        public double evaluate() {
            return expr;
        }
    }

    record Negate(Expr expr) implements Expr {
        public Negate(double expr) {
            this(new Constant(expr));
        }

        @Override
        public double evaluate() {
            return -expr.evaluate();
        }
    }

    record Exponent(Expr expr, Expr power) implements Expr {
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

    record Addition(Expr first, Expr second) implements Expr {
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

    record Multiplication(Expr first, Expr second) implements Expr {
        public Multiplication(Expr first, double second) {
            this(first, new Constant(second));
        }

        public Multiplication(double first, double second) {
            this(new Constant(first), new Constant(second));
        }

        public Multiplication(double first, Expr second) {
            this(new Constant(first), second);
        }

        @Override
        public double evaluate() {
            return first.evaluate() * second.evaluate();
        }
    }

    record Division(Expr first, Expr second) implements Expr {
        public Division(Expr first, double second) {
            this(first, new Constant(second));
        }

        public Division(double first, double second) {
            this(new Constant(first), new Constant(second));
        }

        public Division(double first, Expr second) {
            this(new Constant(first), second);
        }

        @Override
        public double evaluate() {
            return first.evaluate() / second.evaluate();
        }
    }

    record Subtraction(Expr first, Expr second) implements Expr {
        public Subtraction(Expr first, double second) {
            this(first, new Constant(second));
        }

        public Subtraction(double first, double second) {
            this(new Constant(first), new Constant(second));
        }

        public Subtraction(double first, Expr second) {
            this(new Constant(first), second);
        }

        @Override
        public double evaluate() {
            return first.evaluate() - second.evaluate();
        }
    }
}
