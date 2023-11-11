package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw4.Task13.namesMoreThanOneWord;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task13Test {

    @Test
    @DisplayName("Test for animals with names containing more than one word")
    void namesMoreThanOneWord_shouldCollectAnimalsWithMultipleWordNames() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Don Yagon", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        // Act
        var result = namesMoreThanOneWord(animalList);

        // Assert
        assertThat(result.size()).isOne();
    }

    @Test
    @DisplayName("Test for animals with names containing one word")
    void namesMoreThanOneWord_shouldCollectAnimalsWithSingleWordNames() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Don", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        // Act
        var result = namesMoreThanOneWord(animalList);

        // Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("Test for animals with empty names")
    void namesMoreThanOneWord_shouldCollectAnimalsWithEmptyNames() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 80, 100, 200, true),
            new Animal("  ", Animal.Type.DOG, Animal.Sex.M, 5000, 400, 6000, true)
        );

        // Act
        var result = namesMoreThanOneWord(animalList);

        // Assert
        assertThat(result.size()).isZero();
    }

    @Test
    @DisplayName("Test for animals with no names")
    void namesMoreThanOneWord_shouldCollectAnimalsWithNoNames() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal(null, Animal.Type.CAT, Animal.Sex.M, 80, 100, 200, true),
            new Animal(null, Animal.Type.DOG, Animal.Sex.M, 5000, 400, 6000, true)
        );

        // Act
        var result = namesMoreThanOneWord(animalList);

        // Assert
        assertThat(result.size()).isZero();
    }

    @ParameterizedTest
    @DisplayName("Test for name with much whitespace")
    @CsvSource({"sdsdsd    ,0", "sdsdsd     ,0", "sdsd     sdsdsd,1"})
    void namesMoreThanOneWord_shouldHandleNameWithMuchWhitespace(String nameWithWhiteSpace, int expected) {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal(nameWithWhiteSpace, Animal.Type.CAT, Animal.Sex.M, 80, 100, 200, true)
        );

        //Act
        var result = namesMoreThanOneWord(animalList);

        //Assert
        assertThat(result.size()).isEqualTo(expected);

    }
}
