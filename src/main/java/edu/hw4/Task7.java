package edu.hw4;

import java.util.Comparator;
import java.util.List;

final class Task7 {

    private Task7() {

    }

    //Task7 find oldest Animal
    public static Animal findOldestAnimal(List<Animal> animalList) {
        return animalList.stream().max(Comparator.comparingInt(Animal::age)).orElse(null);
    }
}
