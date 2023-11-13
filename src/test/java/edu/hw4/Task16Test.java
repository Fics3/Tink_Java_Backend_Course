package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.hw4.Task16.sortByTypeSexName;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task16Test {

    @Test
    @DisplayName("Test for sorting animals by type, sex, and name")
    void sortByTypeSexName_shouldReturnSortedAnimals() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, 18, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 4, 60, 15, false),
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 3, 70, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        List<Animal> expected = List.of(
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 3, 70, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, 18, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 4, 60, 15, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        // Act
        var result = sortByTypeSexName(animalList);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for sorting an empty input")
    void sortByTypeSexName_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        // Act
        var result = sortByTypeSexName(animalList);

        // Assert
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test for sorting a single animal")
    void sortByTypeSexName_shouldHandleSingleAnimal() {
        // Arrange
        Animal singleAnimal = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true);

        // Act
        var result = sortByTypeSexName(List.of(singleAnimal));

        // Assert
        assertThat(result.size()).isOne();
        assertThat(result.get(0)).isEqualTo(singleAnimal);
    }
}
