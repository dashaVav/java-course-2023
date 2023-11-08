package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalsUtilsTest {

    private List<Animal> sampleAnimals;

    @BeforeEach
    void setUp() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 40, 20, true);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 5, 2, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false);
        Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 1, 3, 2, true);
        sampleAnimals = new ArrayList<>();
        sampleAnimals.addAll(List.of(cat, dog, fish, bird, spider));
    }

    @Test
    void task1SortsByHeight() {
        List<Animal> result = AnimalsUtils.task1(sampleAnimals);

        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i - 1).height() <= result.get(i).height());
        }
    }

    @Test
    void task2SelectAnimalsWithHighestWeight() {
        List<Animal> result = AnimalsUtils.task2(sampleAnimals, 3);

        assertEquals(3, result.size());

        for (int i = 1; i < 3; i++) {
            assertTrue(result.get(i - 1).weight() >= result.get(i).weight());
        }
    }

    @Test
    void task2ExceptionNumberOfRequiredElementsIsGreaterThanInput() {
        assertThrows(IllegalArgumentException.class, () -> AnimalsUtils.task2(sampleAnimals, 6));
    }

    @Test
    void task3CountByType() {
        sampleAnimals.add(
            new Animal("BigSpider", Animal.Type.SPIDER, Animal.Sex.F, 1, 3, 2, true));

        Map<Animal.Type, Integer> animalCounts = AnimalsUtils.task3(sampleAnimals);

        assertEquals(1, animalCounts.get(Animal.Type.CAT));
        assertEquals(1, animalCounts.get(Animal.Type.DOG));
        assertEquals(1, animalCounts.get(Animal.Type.FISH));
        assertEquals(1, animalCounts.get(Animal.Type.BIRD));
        assertEquals(2, animalCounts.get(Animal.Type.SPIDER));
    }

    @Test
    void task4AnimalWithMaxLengthName() {
        Animal expected =
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 1, 3, 2, true);

        Animal result = AnimalsUtils.task4(sampleAnimals);

        assertEquals(expected, result);
    }

    @Test
    void task4ReturnsNullForEmptyList() {
        List<Animal> animals = List.of();

        Animal result = AnimalsUtils.task4(animals);

        assertNull(result);
    }

    @Test
    void task5CompareSexesMoreFemales() {
        Animal.Sex expextedSex = Animal.Sex.F;

        Animal.Sex result = AnimalsUtils.task5(sampleAnimals);

        assertEquals(expextedSex, result);
    }

    @Test
    void task5CompareSexesMoreMales() {
        Animal.Sex expextedSex = Animal.Sex.M;
        sampleAnimals.remove(new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, true));
        sampleAnimals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true));

        Animal.Sex result = AnimalsUtils.task5(sampleAnimals);

        assertEquals(expextedSex, result);
    }

    @Test
    void task5CompareSexesEqualNumbers() {
        sampleAnimals.remove(new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, true));

        Animal.Sex result = AnimalsUtils.task5(sampleAnimals);

        assertNull(result);
    }

    @Test
    void task6FindHeaviestAnimalOfEachType() {
        sampleAnimals.add(new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 10, 25, 100, true));

        Map<Animal.Type, Animal> result = AnimalsUtils.task6(sampleAnimals);

        assertEquals(100, result.get(Animal.Type.CAT).weight());
        assertEquals(20, result.get(Animal.Type.DOG).weight());
        assertEquals(2, result.get(Animal.Type.FISH).weight());
        assertEquals(1, result.get(Animal.Type.BIRD).weight());
        assertEquals(2, result.get(Animal.Type.SPIDER).weight());
    }

    @Test
    void task7AnimalWithMaxAge() {
        Animal expectedAnimal = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, true);

        Animal result = AnimalsUtils.task7(sampleAnimals);

        assertEquals(expectedAnimal, result);
    }

    @Test
    void task7AnimalWithMaxAgeWithEmptyList() {
        List<Animal> animals = List.of();

        Animal result = AnimalsUtils.task7(animals);

        assertNull(result);
    }

    @Test
    void task8TheHeaviestAnimalBelowHeight() {
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 5, 2, false);

        Optional<Animal> result = AnimalsUtils.task8(sampleAnimals, 10);

        assertEquals(fish, result.orElse(null));
    }

    @Test
    void task8TheHeaviestAnimalBelowHeightEmptyOptional() {
        Optional<Animal> result = AnimalsUtils.task8(sampleAnimals, 1);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void task9SumPaws() {
        Integer result = AnimalsUtils.task9(sampleAnimals);

        assertEquals(18, result);

        assertEquals(0, AnimalsUtils.task9(List.of()));
    }

    @Test
    void task10ListAnimalsWhoseAgeDoesNotMatchNumberOfPaws() {
        List<Animal> result = AnimalsUtils.task10(sampleAnimals);

        assertEquals(4, result.size());

        sampleAnimals.remove(3);
        assertEquals(sampleAnimals, result);
    }

    @Test
    void task11() {
        Animal animal = new Animal("Cat1", Animal.Type.CAT, Animal.Sex.F, 10, 150, 5, true);
        sampleAnimals.add(animal);

        List<Animal> result = AnimalsUtils.task11(sampleAnimals);

        assertEquals(List.of(animal), result);
    }

    @Test
    void task11EmptyResult() {
        List<Animal> result = AnimalsUtils.task11(sampleAnimals);

        assertEquals(0, result.size());
    }

    @Test
    void task12AnimalsWhoseWeightIsGreaterThanHeight() {
        sampleAnimals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 100, true));

        Integer result = AnimalsUtils.task12(sampleAnimals);

        assertEquals(1, result);
    }

    @Test
    void task13AnimalsWhoseNamesConsistMoreThanTwoWords() {
        Animal newAnimal = new Animal("Cat Leo Old", Animal.Type.CAT, Animal.Sex.F, 10, 25, 100, true);
        sampleAnimals.add(newAnimal);

        List<Animal> result = AnimalsUtils.task13(sampleAnimals);

        assertEquals(List.of(newAnimal), result);
    }

    @Test
    void task13AnimalsWhoseNamesConsistMoreThanTwoWordsEmptyResult() {
        List<Animal> result = AnimalsUtils.task13(sampleAnimals);

        assertEquals(List.of(), result);
    }

    @Test
    void task14IsDogWithHeightOfMoreThanK() {
        Boolean resultTrue = AnimalsUtils.task14(sampleAnimals, 30);
        assertTrue(resultTrue);

        Boolean resultFalse = AnimalsUtils.task14(sampleAnimals, 40);
        assertFalse(resultFalse);
    }

    @Test
    void task15SumWeightOfAnimalsThatAgeFromKToL() {
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 5, 10, false);
        sampleAnimals.add(fish);

        Map<Animal.Type, Integer> result = AnimalsUtils.task15(sampleAnimals, 1, 4);

        assertEquals(3, result.size());
        assertEquals(12, result.get(Animal.Type.FISH));
        assertEquals(1, result.get(Animal.Type.BIRD));
        assertEquals(2, result.get(Animal.Type.SPIDER));
    }

    @Test
    void task16SortByTypeSexAndName() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, true);
        Animal cat1 = new Animal("Alfa", Animal.Type.CAT, Animal.Sex.F, 10, 25, 101, true);
        Animal cat2 = new Animal("Leo", Animal.Type.CAT, Animal.Sex.M, 10, 25, 101, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 40, 20, true);

        List<Animal> animals = new ArrayList<>(List.of(cat, cat1, cat2, dog));
        List<Animal> expectedResult = new ArrayList<>(List.of(cat2, cat1, cat, dog));

        List<Animal> result = AnimalsUtils.task16(animals);

        assertEquals(expectedResult, result);
    }

    @Test
    void task17SpidersBiteMoreOftenThanDogsTrue() {
        sampleAnimals.add(new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 5, 40, 20, true));

        Boolean result = AnimalsUtils.task17(sampleAnimals);

        assertTrue(result);
    }

    @Test
    void task17SpidersBiteMoreOftenThanDogsFalse() {
        Boolean result = AnimalsUtils.task17(sampleAnimals);

        assertFalse(result);
        assertFalse(AnimalsUtils.task17(List.of()));
    }

    @Test
    void task18TwoLists() {
        Animal fish = new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 1, 10, false);
        List<Animal> listFishes = List.of(fish);

        Animal result = AnimalsUtils.task18(sampleAnimals, listFishes);

        assertEquals(fish, result);
    }

    @Test
    void task18MoreThanTwoLists() {
        List<Animal> list1 = List.of(new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 10, 25, 5, false));
        List<Animal> list2 = List.of(new Animal("Cat", Animal.Type.DOG, Animal.Sex.F, 10, 25, 5, false));

        Animal result = AnimalsUtils.task18(sampleAnimals, list1, list2);

        assertEquals(sampleAnimals.get(2), result);
    }

    @Test
    public void task19() {
        sampleAnimals.add(new Animal("", Animal.Type.DOG, Animal.Sex.F, -1, 1000, 200, false));

        Map<String, Set<ValidationError>> result = AnimalsUtils.task19(sampleAnimals);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(""));

        Set<String> errorMessages = result.get("").stream()
            .map(ValidationError::getMessage)
            .collect(Collectors.toSet());

        assertTrue(errorMessages.contains("Name is empty"));
        assertTrue(errorMessages.contains("Age is negative"));
        assertTrue(errorMessages.contains("Height is too big"));
        assertTrue(errorMessages.contains("Weight is too big"));
    }

    @Test
    public void task20() {
        sampleAnimals.add(new Animal("", Animal.Type.DOG, Animal.Sex.F, -1, 1000, 200, false));

        Map<String, String> result = AnimalsUtils.task20(sampleAnimals);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(""));

        String errorFields = result.get("");

        assertTrue(errorFields.contains("Name"));
        assertTrue(errorFields.contains("Age"));
        assertTrue(errorFields.contains("Height"));
        assertTrue(errorFields.contains("Weight"));
    }

}
