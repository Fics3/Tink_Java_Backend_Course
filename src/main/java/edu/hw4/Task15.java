package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Task15 {

    private Task15() {

    }

    //Task15 find total sum of animal age from boundary to one
    public static Map<Animal.Type, Integer> findSumWeightYoungerThanBoundary(List<Animal> animalList, int boundary) {
        return animalList.stream()
            .filter(animal -> animal.age() >= 1 && animal.age() <= boundary)
            .collect(
                Collectors
                    .groupingBy(Animal::type, Collectors.summingInt(Animal::age))
            );
    }

}
