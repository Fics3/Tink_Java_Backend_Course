package edu.hw2;

public class Task1 {
    public sealed interface Expr {

        double evaluate();

        record Constant(double value) implements Expr {
            @Override
            public double evaluate() {
                return value;
            }

        }

        record Negate(Expr expr) implements Expr {
            @Override
            public double evaluate() {
                return -expr.evaluate();
            }

        }

        record Addition(Expr sum1, Expr sum2) implements Expr {

            @Override
            public double evaluate() {
                return sum1.evaluate() + sum2.evaluate();
            }
        }

        record Multiplication(Expr multipliable, Expr multiplier) implements Expr {
            @Override
            public double evaluate() {
                return multipliable.evaluate() * multiplier.evaluate();
            }
        }

        record Exponent(Expr baseNum, int exponent) implements Expr {

            @Override
            public double evaluate() {
                return Math.pow(baseNum.evaluate(), exponent);
            }
        }
    }
}
