package edu.hw1;

final class Task2 {

    private Task2() {

    }

    private static final int MAX_INT_DIGIT_COUNT = 10;

    public static int countDigits(int value) {
        return switch (value) {
            case 0 -> 1;
            case Integer.MIN_VALUE -> MAX_INT_DIGIT_COUNT;
            default -> (int) Math.log10(Math.abs(value)) + 1;
        };
    }

}
