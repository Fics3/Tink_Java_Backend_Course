package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class ParseContacts {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private ParseContacts() {

    }

    public static List<Contact> parseContacts(List<String> contacts, String sortOrder) {
        if (contacts == null || contacts.isEmpty()) {
            return new ArrayList<>();
        }

        List<Contact> parsedContacts = new ArrayList<>();

        for (String contact : contacts) {
            parsedContacts.add(new Contact(contact));
        }

        sortContacts(parsedContacts, sortOrder);
        return parsedContacts;
    }

    private static void sortContacts(List<Contact> parsedContacts, String sortOrder) {
        if (sortOrder.equals(ASC)) {
            parsedContacts.sort(Comparator.comparing(Contact::getLastName).thenComparing(Contact::getFirstName));
        } else if (sortOrder.equals(DESC)) {
            parsedContacts.sort(Comparator.comparing(Contact::getLastName).reversed()
                .thenComparing(Comparator.comparing(Contact::getFirstName).reversed()));
        } else {
            throw new IllegalArgumentException("Error: choose available type of sort: ASC/DESC");
        }
    }

}
