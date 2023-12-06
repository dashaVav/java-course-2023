package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseImplLock implements PersonDatabase {
    private final Map<Integer, Person> personById = new HashMap<>();
    private final Map<String, List<Person>> personByName = new HashMap<>();
    private final Map<String, List<Person>> personByAddress = new HashMap<>();
    private final Map<String, List<Person>> personByPhone = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            personById.put(person.id(), person);
            personByName.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
            personByAddress.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
            personByPhone.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person personForDelete = personById.get(id);
            removeFromIndex(personByName, personForDelete.name(), personForDelete);
            removeFromIndex(personByAddress, personForDelete.address(), personForDelete);
            removeFromIndex(personByPhone, personForDelete.phoneNumber(), personForDelete);
            personById.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
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
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return personByName.getOrDefault(name, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return personByAddress.getOrDefault(address, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return personByPhone.getOrDefault(phone, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }
}
