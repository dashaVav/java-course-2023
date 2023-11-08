package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    private final static int PAWS_OF_CAT_DOG = 4;
    private final static int PAWS_OF_SPIDER = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> PAWS_OF_CAT_DOG;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> PAWS_OF_SPIDER;
        };
    }
}
