package edu.hw4;

import java.util.Comparator;
import java.util.List;

final class Task7 {

    private Task7() {

    }

    //Task7 find oldest Animal
    public static Animal findOldestAnimal(List<Animal> animalList, int k) {
        if (k > animalList.size() || k <= 0) {
            throw new IllegalArgumentException("k should be k>0 or k<=size");
        }
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::age))
            .skip(animalList.size() - k)
            .findFirst()
            .orElse(null);
    }
}
