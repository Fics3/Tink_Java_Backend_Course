package edu.hw3.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {

    @Test
    @DisplayName("List is empty")
    public void hasNext_shouldBeFalseWhenListIsEmpty() {
        // Arrange
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of());

        // Act and Assert
        assertThat(backwardIterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("hasNext checks single element in List")
    public void hasNext_shouldBeTrueWhenListIsNotEmpty() {
        // Arrange
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(11));

        // Act and Assert
        assertThat(backwardIterator.hasNext()).isTrue();
    }

    @Test
    @DisplayName("Backward iterate list with several elements")
    public void next_shouldBackwardIterateSeveralElementList() {
        // Arrange
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        java.util.List<Integer> expectedElements = List.of(3, 2, 1);

        // Act and Assert
        for (Integer expected : expectedElements) {
            assertThat(backwardIterator.hasNext()).isTrue();
            assertThat(backwardIterator.next()).isEqualTo(expected);
        }

        assertThat(backwardIterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("Backward iterate single element collection")
    public void next_shouldBackwardIterateSingleElementList() {
        // Arrange
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(42));

        // Act and Assert
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(42);
        assertThat(backwardIterator.hasNext()).isFalse();
    }

    @Test
    @DisplayName("Remove should throw UnsupportedOperationException")
    public void remove_shouldBackwardIterateSingleElementList() {
        // Arrange
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(42));

        // Act and Assert
        try {
            backwardIterator.remove();
        }
        catch (UnsupportedOperationException e) {
        }
    }
}
