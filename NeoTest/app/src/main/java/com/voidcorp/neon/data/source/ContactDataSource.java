package com.voidcorp.neon.data.source;

import com.voidcorp.neon.model.Contact;

import java.util.List;

public interface ContactDataSource {

    interface OnContactsRetrievedListener {

        void onContactRetrieved(final List<Contact> contacts);

        void onFail();

    }

    void getContacts(final OnContactsRetrievedListener listener);

    void save(final Contact contact);

    void save(final List<Contact> contact);

}
