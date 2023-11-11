package edu.hw4.Task19;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

final class FindErrors {

    private FindErrors() {

    }

    //Task19 find errors
    public static Map<String, Set<ValidationError>> findErrors(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.toMap(Animal::name, FindErrors::validateAnimal));
    }

    private static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        validateWeight(animal, errors);
        validateHeight(animal, errors);
        validateAge(animal, errors);
        validateName(animal, errors);

        return errors;
    }

    private static void validateWeight(Animal animal, Set<ValidationError> errors) {
        if (animal.weight() < 0) {
            errors.add(ValidationError.NEG_WEIGHT);
        }
    }

    private static void validateHeight(Animal animal, Set<ValidationError> errors) {
        if (animal.height() < 0) {
            errors.add(ValidationError.NEG_HEIGHT);
        }
    }

    private static void validateAge(Animal animal, Set<ValidationError> errors) {
        if (animal.age() < 0) {
            errors.add(ValidationError.NEG_AGE);
        }
    }

    private static void validateName(Animal animal, Set<ValidationError> errors) {
        if (animal.name() == null || animal.name().isEmpty()) {
            errors.add(ValidationError.NAME_EMPTY);
        }
    }

}

