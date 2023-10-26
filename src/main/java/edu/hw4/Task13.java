package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

final class Task13 {

    private Task13() {

    }

    //Task13 make list of animals with names with more than one word
    public static List<Animal> namesMoreThanOneWord(List<Animal> animalList) {
        return animalList.stream().filter(animal -> {
                String name = animal.name();
                return name != null && !name.trim().isEmpty() && name.split(" ").length > 1;
            }
        ).collect(Collectors.toList());
    }

}
