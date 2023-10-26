package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task4.findLongestNameAnimal;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Should find animal with longest name among different size names")
    void findLongestNameAnimal_shouldFindAnimalWithLongestNameAmongDifferentSizeNames() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Don Yagon", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = findLongestNameAnimal(animalList);

        //Assert
        assertThat(result.name()).isEqualTo("Don Yagon");
    }

    @Test
    @DisplayName("Should find animal with longest name among equal size names")
    void findLongestNameAnimal_shouldFindAnimalWithLongestNameAmongEqualSizeNames() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cap", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = findLongestNameAnimal(animalList);

        //Assert
        assertThat(result.name()).isEqualTo("Cat");
    }

    @Test
    @DisplayName("Should throw exception if List empty")
    void findLongestNameAnimal_shouldThrowExceptionIfEmptyList() {
        //Arrange
        List<Animal> animalList = List.of();

        //Act
        var result = findLongestNameAnimal(animalList);

        //Assert
        assertThat(result).isNull();

    }
}
