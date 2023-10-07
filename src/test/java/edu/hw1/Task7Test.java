package edu.hw1;

import org.junit.jupiter.api.Test;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @Test
    public void rotateLeftEvenNumTest() {
        assertEquals(1, rotateLeft(16, 1));
        assertEquals(6, rotateLeft(12, 3));
    }

    @Test
    public void rotateLeftUnevenNumTest() {
        assertEquals(6, rotateLeft(17, 2));
        assertEquals(30, rotateLeft(29, 4));
    }

    @Test
    public void rotateRightEvenNumTest() {
        assertEquals(8, rotateRight(16, 1));
        assertEquals(9, rotateRight(12, 3));
    }

    @Test
    public void rotateRightUnevenNumTest() {
        assertEquals(12, rotateRight(17, 2));
        assertEquals(27, rotateRight(29, 4));
    }

    @Test
    public void rotateNumWithOnlyOneTest() {
        assertEquals(31, rotateLeft(31, 3));
        assertEquals(31, rotateRight(31, 3));
    }

    @Test
    public void fullCircleRotateTest() {
        assertEquals(16, rotateLeft(16, 5));
        assertEquals(16, rotateRight(16, 5));
        assertEquals(17, rotateLeft(17, 5));
        assertEquals(17, rotateRight(17, 5));
    }

    @Test
    public void negValueTest() {
        assertEquals(-1, rotateLeft(-5, 1));
        assertEquals(-1, rotateRight(-5, 1));
    }

    @Test
    public void negShiftTest() {
        assertEquals(-1, rotateLeft(4, -1));
        assertEquals(-1, rotateRight(5, -1));
    }

    @Test
    public void maxIntTest() {
        assertEquals(Integer.MAX_VALUE, rotateLeft(Integer.MAX_VALUE, 1));
        assertEquals(Integer.MAX_VALUE, rotateRight(Integer.MAX_VALUE, 1));
    }

}
