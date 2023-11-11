package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw4.Task2.sortWeight;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("Test for not null List animals with different weights and animal count equals or less limit")
    @ValueSource(ints = {3, 5})
    void sortWeight_shouldSortNotNullListFromMoreToLessAndAnimalCountEqualOrLowerLimit(int limit) {
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
        List<Animal> result = sortWeight(animalList, limit);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for not null List animals with different weights and animal count higher limit")
    void sortWeight_shouldSortNotNullListFromMoreToLessAndAnimalCountHigherLimit() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)
        );

        List<Animal> expected = List.of(
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true)
        );

        // Act
        List<Animal> result = sortWeight(animalList, 1);

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
        List<Animal> result = sortWeight(animalList, 3);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for empty list")
    void sortWeight_shouldHandleInputOfEmptyList() {
        // Arrange
        List<Animal> animalList = List.of();

        //Act
        List<Animal> result = sortWeight(animalList, 3);

        //Arrange
        assertThat(result).isEqualTo(animalList);
    }

    @Test
    @DisplayName("Test for limit zero")
    void sortWeight_shouldHandleInputLimitZero() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)
        );

        //Act
        List<Animal> result = sortWeight(animalList, 0);

        //Arrange
        assertThat(result.size()).isZero();
    }


    @ParameterizedTest
    @DisplayName("Test for limit lower than 0")
    @ValueSource(ints = {-2, -3})
    void sortWeight_shouldHandleInputLowerThanZero(int limit) {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)
        );

        //Act
        List<Animal> result = sortWeight(animalList, limit);

        //Arrange
        assertThat(result.size()).isEqualTo(Math.abs(limit));
    }
}
