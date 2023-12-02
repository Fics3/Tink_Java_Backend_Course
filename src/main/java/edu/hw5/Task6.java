package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Task6 {

    private Task6() {

    }

    public static boolean isSubsequence(String mainSequence, String subSequence) {
        StringBuilder regexBuilder = new StringBuilder();
        for (char c : subSequence.toCharArray()) {
            regexBuilder.append(".*").append(Pattern.quote(String.valueOf(c)));
        }
        regexBuilder.append(".*");

        String regex = regexBuilder.toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mainSequence);

        return matcher.find();
    }

}
