package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.hw1.Task3.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Test for nestable arrays (should return true)")
    void isNestable_trueCase() {
        //arrange
        int[] outerArray1 = {1, 2, 3, 4};
        int[] innerArray1 = {0, 6};

        int[] outerArray2 = {3, 1};
        int[] innerArray2 = {4, 0};

        //act
        boolean result1 = isNestable(outerArray1, innerArray1);
        boolean result2 = isNestable(outerArray2, innerArray2);

        //assert
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

    @Test
    @DisplayName("Test for non-nestable arrays (should return false)")
    void testNonNestableArrays_falseCase() {
        //arrange
        int[] outerArray1 = {9, 9, 8};
        int[] innerArray1 = {8, 9};

        int[] outerArray2 = {1, 2, 3, 4};
        int[] innerArray2 = {2, 3};

        //act
        boolean result1 = isNestable(outerArray1, innerArray1);
        boolean result2 = isNestable(outerArray2, innerArray2);

        //assert
        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
    }

    @Test
    @DisplayName("Test for non-nestable same arrays (should return false)")
    void isNestable_sameArraysCase() {
        //arrange
        int[] sameArray = {1, 2, 3};

        //act
        boolean result = isNestable(sameArray, sameArray);

        //assert
        assertThat(result).isFalse();

    }

    @Test
    @DisplayName("Test for empty arrays (should return false)")
    void testEmptyArrays_false() {
        //arrange
        int[] emptyArray = {};
        int[] nonEmptyArray = {1, 2, 3};

        //act
        boolean result1 = isNestable(emptyArray, emptyArray);
        boolean result2 = isNestable(nonEmptyArray, emptyArray);
        boolean result3 = isNestable(emptyArray, nonEmptyArray);

        //assert
        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
    }

}
