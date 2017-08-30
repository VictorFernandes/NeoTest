package com.voidcorp.neon.data;

import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.model.Transfer;

import java.util.List;

public class TransferRepository implements TransferDataSource {

    private final TransferDataSource mLocalDataSource;
    private final TransferDataSource mRemoteDataSource;


    public TransferRepository(final TransferDataSource local, final TransferDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    @Override
    public void send(final Transfer transfer, final OnTransferListener listener) {
        mRemoteDataSource.send(transfer, listener);
    }

    @Override
    public void getTransfers(final OnTransfersRetrievedListener listener) {
        final OnTransfersRetrievedListener _listener = new OnTransfersRetrievedListener() {
            @Override
            public void onTransfersRetrieved(final List<Transfer> transfers) {
                if (transfers != null && !transfers.isEmpty()) {
                    listener.onTransfersRetrieved(transfers);
                }
                save(transfers);
            }

            @Override
            public void onFail() {
                listener.onFail();
            }
        };
        mRemoteDataSource.getTransfers(_listener);
    }

    @Override
    public void save(final List<Transfer> transfers) {
        mLocalDataSource.save(transfers);
    }

}
