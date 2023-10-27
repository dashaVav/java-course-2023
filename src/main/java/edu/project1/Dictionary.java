package edu.project1;

import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

class Dictionary {
    @NotNull List<String> dictionary;

    Dictionary() {
        dictionary = List.of("hello", "world", "floor", "class");
    }

    @NotNull String randomWord() {
        return dictionary.get(new Random().nextInt(dictionary.size()));
    }
}
