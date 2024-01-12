package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public class CachingPersonDatabaseReadWriteLock implements PersonDatabase {
    private final List<Person> people = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            people.add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            people.removeIf(person -> person.id() == id);

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return people.stream()
                .filter(person -> person.isValid() && person.name().equals(name))
                .findFirst()
                .orElse(null);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return people.stream()
                .filter(person -> person.isValid() && person.address().equals(address))
                .findFirst()
                .orElse(null);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    @Override
    @Nullable
    public Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return people.stream()
                .filter(person -> person.isValid() && person.phoneNumber().equals(phone))
                .findFirst()
                .orElse(null);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
