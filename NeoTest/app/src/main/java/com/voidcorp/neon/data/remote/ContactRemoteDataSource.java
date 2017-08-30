package com.voidcorp.neon.data.remote;

import com.voidcorp.neon.data.source.ContactDataSource;
import com.voidcorp.neon.model.Contact;

import java.util.List;

/**
 * Today we don't have an remote connection for contact list this class serves only to accomplish repository patterns
 * */
public class ContactRemoteDataSource implements ContactDataSource {

    public ContactRemoteDataSource() {

    }

    @Override
    public void getContacts(final OnContactsRetrievedListener listener) {

    }

    @Override
    public void save(final Contact contact) {

    }

    @Override
    public void save(final List<Contact> contact) {

    }
}
