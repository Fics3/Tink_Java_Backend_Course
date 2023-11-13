package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task8.findHeaviestBelowHeight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    @DisplayName("Should find animal if exists below height Boundary")
    void findHeaviestBelowHeight_shouldFindHeaviestIfBelowBoundaryExists() {
        //Arrange
        int heightBoundary = 27;
        Animal heaviestAnimalBelowBoundary = new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false);
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            heaviestAnimalBelowBoundary
        );

        //Act
        var result = findHeaviestBelowHeight(animalList, heightBoundary);

        //Assert
        assertThat(result.get().weight()).isEqualTo(heaviestAnimalBelowBoundary.weight());
    }

    @Test
    @DisplayName("Should find first animal if exists below height Boundary and list have animals with same weights ")
    void findHeaviestBelowHeight_shouldFindFirstHeaviestIfBelowBoundaryExistsAndHaveSameWeights() {
        //Arrange
        int heightBoundary = 27;

        Animal firstHeaviestAnimalBelowBoundary = new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 5, 15, true);
        Animal secondHeaviestAnimalBelowBoundary = new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false);

        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 30, 10, false),
            firstHeaviestAnimalBelowBoundary,
            secondHeaviestAnimalBelowBoundary
        );

        //Act
        var result = findHeaviestBelowHeight(animalList, heightBoundary);

        //Assert
        assertThat(result.get().weight()).isEqualTo(firstHeaviestAnimalBelowBoundary.weight());
        assertThat(result.get().name()).isEqualTo(firstHeaviestAnimalBelowBoundary.name());
    }

    @Test
    @DisplayName("Optional should be not present(null) if no animal below boundary")
    void findHeaviestBelowHeight_shouldReturnNotPresentOptional() {
        //Arrange
        int heightBoundary = 1;

        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        var result = findHeaviestBelowHeight(animalList, heightBoundary);

        //Assert
        assertThat(result.isPresent()).isFalse();
    }
}
