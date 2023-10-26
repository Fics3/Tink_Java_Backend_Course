package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

final class Task11 {

    private Task11() {

    }

    //Task11 list of animals that bites and higher than boundary
    public static List<Animal> bitesAndHigherBoundary(List<Animal> animalList, int heightBoundary) {
        return animalList.stream().filter(animal -> animal.height() >= heightBoundary && animal.bites())
            .collect(
                Collectors.toList());
    }

}
