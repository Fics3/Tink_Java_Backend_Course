package edu.project1;

import java.util.Random;

final class Dictionary {
    private static final String[] WORDS = {"solikamsk", "banana", "cherry", "orange", "grape", "hello", "world"};

    private Dictionary() {

    }

    public static String getRandomWord() {
        Random random = new Random();
        int randDictionaryIndex = random.nextInt(WORDS.length);
        return WORDS[randDictionaryIndex];
    }

}
