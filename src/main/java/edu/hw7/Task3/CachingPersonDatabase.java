package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public class CachingPersonDatabase implements PersonDatabase {

    private final List<Person> people = new ArrayList<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public synchronized void add(Person person) {
        people.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        people.removeIf(person -> person.id() == id);
    }

    @Override
    @Nullable
    public synchronized Person findByName(String name) {
        return people.stream()
            .filter(person -> person.isValid() && person.name().equals(name))
            .findFirst()
            .orElse(null);
    }

    @Override
    @Nullable
    public synchronized Person findByAddress(String address) {
        return people.stream()
            .filter(person -> person.isValid() && person.address().equals(address))
            .findFirst()
            .orElse(null);
    }

    @Override
    @Nullable
    public synchronized Person findByPhone(String phone) {
        return people.stream()
            .filter(person -> person.isValid() && person.phoneNumber().equals(phone))
            .findFirst()
            .orElse(null);
    }

}
