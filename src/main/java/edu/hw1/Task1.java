package edu.hw1;

final class Task1 {

    private Task1() {

    }

    private static final int CONST_SEC = 60;

    public static int minutesToSeconds(String time) {
        int sec = 0;
        String[] tmp = time.split(":");
        try {
            if (tmp.length == 2) {
                int minute = Integer.parseInt(tmp[0]);
                sec = Integer.parseInt(tmp[1]);
                if (sec >= CONST_SEC || sec < 0 || minute < 0) {
                    return -1;
                }
                sec += minute * CONST_SEC;
                if (sec < 0) {
                    return -1;
                }
            }
            return sec;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
