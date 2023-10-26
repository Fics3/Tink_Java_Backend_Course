package edu.hw4;

import java.util.List;

final class Task12 {

    private Task12() {

    }

    //Task12 count animals weight bigger than height
    public static Integer countAnimalsHigherBoundary(List<Animal> animalList) {
        return Math.toIntExact(animalList.stream().filter(animal -> animal.weight() > animal.height()).count());
    }

}
