package com.voidcorp.neon.data.remote;

import android.support.v4.util.ArrayMap;

import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.model.Transfer;
import com.voidcorp.neon.net.TransferAPI;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferRemoteDataSource implements TransferDataSource {

    private final TransferAPI mTransferAPI;
    private final String mToken;

    public TransferRemoteDataSource(final TransferAPI api, final String token) {
        mTransferAPI = api;
        mToken = token;
    }

    @Override
    public void send(final Transfer transfer, final OnTransferListener listener) {
        final Call<Boolean> call = mTransferAPI.sendMoney(transfer.getClient(), transfer.getAmount(), mToken);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(final Call<Boolean> call, final Response<Boolean> response) {
                if (response.isSuccessful()) {
                    final Boolean value = response.body();
                    if (value == null) {
                        listener.onFail(transfer.getClient());
                    } else if (value) {
                        listener.onSuccess(transfer.getClient());
                    } else {
                        listener.onFail(transfer.getClient());
                    }
                } else {
                    listener.onFail(transfer.getClient());
                }
            }

            @Override
            public void onFailure(final Call<Boolean> call, final Throwable t) {
                listener.onFail(transfer.getClient());
            }
        });
    }

    @Override
    public void getTransfers(final OnTransfersRetrievedListener listener) {
        final Call<List<Transfer>> call = mTransferAPI.getTransfers(mToken);
        call.enqueue(new Callback<List<Transfer>>() {
            @Override
            public void onResponse(final Call<List<Transfer>> call, final Response<List<Transfer>> response) {
                if (response.isSuccessful()) {
                    final List<Transfer> values = response.body();
                    normalize(values);
                    listener.onTransfersRetrieved(values);
                } else {
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(final Call<List<Transfer>> call, final Throwable t) {
                listener.onFail();
            }

            private void normalize(final List<Transfer> transfers) {
                final ArrayMap<String, Transfer> _transfers = new ArrayMap<>();
                for (final Transfer transfer : transfers) {
                    final Transfer _transfer = _transfers.get(transfer.getClient());
                    if(_transfer == null) {
                        _transfers.put(transfer.getClient(), transfer);
                    } else {
                        final double total = _transfer.getAmount() + transfer.getAmount();
                        _transfer.setAmount(total);
                    }
                }
                transfers.clear();
                transfers.addAll(_transfers.values());
                Comparator<Transfer> comparator = new Comparator<Transfer>() {
                    @Override
                    public int compare(final Transfer t1, final Transfer t2) {
                        return Double.compare(t1.getAmount(), t2.getAmount());
                    }
                };
                comparator = Collections.reverseOrder(comparator);
                Collections.sort(transfers, comparator);
            }

        });
    }

    @Override
    public void save(final List<Transfer> transfers) {
//        TBD
    }

}
