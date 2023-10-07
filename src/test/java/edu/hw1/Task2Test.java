package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task2.countDigits;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    public void posTest() {
        assertEquals(3, countDigits(228));
        assertEquals(6, countDigits(123645));
    }

    @Test
    public void zeroTest() {
        assertEquals(1, countDigits(0));
    }

    @Test
    public void negTest() {
        assertEquals(2, countDigits(-25));
    }

    @Test
    public void intMaxMinTest() {
        assertEquals(10, countDigits(Integer.MIN_VALUE));
        assertEquals(10, countDigits(Integer.MAX_VALUE));
    }

}
