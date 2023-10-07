package edu.hw1;

import org.junit.jupiter.api.Test;

import static edu.hw1.Task3.isNestable;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {

    @Test
    public void nestableTrueTest() {
        assertTrue(isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
        assertTrue(isNestable(new int[] {3, 1}, new int[] {4, 0}));
    }

    @Test
    public void nestableFalseTest() {
        assertFalse(isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
        assertFalse(isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
    }

    @Test
    public void sameArraysTest() {
        assertFalse(isNestable(new int[] {1, 2, 3}, new int[] {1, 2, 3}));
    }

    @Test
    public void emptyArraysTest() {
        assertFalse(isNestable(new int[] {}, new int[] {}));
        assertFalse(isNestable(new int[] {1, 2, 3}, new int[] {}));
        assertFalse(isNestable(new int[] {}, new int[] {1, 2, 3}));
    }

}
