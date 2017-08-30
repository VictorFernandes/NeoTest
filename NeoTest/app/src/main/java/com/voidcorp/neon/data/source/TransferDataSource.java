package com.voidcorp.neon.data.source;

import com.voidcorp.neon.model.Transfer;

import java.util.List;

public interface TransferDataSource {

    void send(final Transfer transfer, final OnTransferListener listener);

    void getTransfers(final OnTransfersRetrievedListener listener);

    void save(final List<Transfer> transfers);

    interface OnTransferListener {

        void onSuccess(final String clientID);

        void onFail(final String clientID);

    }

    interface OnTransfersRetrievedListener {

        void onTransfersRetrieved(final List<Transfer> transfers);

        void onFail();

    }

}
