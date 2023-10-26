package edu.hw4.Task19;

import edu.hw4.Animal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class FindErrors {

    private FindErrors() {

    }

    //Task19 find errors
    public static Map<String, Set<ValidationError>> findErrors(List<Animal> animalList) {
        return animalList.stream().collect(Collectors.toMap(Animal::name, FindErrors::validateAnimal));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        return Stream.of(validateHeight(animal), validateAge(animal), validateName(animal), validateWeight(animal))
            .filter(
                Objects::nonNull).collect(Collectors.toSet());
    }

    private static ValidationError validateWeight(Animal animal) {
        if (animal.weight() < 0) {
            return ValidationError.NEG_WEIGHT;
        } else {
            return null;
        }
    }

    private static ValidationError validateHeight(Animal animal) {
        if (animal.height() < 0) {
            return ValidationError.NEG_HEIGHT;
        } else {
            return null;
        }
    }

    private static ValidationError validateAge(Animal animal) {
        if (animal.age() < 0) {
            return ValidationError.NEG_AGE;
        } else {
            return null;
        }
    }

    private static ValidationError validateName(Animal animal) {
        if (animal.name() == null || animal.name().isEmpty()) {
            return ValidationError.NAME_EMPTY;
        } else {
            return null;
        }
    }

}
