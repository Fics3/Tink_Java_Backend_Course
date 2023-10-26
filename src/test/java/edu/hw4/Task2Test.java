package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Task2.sortWeight;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Test for not null List animals with different weights")
    void sortWeight_shouldSortNotNullListFromMoreToLess() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)
        );

        List<Animal> expected = List.of(
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)

        );

        // Act
        List<Animal> result = sortWeight(animalList);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for not null List animals with same weights")
    void sortWeight_shouldSortNotNullListFromMoreToLessWithDifferentWeights() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 5, false)
        );

        List<Animal> expected = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 5, false)

        );

        // Act
        List<Animal> result = sortWeight(animalList);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for empty list")
    void sortWeight_shouldHandleInputOfEmptyList() {
        // Arrange
        List<Animal> animalList = List.of();

        //Act
        List<Animal> result = sortWeight(animalList);

        //Arrange
        assertThat(result).isEqualTo(animalList);
    }
}
