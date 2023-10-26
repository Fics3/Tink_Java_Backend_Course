package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.hw4.Task6.findHeaviestAnimalEachType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("Should check that return map size equals to count of types animal in list")
    void findHeaviestAnimalEachType_shouldCheckMapSizeEqualsTotalTypesCountInList() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 15, false)
        );

        //Act
        var result = findHeaviestAnimalEachType(animalList);

        //Assert
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should find find Heaviest Animal of each type in list with different weights")
    void findHeaviestAnimalEachType_shouldFindHeaviestAnimalEachTypeInListWithDifferentWeight() {
        //Arrange
        Animal heaviestCatExpected = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 10, false);
        List<Animal> animalList = List.of(
            heaviestCatExpected,
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 15, false)
        );

        //Act
        var result = findHeaviestAnimalEachType(animalList);

        //Assert
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(heaviestCatExpected);
    }

    @Test
    @DisplayName("should return map with null key and value")
    void findHeaviestAnimalEachType_shouldReturnMapWithNullKeyAndValue() {
        //Arrange
        List<Animal> animalList = List.of(

        );

        //Act
        var result = findHeaviestAnimalEachType(animalList);

        //Assert
        assertThat(result.isEmpty()).isTrue();
    }

}
