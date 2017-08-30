package com.voidcorp.neon.data.local;

import android.support.v4.util.ArrayMap;

import com.voidcorp.neon.data.source.ContactDataSource;
import com.voidcorp.neon.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactLocalDataSource implements ContactDataSource {

    private final ArrayMap<Long, Contact> contacts;

    public ContactLocalDataSource() {
        // today we only use a memory list but with this it can be replaced for realm.
        contacts = new ArrayMap<>(0);
        fillContacts(contacts);
    }

    @Override
    public void getContacts(final OnContactsRetrievedListener listener) {
        listener.onContactRetrieved(new ArrayList<>(contacts.values()));
    }

    @Override
    public void save(final Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    @Override
    public void save(final List<Contact> contacts) {
        for (final Contact contact : contacts) {
            save(contact);
        }
    }

    private void fillContacts(final ArrayMap<Long, Contact> contacts) {

        for (int i = 0; i < 10; i++) {
            final Contact contact = new Contact();
            contact.setId(i + 1);
            contact.setName("Contact" + String.valueOf(i));
            contact.setImage("https://images.pexels.com/photos/307008/pexels-photo-307008.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");

            contacts.put(contact.getId(), contact);

        }

    }

}
