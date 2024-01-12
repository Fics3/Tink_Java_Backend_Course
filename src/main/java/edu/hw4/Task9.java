package edu.hw4;

import java.util.List;

final class Task9 {

    private Task9() {

    }

    //Task9 total sum of paws
    public static Integer totalPawsSum(List<Animal> animalList) {
        return animalList.stream().mapToInt(Animal::paws).sum();
    }
}
