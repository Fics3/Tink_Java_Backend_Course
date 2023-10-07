package edu.hw1;

import org.junit.jupiter.api.Test;

import static edu.hw1.Task5.isPalindromeDescendant;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {

    @Test
    public void palindromesTest() {
        assertTrue(isPalindromeDescendant(11));
        assertTrue(isPalindromeDescendant(11211230));
    }

    @Test
    public void notPalindromeTest() {
        assertFalse(isPalindromeDescendant(12));
        assertFalse(isPalindromeDescendant(12345));
    }

    @Test
    public void oneDigitTest() {
        assertFalse(isPalindromeDescendant(5));
        assertFalse(isPalindromeDescendant(0));
    }

    @Test
    public void intMaxMinTest() {
        assertFalse(isPalindromeDescendant(Integer.MAX_VALUE));
        assertTrue(isPalindromeDescendant(Integer.MIN_VALUE));
    }


    @Test
    public void negTest() {
        assertTrue(isPalindromeDescendant(-11));
        assertTrue(isPalindromeDescendant(-11211230));

        assertFalse(isPalindromeDescendant(-12));
        assertFalse(isPalindromeDescendant(-12345));

        assertFalse(isPalindromeDescendant(-5));
    }
}
