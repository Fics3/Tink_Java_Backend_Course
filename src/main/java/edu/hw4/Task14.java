package edu.hw4;

import java.util.List;

final class Task14 {

    private Task14() {

    }

    //Task14 true if list has dog higher boundary
    public static Boolean hasDogHigherBoundary(List<Animal> animalList, int boundary) {
        return animalList.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() >= boundary);
    }

}
