package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task17.isSpidersBitesMoreThanDogs;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task17Test {

    @Test
    @DisplayName("Test for checking if spiders bite more often than dogs")
    void isSpidersBitesMoreThanDogs_shouldReturnTrueIfSpidersBiteMore() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 5, 10, 2, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 2, 30, 10, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 4, 8, 2, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, false)
        );

        // Act
        var result = isSpidersBitesMoreThanDogs(animalList);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test for checking if dogs bite more often than spiders")
    void isSpidersBitesMoreThanDogs_shouldReturnFalseIfDogsBiteMore() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 5, 10, 2, false),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 2, 30, 10, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 4, 8, 2, false),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true)
        );

        // Act
        var result = isSpidersBitesMoreThanDogs(animalList);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Test for no spiders and dogs")
    void isSpidersBitesMoreThanDogs_shouldReturnFalseIfNoDogsAndSpiders() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 2, false),
            new Animal("CAT1", Animal.Type.CAT, Animal.Sex.M, 2, 30, 10, true),
            new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 4, 8, 2, false),
            new Animal("CAT1", Animal.Type.CAT, Animal.Sex.M, 3, 40, 15, true)
        );

        // Act
        var result = isSpidersBitesMoreThanDogs(animalList);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Test for empty input")
    void isSpidersBitesMoreThanDogs_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        // Act
        var result = isSpidersBitesMoreThanDogs(animalList);

        // Assert
        assertThat(result).isFalse();
    }
}
