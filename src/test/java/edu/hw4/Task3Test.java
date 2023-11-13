package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Task3.countAllTypes;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("should count animals by type with different types")
    void countAllTypes_shouldCountAnimalsByTypeWithDifferentTypes() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = countAllTypes(animalList);

        //Assert
        for (var entry : result.entrySet()) {
            assertThat(entry.getValue()).isOne();
        }
    }

    @Test
    @DisplayName("should count animals by type with Same types")
    void countAllTypes_shouldCountAnimalsByTypeWithSameTypes() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = countAllTypes(animalList);

        //Assert
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(2);
    }

    @Test
    @DisplayName("test for empty list")
    void countAllTypes_shouldHandleInputEmptyList() {
        //Arrange
        List<Animal> animalList = List.of();

        //Act
        var result = countAllTypes(animalList);

        //Assert
        assertThat(result.size()).isEqualTo(0);
    }
}
