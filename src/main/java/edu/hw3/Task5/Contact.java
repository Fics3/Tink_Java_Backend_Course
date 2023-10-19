package edu.hw3.Task5;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String fullName;

    public Contact(String fullName) {
        this.fullName = fullName;
        String[] lastFirstNames = fullName.split(" ");
        if (lastFirstNames.length > 1) {
            lastName = fullName.split(" ")[1];
            firstName = fullName.split(" ")[0];
        } else {
            firstName = lastFirstNames[0];
            lastName = lastFirstNames[0];
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }
}
