package edu.hw3.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContactTest {

    @Test
    @DisplayName("Test with last and first name")
    void contact_shouldParseLastAndFirstName() {
        //Arrange
        String name = "David Hume";

        //Act
        Contact contact = new Contact(name);

        //Assert
        assertThat(contact.getFirstName()).isEqualTo("David");
        assertThat(contact.getLastName()).isEqualTo("Hume");
    }

    @Test
    @DisplayName("Test without last name")
    void contact_shouldHandleInputWithoutLastName() {
        //Arrange
        String name = "David";

        //Act
        Contact contact = new Contact(name);

        //Assert
        assertThat(contact.getFirstName()).isEqualTo("David");
        assertThat(contact.getLastName()).isEqualTo("David");
    }
}
