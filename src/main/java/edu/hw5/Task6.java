package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Task6 {

    private Task6() {

    }

    public static boolean isSubsequence(String mainSequence, String substring) {
        Pattern pattern = Pattern.compile(".*" + substring + ".*");
        Matcher matcher = pattern.matcher(mainSequence);
        return matcher.matches();
    }

}
