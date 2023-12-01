package edu.hw8.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordCrackerTest {

    @Test
    @DisplayName("readPasswords - read from correct file")
    void readPasswords_ReadPasswordsFromFile() {
        // Arrange
        PasswordCracker passwordCracker = new PasswordCracker(4, "src/test/resources/hw8/Task3");

        // Act
        passwordCracker.readPasswords("src/test/resources/hw8/Task3");

        // Assert
        assertThat(passwordCracker.getPasswords()).containsValues("a.v.petrov", "v.v.belov");
    }

    @Test
    @DisplayName("crackPasswords - Valid passwords")
    void crackPasswords_ValidPasswords_SuccessfullyCracked() {
        // Arrange
        PasswordCracker passwordCracker = new PasswordCracker(4, "src/test/resources/hw8/Task3");

        // Act
        passwordCracker.crackPasswords();

        // Assert
        assertThat(passwordCracker.getCrackedPasswords()).hasSize(2)
            .containsValues("m123", "aaaa");
    }

    @Test
    @DisplayName("hashPasswords - Hashing test pass")
    void hashPassword_ValidPassword_CorrectHash() {
        // Arrange
        PasswordCracker passwordCracker = new PasswordCracker(4, "src/test/resources/hw8/Task3");

        // Act
        String hashedPassword = passwordCracker.hashPassword("testPassword");

        // Assert
        assertThat(hashedPassword).isEqualTo("fed3b61b26081849378080b34e693d2e");
    }

}
