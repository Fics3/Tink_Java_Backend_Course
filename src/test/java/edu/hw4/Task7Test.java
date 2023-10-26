package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Task7.findOldestAnimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Should find oldest animal in list with animal different ages")
    void findOldestAnimal_shouldFindOldestInListWithDifferentAges() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 15, false)
        );

        //Act
        var result = findOldestAnimal(animalList);

        //Assert
        assertThat(result.name()).isEqualTo("Cat1");
    }

    @Test
    @DisplayName("Should find first oldest animal in list with animal same ages")
    void findOldestAnimal_shouldFindFirstOldestInFirstWhenHaveSameAge() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );

        //Act
        var result = findOldestAnimal(animalList);

        //Assert
        assertThat(result.name()).isEqualTo("Cat");
    }

    @Test
    @DisplayName("Should throw exception if List")
    void findOldestAnimal_shouldThrowException() {
        //Arrange
        List<Animal> animalList = List.of(
        );

        //Act
        var result = findOldestAnimal(animalList);

        //Assert
        assertThat(result).isNull();

    }
}
