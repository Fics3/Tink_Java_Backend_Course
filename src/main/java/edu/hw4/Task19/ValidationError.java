package edu.hw4.Task19;

public enum ValidationError {

    NEG_WEIGHT("Negative weight"),
    NEG_HEIGHT("Negative height"),
    NEG_AGE("Negative age"),
    NAME_EMPTY("Name empty or null");

    private final String message;

    ValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
