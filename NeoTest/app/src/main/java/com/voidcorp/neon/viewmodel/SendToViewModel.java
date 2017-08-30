package com.voidcorp.neon.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ViewDataBinding;
import android.os.Handler;

import com.voidcorp.neon.BR;
import com.voidcorp.neon.data.ContactRepository;
import com.voidcorp.neon.data.TransferRepository;
import com.voidcorp.neon.data.source.ContactDataSource;
import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.data.util.ListUtil;
import com.voidcorp.neon.model.Contact;
import com.voidcorp.neon.model.Transfer;
import com.voidcorp.neon.view.adapter.AdpContact;
import com.voidcorp.neon.view.adapter.BindableViewHolder;

import java.util.List;

import javax.inject.Inject;

public class SendToViewModel extends BaseViewModel implements SendToContract.ViewModelContract, AdpContact.ContactViewModel {

    private final ContactDataSource.OnContactsRetrievedListener mOnContactsRetrievedListener = new ContactDataSource.OnContactsRetrievedListener() {
        @Override
        public void onContactRetrieved(final List<Contact> contacts) {
            SendToViewModel.this.contacts.clear();
            for (final Contact contact : contacts) {
                SendToViewModel.this.contacts.put(contact.getId(), contact);
            }
        }

        @Override
        public void onFail() {

        }
    };

    private final TransferDataSource.OnTransferListener mOnSendTransferListener = new TransferDataSource.OnTransferListener() {
        @Override
        public void onSuccess(final String clientID) {
            mHandler
                    .postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final Long id = Long.valueOf(clientID);
                            final Contact contact = contacts.get(id);
                            if (contact != null) {
                                contact.setTransferStatus(Contact.TRANSFERRED);
                                mHandler
                                        .postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                contact.setTransferStatus(Contact.NONE);
                                            }
                                        }, 1000);
                            }
                        }
                    }, 1000);
        }

        @Override
        public void onFail(final String clientID) {
            final Long id = Long.valueOf(clientID);
            final Contact contact = contacts.get(id);
            if (contact != null) {
                contact.setTransferStatus(Contact.ERROR);
            }
        }
    };


    @Inject
    ContactRepository mContactRepo;

    @Inject
    TransferRepository mTransferRepo;

    public final ObservableArrayMap<Long, Contact> contacts = new ObservableArrayMap<>();

    private final Handler mHandler;

    @Inject
    SendToViewModel() {
        mHandler = new Handler();
    }

    @Override
    public void load() {
        mContactRepo.getContacts(mOnContactsRetrievedListener);
    }

    @Override
    public void send(final int position, final double amount) {
        final Contact contact = ListUtil.getItemAt(contacts, position);
        if (contact != null) {
            contact.setTransferStatus(Contact.TRANSFERRING);
            final String id = String.valueOf(contact.getId());
            mTransferRepo.send(new Transfer(id, amount), mOnSendTransferListener);
        }
    }

    @Override
    public void putValues(final BindableViewHolder holder, final ViewDataBinding viewBinding, final int position) {
        viewBinding.setVariable(BR.model, contacts.valueAt(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public String getImage(final int position) {
        final Contact contact = ListUtil.getItemAt(contacts, position);
        if (contact == null) {
            return null;
        } else {
            return contact.getImage();
        }
    }

    @Override
    public boolean canSend(final int position) {
        final Contact contact = ListUtil.getItemAt(contacts, position);
        return contact != null && (contact.getTransferStatus() == Contact.ERROR || contact.getTransferStatus() == Contact.NONE);
    }

}
