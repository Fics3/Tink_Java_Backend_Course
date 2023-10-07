package edu.hw1;

import org.junit.jupiter.api.Test;

import static edu.hw1.Task1.minutesToSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    public void withoutMinuteTest() {
        assertEquals(59, minutesToSeconds("00:59"));
        assertEquals(0, minutesToSeconds("00:00"));
    }

    @Test
    public void withMinuteTest() {
        assertEquals(1859, minutesToSeconds("30:59"));
        assertEquals(73892, minutesToSeconds("1231:32"));
        assertEquals(60, minutesToSeconds("01:00"));
    }

    @Test
    public void withMuchSecTest() {
        assertEquals(-1, minutesToSeconds("30:592"));
    }

    @Test
    public void withNegTest() {
        assertEquals(-1, minutesToSeconds("30:-2"));
        assertEquals(-1, minutesToSeconds("-120:01"));
    }

    @Test
    public void withCharTest() {
        assertEquals(-1, minutesToSeconds("3420:ac"));
        assertEquals(-1, minutesToSeconds("df:01"));
    }

    @Test
    public void maxMinIntTest() {
        assertEquals(-1, minutesToSeconds(Integer.MAX_VALUE + ":00"));
        assertEquals(-1, minutesToSeconds(Integer.MIN_VALUE + ":00"));
    }

}
