package edu.hw3.Task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.hw3.Task5.ParseContacts.parseContacts;
import static org.assertj.core.api.Assertions.assertThat;

public class ParseContactTest {
    private List<String> names;
    private List<String> namesWithoutLastName;
    private List<Contact> expectedContactsAsc;
    private List<Contact> expectedContactsDesc;
    private List<Contact> expectedContactsWithoutLastNameAsc;
    private List<Contact> expectedContactsWithoutLastNameDesc;

    @BeforeEach
    void arrange() {
        names = List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes");
        namesWithoutLastName = List.of("John Locke", "Thomas Aquinas", "David", "Rene Descartes");
        expectedContactsAsc = List.of(
            new Contact("Thomas Aquinas"),
            new Contact("Rene Descartes"),
            new Contact("David Hume"),
            new Contact("John Locke")
        );
        expectedContactsDesc = List.of(
            new Contact("John Locke"),
            new Contact("David Hume"),
            new Contact("Rene Descartes"),
            new Contact("Thomas Aquinas")
        );
        expectedContactsWithoutLastNameAsc = List.of(
            new Contact("David"),
            new Contact("Thomas Aquinas"),
            new Contact("Rene Descartes"),
            new Contact("John Locke")
        );
        expectedContactsWithoutLastNameDesc = List.of(
            new Contact("John Locke"),
            new Contact("Rene Descartes"),
            new Contact("Thomas Aquinas"),
            new Contact("David")
        );
    }

    @Test
    @DisplayName("Test for ASC sort with first and last name")
    void parseContacts_shouldSortAscContactsWithLastAndFirstName() {
        // Act
        List<Contact> sortedContacts = parseContacts(names, "ASC");

        // Assert
        for (int i = 0; i < sortedContacts.size(); i++) {
            assertThat(sortedContacts.get(i).getFirstName()).isEqualTo(expectedContactsAsc.get(i).getFirstName());
            assertThat(sortedContacts.get(i).getLastName()).isEqualTo(expectedContactsAsc.get(i).getLastName());
        }
    }

    @Test
    @DisplayName("Test for DESC sort with first and last name")
    void parseContacts_shouldSortDescContactsWithLastAndFirstName() {
        // Act
        List<Contact> sortedContacts = parseContacts(names, "DESC");

        // Assert
        for (int i = 0; i < sortedContacts.size(); i++) {
            assertThat(sortedContacts.get(i).getFirstName()).isEqualTo(expectedContactsDesc.get(i).getFirstName());
            assertThat(sortedContacts.get(i).getLastName()).isEqualTo(expectedContactsDesc.get(i).getLastName());
        }

    }

    @Test
    @DisplayName("Test sort ASC if no last name sort by first")
    void parseContacts_shouldSortAscByFirstNameWithoutLastName() {
        //Act
        List<Contact> sortedContacts = parseContacts(namesWithoutLastName, "ASC");

        // Assert
        for (int i = 0; i < sortedContacts.size(); i++) {
            assertThat(sortedContacts.get(i).getFirstName()).isEqualTo(expectedContactsWithoutLastNameAsc.get(i)
                .getFirstName());
            assertThat(sortedContacts.get(i).getLastName()).isEqualTo(expectedContactsWithoutLastNameAsc.get(i)
                .getLastName());
        }
    }

    @Test
    @DisplayName("Test sort DESC if no last name sort by first")
    void parseContacts_shouldSortByFirstIfNoLastName() {
        //Act
        List<Contact> sortedContacts = parseContacts(namesWithoutLastName, "DESC");

        // Assert
        for (int i = 0; i < sortedContacts.size(); i++) {
            assertThat(sortedContacts.get(i).getFirstName()).isEqualTo(expectedContactsWithoutLastNameDesc.get(i)
                .getFirstName());
            assertThat(sortedContacts.get(i).getLastName()).isEqualTo(expectedContactsWithoutLastNameDesc.get(i)
                .getLastName());
        }
    }

    @Test
    @DisplayName("Test for null list of names")
    void parseContacts_shouldReturnEmptyListIfNull() {
        // Arrange
        names = null;

        // Act
        List<Contact> sortedContactsDesc = parseContacts(names, "DESC");
        List<Contact> sortedContactsAsc = parseContacts(names, "ASC");

        // Assert
        assertThat(sortedContactsDesc).isEmpty();
        assertThat(sortedContactsAsc).isEmpty();
    }

    @Test
    @DisplayName("Test for empty list of names")
    void parseContacts_shouldReturnEmptyListIfEmpty() {
        // Arrange
        names = new ArrayList<>();

        // Act
        List<Contact> sortedContactsDesc = parseContacts(names, "DESC");
        List<Contact> sortedContactsAsc = parseContacts(names, "ASC");

        // Assert
        assertThat(sortedContactsDesc).isEmpty();
        assertThat(sortedContactsAsc).isEmpty();
    }
}
