package edu.hw5;

import java.util.regex.Pattern;

final class Task7 {

    private Task7() {

    }

    public static boolean isThirdZeroAndLengthMoreThree(String txt) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*");
        return pattern.matcher(txt).matches();
    }

    public static boolean isStartEqualsEnd(String txt) {
        Pattern pattern = Pattern.compile("^([01])([01])*(\\1)$|^[01]$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isStringSizeBetweenOneAndThree(String txt) {
        Pattern pattern = Pattern.compile("^([01])([01]{0,2})$");
        return pattern.matcher(txt).matches();
    }

}
