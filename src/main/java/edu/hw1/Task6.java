package edu.hw1;

final class Task6 {

    private static final int KAPREKAR_CONST = 6174;
    private static final int LOWER_BOUND = 1000;
    private static final int HIGHER_BOUND = 9999;

    private static int count = 0;

    private Task6() {

    }

    public static int countK(int value) {
        if (value == KAPREKAR_CONST) {
            return count;
        } else if (value < LOWER_BOUND || value > HIGHER_BOUND) {
            return -1;
        } else {
            return countKaprekarConst(value);
        }
    }

    public static int countKaprekarConst(int value) {
        if (value == KAPREKAR_CONST) {
            return count;
        }
        String valueStr = String.valueOf(value);

        int maxSortedValue = sortMax(valueStr.toCharArray());
        int minSortedValue = sortMin(valueStr.toCharArray());

        int res = maxSortedValue - minSortedValue;
        count++;

        countKaprekarConst(res);
        return count;
    }

    public static int sortMin(char[] val) {

        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val.length; j++) {
                if (val[i] < val[j]) {
                    char tmp = val[j];
                    val[j] = val[i];
                    val[i] = tmp;
                }
            }
        }
        return Integer.parseInt(String.valueOf(val));
    }

    public static int sortMax(char[] val) {
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val.length; j++) {
                if (val[i] > val[j]) {
                    char tmp = val[j];
                    val[j] = val[i];
                    val[i] = tmp;
                }
            }
        }
        return Integer.parseInt(String.valueOf(val));
    }

    public static void resetCount() {
        count = 0;
    }

}
