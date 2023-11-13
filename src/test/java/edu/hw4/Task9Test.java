package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task9.totalPawsSum;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task9Test {

    @Test
    @DisplayName("Test for eight paws animals")
    void totalPawsSum_shouldCountEightPawsAnimals() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 40, 5, 15, false)
        );
        var expected = 8 * animalList.size();

        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for four paws animals")
    void totalPawsSum_shouldCountFourPawsAnimals() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 40, 5, 15, false)
        );
        var expected = 4 * animalList.size();

        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for two paws animals")
    void totalPawsSum_shouldCountTwoPawsAnimals() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Bird1", Animal.Type.BIRD, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Bird2", Animal.Type.BIRD, Animal.Sex.M, 40, 5, 15, false)
        );
        var expected = 2 * animalList.size();

        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for animals without paws")
    void totalPawsSum_shouldCountZeroPawsAnimals() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, 40, 5, 15, false)
        );
        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Test for animals with mixed paws count")
    void totalPawsSum_shouldCountPawsAnimalsWithMixedPaws() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 40, 25, 10, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 40, 5, 15, false),
            new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 40, 5, 15, false)
        );
        var expected = 8 + 4 + 2;

        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test for empty list")
    void totalPawsSum_shouldReturnZero() {
        //Arrange
        List<Animal> animalList = List.of();

        //Act
        var result = totalPawsSum(animalList);

        //Assert
        assertThat(result).isZero();
    }
}
