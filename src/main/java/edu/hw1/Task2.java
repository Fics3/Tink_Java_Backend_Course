package edu.hw1;

final class Task2 {

    private Task2() {

    }

    private static final int MAX_INT_DIGIT_COUNT = 10;

    public static int countDigits(int value) {
        int res;
        switch (value) {
            case 0 -> {
                res = 1;
            }
            case Integer.MIN_VALUE -> {
                res = MAX_INT_DIGIT_COUNT;
            }
            default -> {
                res = (int) Math.log10(Math.abs(value)) + 1;
            }
        }
        return res;
    }

}
