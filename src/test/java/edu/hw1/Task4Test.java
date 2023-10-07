package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task4.fixString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task4Test {

    @Test
    public void evenStringLengthTest() {
        assertEquals("This is a mixed up string.", fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals("bababa", fixString("ababab"));

    }

    @Test
    public void unevenStringLengthTest() {
        assertEquals("abcde", fixString("badce"));
        assertEquals("bebeb", fixString("ebebb"));
    }

    @Test
    public void nullTest() {
        assertNull(fixString(null));
    }

    @Test
    public void emptyStringTest() {
        assertEquals("", fixString(""));
    }

    @Test
    public void oneCharTest() {
        assertEquals(")", fixString(")"));
    }

}
