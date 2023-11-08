package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnimalsUtils {
    private AnimalsUtils() {
    }

    private final static int MAX_LEN_FOR_TASK_11 = 100;
    private final static int MAX_ANIMAL_WEIGHT = 100;
    private final static int MAX_ANIMAL_HEIGHT = 200;
    private final static int MAX_ANIMAL_AGE = 50;
    private final static String HEIGHT = "Height";
    private final static String WEIGHT = "Weight";
    private final static String AGE = "Age";

    public static List<Animal> task1(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> task2(List<Animal> animals, int numberOfRequiredElements) {
        Objects.requireNonNull(animals);

        if (numberOfRequiredElements > animals.size()) {
            throw new IllegalArgumentException("The method can not return more elements than it accepted");
        }

        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(numberOfRequiredElements)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(a -> 1)));
    }

    public static Animal task4(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex task5(List<Animal> animals) {
        Objects.requireNonNull(animals);

        long female = animals.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();
        long male = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();

        if (female > male) {
            return Animal.Sex.F;
        } else if (male > female) {
            return Animal.Sex.M;
        }
        return null;
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (existing, replacement) -> existing.weight() >= replacement.weight() ? existing : replacement
            ));
    }

    public static Animal task7(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream().max(Comparator.comparing(Animal::age)).orElse(null);
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> task11(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > MAX_LEN_FOR_TASK_11)
            .collect(Collectors.toList());
    }

    public static Integer task12(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> task13(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static Boolean task14(List<Animal> animals, int k) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int l) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.toMap(
                Animal::type,
                Animal::weight,
                Integer::sum
            ));
    }

    public static List<Animal> task16(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static Boolean task17(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites()).count()
            > animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.bites()).count();
    }

    @SafeVarargs public static Animal task18(List<Animal> animals, List<Animal>... animalLists) {
        Objects.requireNonNull(animals);
        Objects.requireNonNull(animalLists);

        return Stream.concat(animals.stream(), Stream.of(animalLists).flatMap(List::stream))
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                AnimalsUtils::isValidAnimal
            ))
            .entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> task20(List<Animal> animals) {
        Objects.requireNonNull(animals);

        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                animal -> isValidAnimal(animal).stream()
                    .map(ValidationError::getField)
                    .collect(Collectors.joining(", "))
            ))
            .entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Set<ValidationError> isValidAnimal(Animal animal) {
        return Stream.of(
                isValidName(animal),
                isValidAge(animal),
                isValidHeight(animal),
                isValidWeight(animal)
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    private static ValidationError isValidName(Animal animal) {
        if (animal.name() == null || animal.name().equals("")) {
            return new ValidationError("Name is empty", "Name");
        }
        return null;
    }

    private static ValidationError isValidAge(Animal animal) {
        if (animal.age() < 0) {
            return new ValidationError("Age is negative", AGE);
        }

        if (animal.age() > MAX_ANIMAL_AGE) {
            return new ValidationError("Age is too big", AGE);
        }
        return null;
    }

    private static ValidationError isValidWeight(Animal animal) {
        if (animal.weight() < 0) {
            return new ValidationError("Weight is negative", WEIGHT);
        }

        if (animal.weight() > MAX_ANIMAL_WEIGHT) {
            return new ValidationError("Weight is too big", WEIGHT);
        }

        return null;
    }

    private static ValidationError isValidHeight(Animal animal) {
        if (animal.height() < 0) {
            return new ValidationError("Height is negative", HEIGHT);
        }

        if (animal.height() > MAX_ANIMAL_HEIGHT) {
            return new ValidationError("Height is too big", HEIGHT);
        }

        return null;
    }
}
