package edu.hw3.Task5;

public class Contact {
    private final String firstName;
    private final String lastName;

    public Contact(String fullName) {
        String[] parsedName = NameParser.splitFullName(fullName);
        firstName = parsedName[0];
        lastName = parsedName[1];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
