package edu.hw4;

import java.util.Comparator;
import java.util.List;

final class Task18 {

    private Task18() {

    }

    //Task18 find the heaviest fish in several lists
    public static Animal findHeaviestFishInSeveralLists(List<List<Animal>> animalLists) {
        return animalLists.stream().flatMap(List::stream)
            .filter(animal -> Animal.Type.FISH.equals(animal.type()))
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

}
