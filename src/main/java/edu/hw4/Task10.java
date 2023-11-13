package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

final class Task10 {

    private Task10() {

    }

    //Task10 make list of animals with paws != age
    public static List<Animal> pawsNotEqualsAge(List<Animal> animalList) {
        return animalList.stream().filter(animal -> animal.age() != animal.paws()).collect(Collectors.toList());
    }
}
