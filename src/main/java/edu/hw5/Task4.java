package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Task4 {

    private Task4() {

    }

    public static boolean checkPasswordForSpecialSymbols(String txt) {
        Pattern pattern = Pattern.compile(".*[~!@#$%^&*|].*");
        Matcher matcher = pattern.matcher(txt);
        return matcher.matches();
    }

}

