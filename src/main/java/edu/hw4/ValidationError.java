package edu.hw4;

public class ValidationError extends RuntimeException {
    private final String field;

    public ValidationError(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
