package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final class ParseContacts {
    private static final String ASK = "ASC";
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

        if (sortOrder.equals(ASK)) {
            Collections.sort(
                parsedContacts,
                Comparator.comparing(Contact::getLastName).thenComparing(Contact::getFirstName)
            );
        } else if (sortOrder.equals(DESC)) {
            Collections.sort(
                parsedContacts,
                Comparator.comparing(Contact::getLastName).reversed().thenComparing(Contact::getFirstName)
            );

        } else {
            throw new IllegalArgumentException("Error: choose available type of sort: ASC/DESC");
        }
        return parsedContacts;
    }

}
