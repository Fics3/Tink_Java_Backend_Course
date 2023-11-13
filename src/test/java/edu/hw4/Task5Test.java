package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw4.Task5.checkWhichSexIsMore;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Should find dominant sex in list with different sexes")
    void checkWhichSexIsMore_shouldFindDominantSexInListWithDifferentSexes() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = checkWhichSexIsMore(animalList);

        //Assert
        assertThat(result).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Should find dominant sex in list with only males")
    void checkWhichSexIsMore_shouldReturnMale() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.M, 10, 5, 5, false)
        );

        //Act
        var result = checkWhichSexIsMore(animalList);

        //Assert
        assertThat(result).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Should find dominant sex in list with only females")
    void checkWhichSexIsMore_shouldReturnFemale() {
        //Arrange
        List<Animal> animalList = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 30, 25, 5, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 40, 30, 5, true),
            new Animal("Doh", Animal.Type.DOG, Animal.Sex.F, 10, 5, 5, false)
        );

        //Act
        var result = checkWhichSexIsMore(animalList);

        //Assert
        assertThat(result).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Should return male if list is empty")
    void checkWhichSexIsMore_shouldReturnMaleIfListEmpty() {
        //Arrange
        List<Animal> animalList = List.of();

        //Act
        var result = checkWhichSexIsMore(animalList);

        //Assert
        assertThat(result).isEqualTo(Animal.Sex.M);
    }
}
