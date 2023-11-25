package edu.hw7;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.PersonDatabaseImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    private PersonDatabase database;

    @BeforeEach
    void setUp() {
        database = new PersonDatabaseImpl();
    }

    @Test
    void testAddAndFind() {
        Person person = new Person(1, "User", "Street", "123-45-67");
        Person anotherPerson = new Person(2, "Another User", "Street", "123-45-67-89");

        database.add(person);
        database.add(anotherPerson);

        assertEquals(person, database.findByName("User").get(0));
        assertEquals(List.of(person, anotherPerson), database.findByAddress("Street"));
        assertEquals(person, database.findByPhone("123-45-67").get(0));
    }

    @Test
    void testDelete() {
        Person person = new Person(1, "User", "Street", "123-45-67");
        database.add(person);

        database.delete(1);

        assertTrue(database.findByName("User").isEmpty());
        assertTrue(database.findByAddress("Street").isEmpty());
        assertTrue(database.findByPhone("123-45-67").isEmpty());
    }

    @Test
    void testFindByNonexistentAttributes() {
        Person person = new Person(1, "User", "Street", "123-45-67");
        database.add(person);

        assertTrue(database.findByName("NonexistentName").isEmpty());
        assertTrue(database.findByAddress("NonexistentAddress").isEmpty());
        assertTrue(database.findByPhone("NonexistentPhone").isEmpty());
    }

    @Test
    void testConcurrentAccess1() {
        int numberOfThreads = 10;
        int personsPerThread = 100;

        Runnable addPersonTask = () -> {
            for (int i = 0; i < personsPerThread; i++) {
                Person person = new Person(i, "Person" + i, "Address" + i, "Phone" + i);
                database.add(person);
            }
        };

        Runnable deletePersonTask = () -> {
            for (int i = 0; i < personsPerThread; i++) {
                database.delete(i);
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            addPersonTask.run();
            deletePersonTask.run();
        }

        assertEquals(0, database.findByName("Person0").size());
    }
}
