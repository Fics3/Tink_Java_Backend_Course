package edu.hw3.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class NullComparatorTest {

    String nullString = null;
    String string = "test";

    @Test
    @DisplayName("Both strings null")
    void compare_shouldReturnZeroWithTwoNulls() {
        //Arrange
        NullComparator nullComparator = new NullComparator();

        //Act
        int res = nullComparator.compare(nullString, nullString);

        //Assert
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("First string is null")
    void compare_shouldReturnOneWhenFirstStrNull() {
        //Arrange
        NullComparator nullComparator = new NullComparator();

        //Act
        int res = nullComparator.compare(nullString, string);

        //Assert
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Second string is null")
    void compare_shouldReturnMinusOneWhenSecondStrNull() {
        //Arrange
        NullComparator nullComparator = new NullComparator();

        //Act
        int res = nullComparator.compare(string, nullString);

        //Assert
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Both strings not null and not equals")
    void compare_shouldReturnNotZeroWhenStrsNotNullAndNotEquals() {
        //Arrange
        NullComparator nullComparator = new NullComparator();
        String string1 = "test";
        String string2 = "test1";

        //Act
        int res = nullComparator.compare(string1, string2);

        //Assert
        assertThat(res).isNotEqualTo(0);
    }

    @Test
    @DisplayName("Tree map should find null key(Test from task)")
    void compare_shouldFindNullKeyInTreeMap() {
        //Arrange
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());

        //Act
        tree.put(null, "test");

        //Arrange
        assertThat(tree.containsKey(null)).isEqualTo(true);
    }
}
