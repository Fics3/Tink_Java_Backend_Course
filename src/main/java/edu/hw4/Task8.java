package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

final class Task8 {

    private Task8() {

    }

    //Task8 find the heaviest animal below height border
    public static Optional<Animal> findHeaviestBelowHeight(List<Animal> animalList, int boundary) {
        return animalList.stream().filter(animal -> animal.height() < boundary)
            .max(Comparator.comparingInt(Animal::weight));
    }

}
