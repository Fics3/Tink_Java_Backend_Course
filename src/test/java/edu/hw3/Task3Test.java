package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static edu.hw3.Task3.freqDict;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Test frequency count with strings")
    public void freqDict_shouldMakeFreqDictionaryWithStrings() {
        // Arrange
        List<Object> input = List.of("a", "bb", "a", "bb");
        Map<Object, Integer> expected = new HashMap<>();
        expected.put("a", 2);
        expected.put("bb", 2);

        // Act
        Map<Object, Integer> result = freqDict(input);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test frequency count with Integers")
    public void freqDict_shouldMakeFreqDictWithIntegers() {
        // Arrange
        List<Object> input = List.of(1, 1, 2, 2);
        Map<Object, Integer> expected = new HashMap<>();
        expected.put(1, 2);
        expected.put(2, 2);

        // Act
        Map<Object, Integer> result = freqDict(input);

        // Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test frequency count with mixed input types")
    public void freqDict_shouldMakeFreqDictWithMixedTypes() {
        // Arrange
        List<Object> input = List.of("a", "bb", 1, "bb", 1);
        Map<Object, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("bb", 2);
        expected.put(1, 2);

        // Act
        Map<Object, Integer> result = freqDict(input);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test with empty input")
    public void freqDict_shouldHandleEmptyInput() {
        // Arrange
        List<Object> input = List.of();
        Map<Object, Integer> expected = new HashMap<>();

        // Act
        Map<Object, Integer> result = freqDict(input);

        // Assert

        assertThat(result).isEqualTo(expected);
    }
}
