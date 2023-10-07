package edu.hw1;

final class Task5 {
    private Task5() {

    }

    public static boolean isPalindromeDescendant(int value) {
        if (isPalindrome(Math.abs(value))) {
            return true;
        } else {
            return createDescendentAndCheck(Math.abs(value));
        }
    }

    public static boolean isPalindrome(int value) {
        String intVal = String.valueOf(value);
        if (intVal.length() >= 2) {
            return intVal.contentEquals(new StringBuilder(intVal).reverse().toString());
        } else {
            return false;
        }
    }

    public static boolean createDescendentAndCheck(int value) {
        String strValue = String.valueOf(value);
        if (strValue.length() <= 1) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strValue.length(); i += 2) {
            if (i != strValue.length() - 1) {
                int firstNum = Character.getNumericValue(strValue.charAt(i));
                int secNum = Character.getNumericValue(strValue.charAt(i + 1));
                stringBuilder.append((firstNum + secNum));
            } else {
                stringBuilder.append(Character.getNumericValue(strValue.charAt(i)));
            }
        }
        int descendent = Integer.parseInt(String.valueOf(stringBuilder));
        if (isPalindrome(descendent)) {
            return true;
        } else {
            return createDescendentAndCheck(descendent);
        }
    }

}

