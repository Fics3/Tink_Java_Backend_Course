package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task1.sortHeight;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    @DisplayName("Test for not null List animals with different heights")
    void sortHeight_shouldSortNotNullListFromLessToMore() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false)
        );

        List<Animal> expected = List.of(
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 0, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 10, true)
        );

        // Act
        List<Animal> result = sortHeight(animalList);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for not null List animals with same heights")
    void sortHeight_shouldSortNotNullListFromLessToMoreWithSameHeights() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 25, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 25, 0, false)
        );

        List<Animal> expected = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 25, 10, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 25, 0, false)
        );

        // Act
        List<Animal> result = sortHeight(animalList);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for empty list")
    void sortHeight_shouldHandleInputOfEmptyList() {
        // Arrange
        List<Animal> animalList = List.of();

        //Act
        List<Animal> result = sortHeight(animalList);

        //Arrange
        assertThat(result).isEqualTo(animalList);
    }
}
