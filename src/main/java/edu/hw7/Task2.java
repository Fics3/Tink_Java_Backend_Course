package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

final class Task2 {

    private Task2() {

    }

    public static BigInteger parallelFactorial(int factorialValue) {
        return LongStream.rangeClosed(1, factorialValue)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
