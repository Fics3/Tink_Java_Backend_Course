package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task18.findHeaviestFishInSeveralLists;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task18Test {

    @Test
    @DisplayName("Test for finding the heaviest fish in several lists")
    void findHeaviestFishInSeveralLists_shouldReturnHeaviestFish() {
        // Arrange
        Animal heaviestFish = new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 4, 12, 6, true);

        List<Animal> list1 = List.of(
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 10, 2, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 8, 3, true)
        );

        List<Animal> list2 = List.of(
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, 2, 15, 5, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 3, 20, 7, false)
        );

        List<Animal> list3 = List.of(
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, false),
            heaviestFish
        );

        List<List<Animal>> animalLists = List.of(list1, list2, list3);

        // Act
        Animal result = findHeaviestFishInSeveralLists(animalLists);

        // Assert
        assertThat(result).isEqualTo(heaviestFish);
    }

    @Test
    @DisplayName("Test for finding the heaviest fish when no fish is present")
    void findHeaviestFishInSeveralLists_shouldReturnNullIfNoFish() {
        // Arrange
        List<Animal> list1 = List.of(
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 3, 20, 7, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 8, 3, true)
        );

        List<Animal> list2 = List.of(
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 1, 9, 4, false),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 2, 15, 5, true)
        );

        List<List<Animal>> animalLists = List.of(list1, list2);

        // Act
        var result = findHeaviestFishInSeveralLists(animalLists);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Test for empty input lists")
    void findHeaviestFishInSeveralLists_shouldReturnNullForEmptyInput() {
        // Arrange
        List<List<Animal>> emptyLists = new ArrayList<>();

        // Act
        var result = findHeaviestFishInSeveralLists(emptyLists);

        // Assert
        assertThat(result).isNull();
    }
}
