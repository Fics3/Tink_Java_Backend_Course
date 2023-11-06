package edu.hw3.Task5;

public class Contact {
    private String firstName;
    private String lastName;
    private final String fullName;

    public Contact(String fullName) {
        this.fullName = fullName;
        splitFullName();
    }

    private void splitFullName() {
        String[] lastFirstNames = fullName.split(" ");
        if (lastFirstNames.length > 1) {
            lastName = lastFirstNames[1];
            firstName = lastFirstNames[0];
        } else {
            firstName = lastFirstNames[0];
            lastName = "";
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
