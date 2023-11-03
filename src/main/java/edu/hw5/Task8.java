package edu.hw5;

import java.util.regex.Pattern;

final class Task8 {

    private Task8() {

    }

    public static boolean isStringUneven(String txt) {
        Pattern pattern = Pattern.compile("^([01]{2})*[01]$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isOneEvenOrZeroUneven(String txt) {
        Pattern pattern = Pattern.compile("^[0]([01]{2})*$|^[1]([01]{2})*[01]$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isZeroCountMultipleOfThree(String txt) {
        Pattern pattern = Pattern.compile("^((1*0){3})+$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isNotBinaryThreeOrSeven(String txt) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isEveryUnevenIsOne(String txt) {
        Pattern pattern = Pattern.compile("^((10)|(11)|1)*$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isTwoOrMore0AndOneOrZero1(String txt) {
        Pattern pattern = Pattern.compile("^(?=.*0.*0)(?!.*1.*1)[01]*$");
        return pattern.matcher(txt).matches();
    }

    public static boolean isNoConsecutiveOne(String txt) {
        Pattern pattern = Pattern.compile("^(?!.*11)[01]*$");
        return pattern.matcher(txt).matches();
    }

}
