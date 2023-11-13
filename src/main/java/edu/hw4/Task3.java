package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Task3 {

    private Task3() {

    }

    //Task3 count animals by types
    public static Map<Animal.Type, Integer> countAllTypes(List<Animal> animalList) {

        return animalList.stream().collect(Collectors.groupingBy(
            Animal::type,
            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
        ));
    }
}
