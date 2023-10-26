package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task11.bitesAndHigherBoundary;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task11Test {

    @Test
    @DisplayName("Test for animals that bite and are higher than the boundary")
    void bitesAndHigherBoundary_shouldCollectToListWhenAnimalsBiteAndAreHigher() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, true)
        );

        int heightBoundary = 1;

        // Act
        var result = bitesAndHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test for animals that bite but are not higher than the boundary")
    void bitesAndHigherBoundary_shouldCollectToListWhenAnimalsBiteButNotHigher() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, true)
        );

        int heightBoundary = 150;

        // Act
        var result = bitesAndHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("Test for animals that are higher than the boundary but don't bite")
    void bitesAndHigherBoundary_shouldCollectToListWhenAnimalsAreHigherButDontBite() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, false),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        int heightBoundary = 150;

        // Act
        var result = bitesAndHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("Test for empty input")
    void bitesAndHigherBoundary_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        int heightBoundary = 100;

        // Act
        var result = bitesAndHigherBoundary(animalList, heightBoundary);

        // Assert
        assertThat(result.size()).isZero();
    }
}
