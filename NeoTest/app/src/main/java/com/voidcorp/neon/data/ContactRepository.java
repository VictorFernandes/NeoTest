package com.voidcorp.neon.data;

import com.voidcorp.neon.data.source.ContactDataSource;
import com.voidcorp.neon.model.Contact;

import java.util.List;

public class ContactRepository implements ContactDataSource {

    private final ContactDataSource mLocalDataSource;
    private final ContactDataSource mRemoteDataSource;

    public ContactRepository(final ContactDataSource local, final ContactDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    @Override
    public void getContacts(final OnContactsRetrievedListener listener) {
        final OnContactsRetrievedListener _listener = new OnContactsRetrievedListener() {
            boolean skipRemoteIfSuccess = false;

            @Override
            public void onContactRetrieved(final List<Contact> contacts) {
                listener.onContactRetrieved(contacts);
                if (!skipRemoteIfSuccess) {
                    skipRemoteIfSuccess = true;
                    mRemoteDataSource.getContacts(this);
                }
            }

            @Override
            public void onFail() {
                listener.onFail();
            }
        };
        mLocalDataSource.getContacts(_listener);
    }

    @Override
    public void save(final Contact contact) {
        mLocalDataSource.save(contact);
        mRemoteDataSource.save(contact);
    }

    @Override
    public void save(final List<Contact> contacts) {
        mLocalDataSource.save(contacts);
        mRemoteDataSource.save(contacts);
    }

}
