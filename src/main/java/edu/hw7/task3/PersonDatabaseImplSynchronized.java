package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabaseImplSynchronized implements PersonDatabase {
    private final Map<Integer, Person> personById = new HashMap<>();
    private final Map<String, List<Person>> personByName = new HashMap<>();
    private final Map<String, List<Person>> personByAddress = new HashMap<>();
    private final Map<String, List<Person>> personByPhone = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personById.put(person.id(), person);
        personByName.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        personByAddress.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        personByPhone.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person personForDelete = personById.get(id);
        removeFromIndex(personByName, personForDelete.name(), personForDelete);
        removeFromIndex(personByAddress, personForDelete.address(), personForDelete);
        removeFromIndex(personByPhone, personForDelete.phoneNumber(), personForDelete);
        personById.remove(id);
    }

    private void removeFromIndex(Map<String, List<Person>> map, String key, Person person) {
        map.computeIfPresent(key, (k, v) -> {
            v.remove(person);
            if (v.isEmpty()) {
                return null;
            }
            return v;
        });

        if (map.get(key) == null) {
            map.remove(key);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return personByName.getOrDefault(name, List.of());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return personByAddress.getOrDefault(address, List.of());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return personByPhone.getOrDefault(phone, List.of());
    }
}
