package edu.hw3;

final class Task4 {
    private static final int MAX_AVAILABLE_ARABIC_NUM = 3999;

    private Task4() {

    }

    private static final int[] ARABIC_NUMBERS = {
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    private static final String[] ROMAN_NUMERALS = {
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public static String arabicToRoman(int value) {
        if (value <= 0 || value > MAX_AVAILABLE_ARABIC_NUM) {
            return "Error:  value should be > 0 and <4000";
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        int arabicNumber = value;
        while (arabicNumber > 0) {
            if (arabicNumber >= ARABIC_NUMBERS[i]) {
                result.append(ROMAN_NUMERALS[i]);
                arabicNumber -= ARABIC_NUMBERS[i];
            } else {
                i++;
            }
        }

        return result.toString();
    }

}
