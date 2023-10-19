package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Constant test")
    @ValueSource(doubles = {1, 0, 4, -2, 32})
    void evaluateConstant_shouldReturnItself(double value) {
        // Act
        var constant = new Expr.Constant(value);
        //Assert
        assertThat(constant.evaluate()).isEqualTo(value);
    }

    @ParameterizedTest
    @DisplayName("Negate test for change numbers sign")
    @ValueSource(doubles = {1, 0, 4, -2, 32})
    void evaluateNegate_shouldReturnValueWithOppositeSign(double value) {
        // Act
        var negate = new Expr.Negate(new Expr.Constant(value));
        // Assert
        assertThat(negate.evaluate()).isEqualTo(-value);
    }

    @ParameterizedTest
    @DisplayName("Test for sum of 2 numbers")
    @CsvSource({"1,3,4", "-5,4,-1", "6,-6,0"})
    void evaluateAddition_shouldReturnSumOfToValues(double sum1, double sum2, double expected) {
        // Act
        var add = new Expr.Addition(new Expr.Constant(sum1), new Expr.Constant(sum2));
        // Assert
        assertThat(add.evaluate()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Test for multiplication of 2 numbers")
    @CsvSource({"3,3,9", "2,0,0", "-1,3,-3"})
    void evaluateMultiplication_shouldReturnMultiplicationOfTwoNumbers(
        double multipliable,
        double multiplier,
        double expected
    ) {
        // Act
        var mul = new Expr.Multiplication(new Expr.Constant(multipliable), new Expr.Constant(multiplier));
        // Assert
        assertThat(mul.evaluate()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Test for raising number into given exponent")
    @CsvSource({"3,3,27", "4,-1,0.25", "-2,2,4"})
    void evaluateExponent_shouldReturnNumberInExponent(double value, int exponent, double expected) {
        // Act
        var exp = new Expr.Exponent(new Expr.Constant(value), exponent);
        // Assert
        assertThat(exp.evaluate()).isEqualTo(expected);
    }
}
