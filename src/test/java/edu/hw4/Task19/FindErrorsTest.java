package edu.hw4.Task19;

import edu.hw4.Animal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Task19.FindErrors.findErrors;
import static edu.hw4.Task19.ValidationError.NAME_EMPTY;
import static edu.hw4.Task19.ValidationError.NEG_AGE;
import static edu.hw4.Task19.ValidationError.NEG_HEIGHT;
import static edu.hw4.Task19.ValidationError.NEG_WEIGHT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FindErrorsTest {

    @Test
    @DisplayName("Test for finding negative age errors in a list of animals")
    void findErrors_shouldReturnNegativeAgeErrors() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, -2, 80, 10, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        // Expected error map
        Map<String, Set<ValidationError>> expectedErrors = Map.of(
            "Cat1", Set.of(NEG_AGE),
            "Dog1", Set.of(),
            "Fish1", Set.of()
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(expectedErrors.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for finding negative weight errors in a list of animals")
    void findErrors_shouldReturnNegativeWeightErrors() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, -18, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        // Expected error map
        Map<String, Set<ValidationError>> expectedErrors = Map.of(
            "Cat1", Set.of(NEG_WEIGHT),
            "Dog1", Set.of(),
            "Fish1", Set.of()
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(expectedErrors.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for finding negative height errors in a list of animals")
    void findErrors_shouldReturnNegativeHeightErrors() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, -80, 10, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false)
        );

        // Expected error map
        var expectedErrors = Map.of(
            "Cat1", Set.of(NEG_HEIGHT),
            "Dog1", Set.of(),
            "Fish1", Set.of()
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(expectedErrors.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for finding empty name errors in a list of animals")
    void findErrors_shouldReturnEmptyNameErrors() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, 80, 10, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, 20, true),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 3, 70, 10, true)
        );

        // Expected error map
        var expectedErrors = Map.of(
            "Cat1", Set.of(),
            "Dog1", Set.of(),
            "Fish1", Set.of(),
            "", Set.of(NAME_EMPTY)
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(animalList.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for finding all errors in a list of animals")
    void findErrors_shouldReturnAllErrors() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, -80, 10, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, 5, 100, -20, true),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, -1, 5, 1, false),
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 3, 70, 10, true)
        );

        // Expected error map
        var expectedErrors = Map.of(
            "Cat1", Set.of(NEG_HEIGHT),
            "Dog1", Set.of(NEG_WEIGHT),
            "Fish1", Set.of(NEG_AGE),
            "", Set.of(NAME_EMPTY)
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(animalList.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for finding several errors of one animal")
    void findErrors_shouldReturnAllErrorsOfOneAnimal() {
        // Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 2, -80, -10, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.M, -5, 100, -20, true),
            new Animal("", Animal.Type.FISH, Animal.Sex.M, -1, 5, 1, false)
        );

        // Expected error map
        var expectedErrors = Map.of(
            "Cat1", Set.of(NEG_HEIGHT, NEG_WEIGHT),
            "Dog1", Set.of(NEG_WEIGHT, NEG_AGE),
            "", Set.of(NAME_EMPTY, NEG_AGE)
        );

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isEqualTo(animalList.size());

        for (String name : result.keySet()) {
            assertThat(result.get(name)).isEqualTo(expectedErrors.get(name));
        }
    }

    @Test
    @DisplayName("Test for empty input")
    void findErrors_shouldHandleEmptyInput() {
        // Arrange
        List<Animal> animalList = List.of();

        // Act
        var result = findErrors(animalList);

        // Assert
        assertThat(result.size()).isZero();

    }

}
