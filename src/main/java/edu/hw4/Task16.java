package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class Task16 {

    private Task16() {

    }

    //Task16 should sort by type then by sex then by name
    public static List<Animal> sortByTypeSexName(List<Animal> animalList) {
        return animalList.stream()
            .sorted(Comparator.comparing(Animal::type)
            .thenComparing(Animal::sex)
            .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

}
