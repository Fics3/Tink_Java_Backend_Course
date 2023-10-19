package edu.hw3;

final class Task1 {
    private Task1() {

    }

    public static String atbash(String messageForEncode) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < messageForEncode.length(); i++) {

            char currentChar = messageForEncode.charAt(i);
            if (Character.isLetter(currentChar)) {
                char mirroredChar;

                if (Character.isUpperCase(currentChar)) {
                    mirroredChar = (char) ('Z' - (currentChar - 'A'));
                } else {
                    mirroredChar = (char) ('z' - (currentChar - 'a'));
                }

                result.append(mirroredChar);
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

}

