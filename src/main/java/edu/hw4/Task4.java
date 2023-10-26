package edu.hw4;

import java.util.Comparator;
import java.util.List;

final class Task4 {

    private Task4() {

    }

    //Task4 find animal with the longest name
    public static Animal findLongestNameAnimal(List<Animal> animalList) {
        return animalList.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }
}
