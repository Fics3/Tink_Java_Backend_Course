package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task12.countAnimalsHigherBoundary;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task12Test {

    @Test
    @DisplayName("Test for counting animals with weight greater than height")
    void countAnimalsHigherBoundary_shouldCountAnimalsWeightHigherThanHeight() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 100, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 100, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 100, false)
        );

        // Act
        var result = countAnimalsHigherBoundary(animalList);

        // Assert
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Test for counting animals with weight equal to height")
    void countAnimalsHigherBoundary_shouldCountAnimalsWithEqualWeightAndHeight() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 25, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 30, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 5, false)
        );

        // Act
        var result = countAnimalsHigherBoundary(animalList);

        // Assert
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Test for counting animals with weight less than height")
    void countAnimalsHigherBoundary_shouldCountAnimalsWithWeightLessThanHeight() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 1, false)
        );

        // Act
        var result = countAnimalsHigherBoundary(animalList);

        // Assert
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Test for empty input")
    void countAnimalsHigherBoundary_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        // Act
        var result = countAnimalsHigherBoundary(animalList);

        // Assert
        assertThat(result).isZero();
    }
}
