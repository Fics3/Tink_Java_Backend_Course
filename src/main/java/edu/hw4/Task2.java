package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class Task2 {

    private Task2() {

    }

    //Task2 sort by weight from big to low
    public static List<Animal> sortWeight(List<Animal> animalList, int k) {
        return animalList.stream().sorted(Comparator
                .comparingInt(Animal::weight).reversed()).limit(Math.abs(k))
            .collect(Collectors.toList());
    }
}
