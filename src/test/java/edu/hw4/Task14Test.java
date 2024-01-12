package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task14.hasDogHigherBoundary;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task14Test {

    @Test
    @DisplayName("Test for checking if there are dogs higher than the boundary")
    void hasDogHigherBoundary_shouldReturnTrueIfDogsHigherThanBoundary() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Don Yagon", Animal.Type.DOG, Animal.Sex.M, 10, 85, 5, false)
        );

        int heightBoundary = 70;

        // Act
        var result = hasDogHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test for checking if there are dogs higher than the boundary")
    void hasDogHigherBoundary_shouldReturnTrueIfDogsHigherEqualBoundary() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("DOg", Animal.Type.DOG, Animal.Sex.M, 30, 70, 5, false),
            new Animal("Dog", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Don Yagon", Animal.Type.DOG, Animal.Sex.M, 10, 14, 5, false)
        );

        int heightBoundary = 70;

        // Act
        var result = hasDogHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test for checking if there no dogs")
    void hasDogHigherBoundary_shouldReturnFalseIfNoDogs() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 70, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Spider Man", Animal.Type.SPIDER, Animal.Sex.M, 10, 14, 5, false)
        );

        int heightBoundary = 70;

        // Act
        var result = hasDogHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Test for checking if there are no dogs higher than the boundary")
    void hasDogHigherBoundary_shouldReturnFalseIfNoDogsHigherThanBoundary() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 60, 100, 20, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 70, 80, 18, true),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 50, 60, 15, false)
        );

        int heightBoundary = 120;

        // Act
        var result = hasDogHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Test for empty input")
    void hasDogHigherBoundary_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        int heightBoundary = 70;

        // Act
        var result = hasDogHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result).isFalse();
    }
}
