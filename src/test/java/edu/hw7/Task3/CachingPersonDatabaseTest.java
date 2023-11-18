package edu.hw7.Task3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CachingPersonDatabaseTest {

    private CachingPersonDatabase database;

    @BeforeEach
    void setUp() {
        database = new CachingPersonDatabase();
    }

    @Test
    @DisplayName("Add should be not null")
    void addPerson_sizeShouldBeOne() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);

        // Assert
        assertThat(database.getPeople().size()).isOne();
    }

    @Test
    @DisplayName("Find by Name")
    void findByName_shouldReturnPersonWithMatchingName() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByName("John");

        // Assert
        assertThat(foundPerson).isNotNull().isEqualTo(person);
    }

    @Test
    @DisplayName("Delete Person")
    void deletePerson_shouldRemovePerson() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        database.delete(1);

        // Assert
        assertThat(database.findByName("John")).isNull();
    }

    @Test
    @DisplayName("Find by Address")
    void findByAddress_shouldReturnPersonWithMatchingAddress() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByAddress("123 Main St");

        // Assert
        assertThat(foundPerson).isNotNull().isEqualTo(person);
    }

    @Test
    @DisplayName("Find by Phone")
    void findByPhone_shouldReturnPersonWithMatchingPhone() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByPhone("123-456-7890");

        // Assert
        assertThat(foundPerson).isNotNull().isEqualTo(person);
    }

    @Test
    @DisplayName("Find by Nonexistent Name")
    void findByName_NonexistentName_shouldReturnNull() {
        // Act
        Person foundPerson = database.findByName("Nonexistent");

        // Assert
        assertThat(foundPerson).isNull();
    }

    @Test
    @DisplayName("Find by Address(Null)")
    void findByAddress_shouldReturnNullIfPersonHaveNullParameter() {
        // Arrange
        Person person = new Person(1, null, "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByAddress("123 Main St");

        // Assert
        assertThat(foundPerson).isNull();
    }

    @Test
    @DisplayName("Find by Phone(Null)")
    void findByPhone_shouldReturnNullIfPersonHaveNullParameter() {
        // Arrange
        Person person = new Person(1, null, "123 Main St", "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByPhone("123-456-7890");

        // Assert
        assertThat(foundPerson).isNull();
    }

    @Test
    @DisplayName("Find by Name(Null)")
    void findByName_shouldReturnNullIfPersonHaveNullParameter() {
        // Arrange
        Person person = new Person(1, "John", null, "123-456-7890");
        database.add(person);

        // Act
        Person foundPerson = database.findByName("John");

        // Assert
        assertThat(foundPerson).isNull();
    }

    @Test
    @DisplayName("Concurrent Add")
    void concurrentAddAndFind_ShouldReturnPerson() throws InterruptedException {
        // Arrange
        int threadCount = 15;
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {

            CountDownLatch latch = new CountDownLatch(threadCount);

            // Act
            for (int i = 0; i < threadCount; i++) {
                executorService.submit(() -> {
                    Person person = new Person(1, "John", "123 Main St", "123-456-7890");
                    database.add(person);
                    latch.countDown();
                });
            }

            latch.await();

            // Assert
            assertThat(database.getPeople().size()).isEqualTo(15);
        }

    }

    @Test
    @DisplayName("Concurrent Delete")
    void concurrentDelete_ShouldRemovePerson() throws InterruptedException {
        // Arrange
        int threadCount = 5;
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");
        database.add(person);
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            CountDownLatch latch = new CountDownLatch(threadCount);

            // Act
            for (int i = 0; i < threadCount; i++) {
                executorService.submit(() -> {
                    database.delete(1);
                    latch.countDown();
                });
            }

            latch.await();

            // Assert
            assertThat(database.getPeople().size()).isZero();
            assertThat(database.findByName("John")).isNull();
        }
    }

}
