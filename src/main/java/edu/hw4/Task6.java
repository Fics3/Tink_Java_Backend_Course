package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Task6 {

    private Task6() {

    }

    //Task6 find Heaviest Animal each type
    public static Map<Animal.Type, Animal> findHeaviestAnimalEachType(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (existing, replacement) -> existing.weight() >= replacement.weight() ? existing : replacement
            ));
    }
}
