package edu.hw1;

final class Task7 {

    private Task7() {

    }

    public static int rotateLeft(int value, int shift) {
        int countBits = countBits(value);
        int res = value;
        if (value >= 0 && shift > 0) {
            for (int i = 0; i < shift; i++) {
                int maxBit = (res >> (countBits - 1)) & 1;
                res = ((res << 1) | maxBit) & ((1 << countBits) - 1);
            }
        } else {
            res = -1;
        }
        return res;
    }

    public static int rotateRight(int value, int shift) {
        int countBits = countBits(value);
        int res = value;
        if (value >= 0 && shift > 0) {
            for (int i = 0; i < shift; i++) {
                int lowBit = res & 1;
                res = (res >> 1) | (lowBit << (countBits - 1));
            }
        } else {
            res = -1;
        }
        return res;
    }

    private static int countBits(int value) {
        int i = 1;
        int count = 0;
        while (i <= value && i != 0) {
            i *= 2;
            count++;
        }
        if (i == 0) {
            count--;
        }
        return count;
    }
}
