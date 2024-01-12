package edu.project1;

public class AnswerManager {
    private static final int MAX_ATTEMPTS = 5;
    private String targetWord;
    private StringBuilder currentWord;
    private int remainingAttempts;

    AnswerManager() {
        targetWord = Dictionary.getRandomWord();
        remainingAttempts = MAX_ATTEMPTS;
        currentWord = new StringBuilder("_".repeat(targetWord.length()));
    }

    AnswerManager(String word) { // This constructor only for tests, cant find better way, files seemed redundant
        targetWord = word;
        remainingAttempts = MAX_ATTEMPTS;
        currentWord = new StringBuilder("_".repeat(targetWord.length()));
    }

    public String getCurrentWord() {
        return currentWord.toString();
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public boolean isGameOver() {
        return remainingAttempts <= 0 || currentWord.indexOf("_") == -1;
    }

    public boolean isLost() {
        return remainingAttempts <= 0;
    }

    public boolean isWin() {
        return currentWord.indexOf("_") == -1;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public boolean makeGuess(char letter) {
        boolean letterFound = false;
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == letter) {
                currentWord.setCharAt(i, letter);
                letterFound = true;
            }
        }

        if (!letterFound) {
            remainingAttempts--;
        }

        return letterFound;
    }
}
