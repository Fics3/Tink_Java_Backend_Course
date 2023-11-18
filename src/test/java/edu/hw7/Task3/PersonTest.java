package edu.hw7.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    @DisplayName("Person is Valid")
    void isValid_shouldReturnTrue() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", "123-456-7890");

        // Assert
        assertThat(person.isValid()).isTrue();
    }

    @Test
    @DisplayName("Person is Invalid - Name is Null")
    void isValid_nameNull() {
        // Arrange
        Person person = new Person(1, null, "123 Main St", "123-456-7890");

        // Assert
        assertThat(person.isValid()).isFalse();
    }

    @Test
    @DisplayName("Person is Invalid - Address is Null")
    void isValid_addressNull() {
        // Arrange
        Person person = new Person(1, "John", null, "123-456-7890");

        // Assert
        assertThat(person.isValid()).isFalse();
    }

    @Test
    @DisplayName("Person is Invalid - Phone Number is Null")
    void isValid_phoneNumberNull() {
        // Arrange
        Person person = new Person(1, "John", "123 Main St", null);

        // Assert
        assertThat(person.isValid()).isFalse();
    }
}
