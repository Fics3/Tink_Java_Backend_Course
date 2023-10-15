package edu.hw1;

final class Task4 {

    private Task4() {

    }

    public static String fixString(String cursedMessage) {
        try {
            char[] ans = cursedMessage.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < ans.length; i += 2) {
                if (i != ans.length - 1) {
                    stringBuilder.append(ans[i + 1]).append(ans[i]);
                } else {
                    stringBuilder.append(ans[i]);
                }
            }

            return stringBuilder.toString();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }
}
