package edu.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task6.countK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @BeforeEach
    public void setUp() {
        Task6.resetCount();
    }

    @Test
    public void possibleKaprekarRangeTest() {
        assertEquals(5, countK(6621));
        Task6.resetCount();
        assertEquals(3, countK(1234));
    }

    @Test
    public void kaprekarConstTest() {
        assertEquals(0, countK(6174));
    }

    @Test
    public void impossibleRangBelowTest() {
        assertEquals(-1, countK(999));
    }

    @Test
    public void impossibleRangHigherTest() {
        assertEquals(-1, countK(100000));
    }

}
