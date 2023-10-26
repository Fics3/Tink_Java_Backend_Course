package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.hw4.Task15.findSumWeightYoungerThanBoundary;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task15Test {

    @Test @DisplayName("Test for finding total sum of animal age from 1 to boundary")
    void findSumWeightYoungerThanBoundary_shouldReturnTotalAgeSumForAnimalsInAgeRange() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, 18, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 4, 60, 15, false),
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 3, 70, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        int boundary = 4;

        // Act
        var result = findSumWeightYoungerThanBoundary(animalList, boundary);

        // Assert
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(Animal.Type.DOG)).isEqualTo(4); // Sum of Dog ages: 5
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(5); // Sum of Cat ages: 2 + 3
        assertThat(result.get(Animal.Type.FISH)).isEqualTo(1); // Sum of Fish ages: 1
    }

    @Test @DisplayName("Test for all ages higher than boundary")
    void findSumWeightYoungerThanBoundary_shouldReturnEmptyMapIfAllAgesHigherBoundary() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, 18, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 4, 60, 15, false),
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 3, 70, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        int boundary = 0;

        // Act
        var result = findSumWeightYoungerThanBoundary(animalList, boundary);

        // Assert
        assertThat(result.size()).isEqualTo(0);
    }

    @Test @DisplayName("Test for empty input") void findSumWeightYoungerThanBoundary_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        int boundary = 4;

        // Act
        var result = findSumWeightYoungerThanBoundary(animalList, boundary);

        // Assert
        assertThat(result.isEmpty()).isTrue();
    }
}
