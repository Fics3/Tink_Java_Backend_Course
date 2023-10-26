package edu.hw4;

import java.util.List;

final class Task17 {

    private Task17() {

    }

    //Task17 check that spiders bites often than dogs (boolean)
    public static Boolean isSpidersBitesMoreThanDogs(List<Animal> animalList) {
        long spidersBites =
            animalList.stream().filter(animal -> Animal.Type.SPIDER.equals(animal.type()) && animal.bites()).count();
        long dogbites =
            animalList.stream().filter(animal -> Animal.Type.DOG.equals(animal.type()) && animal.bites()).count();
        return spidersBites > dogbites;
    }

}
