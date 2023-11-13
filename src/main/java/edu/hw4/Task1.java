package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

final class Task1 {
    private Task1() {

    }

    //Task1 from by height from short to high
    public static List<Animal> sortHeight(List<Animal> animalList) {
        return animalList.stream().sorted(Comparator.comparingInt(Animal::height)).collect(Collectors.toList());
    }

}
