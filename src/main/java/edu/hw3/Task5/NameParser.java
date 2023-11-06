package edu.hw3.Task5;

class NameParser {

    private NameParser() {

    }

    public static String[] splitFullName(String fullName) {
        String[] lastFirstNames = fullName.split(" ");
        String firstName;
        String lastName;

        if (lastFirstNames.length > 1) {
            lastName = lastFirstNames[1];
            firstName = lastFirstNames[0];
        } else {
            firstName = lastFirstNames[0];
            lastName = "";
        }
        return new String[] {firstName, lastName};
    }
}
