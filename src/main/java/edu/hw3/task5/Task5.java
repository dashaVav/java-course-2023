package edu.hw3.task5;

import java.util.Arrays;

public final class Task5 {
    private Task5() {
    }

    public static Contact[] parseContacts(String[] names, String sortOrder) {
        if (names == null || names.length == 0) {
            return new Contact[0];
        }

        Contact[] contacts = new Contact[names.length];

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].isEmpty()) {
                throw new IllegalArgumentException("Invalid name");
            }
            String[] parts = names[i].split(" ");
            contacts[i] = new Contact(parts[0], parts.length > 1 ? parts[1] : null);
        }

        Arrays.sort(contacts, (contact1, contact2) -> {
            String contact1Compare = contact1.getSurname() == null ? contact1.getName() : contact1.getSurname();
            String contact2Compare = contact2.getSurname() == null ? contact2.getName() : contact2.getSurname();

            if (sortOrder.equals("ASC")) {
                return contact1Compare.compareTo(contact2Compare);
            } else if (sortOrder.equals("DESC")) {
                return contact2Compare.compareTo(contact1Compare);
            } else {
                throw new IllegalArgumentException("Invalid sort order");
            }
        });

        return contacts;
    }
}
