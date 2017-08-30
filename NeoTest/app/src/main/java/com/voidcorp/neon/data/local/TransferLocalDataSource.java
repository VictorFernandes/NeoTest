package com.voidcorp.neon.data.local;

import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.model.Transfer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class TransferLocalDataSource implements TransferDataSource {

    private final Realm mDatabase;

    public TransferLocalDataSource(final Realm database) {
        mDatabase = database;
    }

    @Override
    public void send(final Transfer transfer, final OnTransferListener listener) {
        // not available for now, maybe some state for later sync
    }

    @Override
    public void getTransfers(final OnTransfersRetrievedListener listener) {
        final RealmResults<Transfer> results = mDatabase.where(Transfer.class).findAll();
        listener.onTransfersRetrieved(results);
    }

    @Override
    public void save(final List<Transfer> transfers) {
//        mDatabase.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(final Realm realm) {
//                realm.copyToRealm(transfers);
//            }
//        });
    }

}
