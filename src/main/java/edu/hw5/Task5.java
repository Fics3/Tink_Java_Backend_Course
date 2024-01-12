package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Task5 {

    private Task5() {

    }

    public static boolean isValidLicensePlate(String licensePlate) {
        String validChars = "АВЕКМНОРСТУХ";
        Pattern pattern = Pattern.compile("^[" + validChars + "]{1}\\d{3}[" + validChars + "]{2}\\d{2,3}$");
        Matcher matcher = pattern.matcher(licensePlate);
        return matcher.matches();
    }

}
