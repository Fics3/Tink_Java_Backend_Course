package edu.hw1;

final class Task3 {
    private Task3() {

    }

    public static boolean isNestable(int[] firstMas, int[] secMas) {
        boolean res = true;
        try {
            if (findMin(firstMas) <= findMin(secMas)) {
                res = false;
            }
            if (findMax(firstMas) >= findMax(secMas)) {
                res = false;
            }
            return res;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int findMin(int[] mas) {
        int min = mas[0];
        for (int ma : mas) {
            if (min > ma) {
                min = ma;
            }
        }
        return min;
    }

    public static int findMax(int[] mas) {
        int max = mas[0];
        for (int ma : mas) {
            if (max < ma) {
                max = ma;
            }
        }
        return max;
    }


}
