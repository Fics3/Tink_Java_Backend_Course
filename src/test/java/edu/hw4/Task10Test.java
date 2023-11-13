package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.hw4.Task10.pawsNotEqualsAge;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task10Test {

    @Test
    @DisplayName("Test for all animals age not equals to paws count")
    void totalPawsSum_shouldCollectToListWhenAllAnimalsAgeNotEqualPaws() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        var result = pawsNotEqualsAge(animalList);

        //Assert
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test for all animals age equals to paws count")
    void totalPawsSum_shouldCollectToListWhenAllAnimalsAgeEqualPaws() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 4, 25, 10, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 4, 5, 15, false)
        );

        //Act
        var result = pawsNotEqualsAge(animalList);

        //Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("Test for mixed list with equals paws and age and not")
    void totalPawsSum_shouldCollectToListMixedList() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 4, 5, 15, false)
        );

        //Act
        var result = pawsNotEqualsAge(animalList);

        //Assert
        assertThat(result.size()).isOne();
    }

    @Test
    @DisplayName("Test for empty input")
    void totalPawsSum_shouldHandleEmptyInput() {
        //Arrange
        List<Animal> animalList = List.of();

        //Act
        var result = pawsNotEqualsAge(animalList);

        //Assert
        assertThat(result.size()).isZero();
    }
}
