package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Task5 {

    private Task5() {

    }

    //Task5 find who is more Male or Female
    public static Animal.Sex checkWhichSexIsMore(List<Animal> animalList) {
        Map<Animal.Sex, Long> sexCount = animalList.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = sexCount.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = sexCount.getOrDefault(Animal.Sex.F, 0L);

        return maleCount >= femaleCount ? Animal.Sex.M : Animal.Sex.F;
    }
}
