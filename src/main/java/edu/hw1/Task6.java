package edu.hw1;

final class Task6 {

    private static final int KAPREKAR_CONST = 6174;
    private static final int LOWER_BOUND = 1000;
    private static final int HIGHER_BOUND = 9999;

    private Task6() {

    }

    public static int countK(int value) {
        int count = 0;
        return switch (value) {
            case KAPREKAR_CONST -> count;
            case LOWER_BOUND, HIGHER_BOUND -> -1;
            default -> countKaprekarConst(value, count);
        };
    }

    private static int countKaprekarConst(int value, int count) {
        int nowCount = count;
        if (value == KAPREKAR_CONST) {
            return count;
        }
        String valueStr = String.valueOf(value);

        int maxSortedValue = sortMax(valueStr.toCharArray());
        int minSortedValue = sortMin(valueStr.toCharArray());

        int res = maxSortedValue - minSortedValue;
        if (res == 0) {
            return -1;
        }
        nowCount++;

        return countKaprekarConst(res, nowCount);
    }

    private static int sortMin(char[] val) {

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

    private static int sortMax(char[] val) {
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

}
