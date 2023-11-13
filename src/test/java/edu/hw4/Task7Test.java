package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw4.Task7.findOldestAnimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("Should find oldest animal in list with animal different ages")
    @CsvSource({"3,Doh", "2,Cat", "1,Cat1"})
    void findOldestAnimal_shouldFindOldestInListWithDifferentAges(int limit, String expected) {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 15, false)
        );

        //Act
        var result = findOldestAnimal(animalList, limit);

        //Assert
        assertThat(result.name()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Should find first oldest animal in list with animal same ages")
    @CsvSource({"3,Cat", "2,Cat1", "1,Doh"})
    void findOldestAnimal_shouldFindFirstOldestInFirstWhenHaveSameAge(int limit, String expected) {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        var result = findOldestAnimal(animalList, limit);

        //Assert
        assertThat(result.name()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should throw exception if List empty")
    void findOldestAnimal_shouldThrowExceptionIfListEmpty() {
        //Arrange
        List<Animal> animalList = List.of(
        );

        //Act
        try {
            var result = findOldestAnimal(animalList, 1);

        } catch (Exception e) {
            //Assert
            assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
        }

    }

    @ParameterizedTest
    @DisplayName("Should throw exception if K zero or below size ")
    @ValueSource(ints = {0, -1})
    void findOldestAnimal_shouldThrowExceptionIfKZeroOrBelow(int k) {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        try {
            var result = findOldestAnimal(animalList, k);
        } catch (Exception e) {
            //Assert
            assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
        }

    }

    @ParameterizedTest
    @DisplayName("Should throw exception if K higher size")
    @ValueSource(ints = {4, 12})
    void findOldestAnimal_shouldThrowExceptionIfHigherSize(int k) {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        try {
            var result = findOldestAnimal(animalList, k);
        } catch (Exception e) {
            //Assert
            assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
        }

    }
}
