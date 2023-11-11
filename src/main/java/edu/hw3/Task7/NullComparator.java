package edu.hw3.Task7;

import java.util.Comparator;

public class NullComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int res;
        if (s2 == null
            && s1 == null) { //Был вариант сделать через свитч, но там код становился неудобным и плохочитаемым
            res = 0;
        } else if (s1 == null) {
            res = 1;
        } else if (s2 == null) {
            res = -1;
        } else {
            res = s1.compareTo(s2);
        }
        return res;
    }

}
