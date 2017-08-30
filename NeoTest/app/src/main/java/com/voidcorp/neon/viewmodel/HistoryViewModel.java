package com.voidcorp.neon.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.voidcorp.neon.BR;
import com.voidcorp.neon.R;
import com.voidcorp.neon.data.ContactRepository;
import com.voidcorp.neon.data.TransferRepository;
import com.voidcorp.neon.data.source.ContactDataSource;
import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.data.util.ListUtil;
import com.voidcorp.neon.model.Contact;
import com.voidcorp.neon.model.Transfer;
import com.voidcorp.neon.view.adapter.AdpContact;
import com.voidcorp.neon.view.adapter.BindableViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class HistoryViewModel extends BaseViewModel implements AdpContact.ContactViewModel {

    private final TransferDataSource.OnTransfersRetrievedListener mOnTransfersRetrievedListener = new TransferDataSource.OnTransfersRetrievedListener() {
        @Override
        public void onTransfersRetrieved(final List<Transfer> transfers) {
            mTransfers = transfers;
            mContactRepo.getContacts(mOnContactsRetrievedListener);
            setData(transfers);
        }

        @Override
        public void onFail() {

        }
    };

    private final ContactDataSource.OnContactsRetrievedListener mOnContactsRetrievedListener = new ContactDataSource.OnContactsRetrievedListener() {
        @Override
        public void onContactRetrieved(final List<Contact> contacts) {
            final List<Transfer> _transfers = new ArrayList<>(mTransfers);
            Collections.sort(_transfers, new Comparator<Transfer>() {
                @Override
                public int compare(final Transfer t1, final Transfer t2) {
                    return Double.compare(t1.getAmount(), t2.getAmount());
                }
            });
            for (final Transfer transfer : _transfers) {
                final int position = contacts.indexOf(new Contact(transfer.getClient()));
                if (position > -1) {
                    Collections.swap(contacts, position, 0);
                }
            }
            HistoryViewModel.this.contacts.clear();
            HistoryViewModel.this.contacts.addAll(contacts);
        }

        @Override
        public void onFail() {

        }
    };

    public final ObservableArrayList<Contact> contacts = new ObservableArrayList<>();

    public final ObservableField<BarData> graphData = new ObservableField<>();

    private List<Transfer> mTransfers;

    @Inject
    TransferRepository mTransferRepo;

    @Inject
    ContactRepository mContactRepo;

    private int mColor;

    @Inject
    HistoryViewModel() {
        mTransfers = new ArrayList<>(0);
    }

    @Override
    public void load() {
        mTransferRepo.getTransfers(mOnTransfersRetrievedListener);
    }

    @Override
    public void putValues(final BindableViewHolder holder, final ViewDataBinding viewBinding, final int position) {
        viewBinding.setVariable(BR.model, contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    private void setData(final List<Transfer> transfers) {
        final List<BarEntry> entries = new ArrayList<>();
        for (final Transfer transfer : transfers) {
            final Float client = Float.valueOf(transfer.getClient());
            final float amount = (float) transfer.getAmount();
            final BarEntry entry = new BarEntry(client, amount);
            entries.add(entry);
        }

        BarDataSet set = new BarDataSet(entries, "Histórico de transferências");
        set.setDrawIcons(false);
        set.setColor(mColor);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);

        graphData.set(data);

    }

    public void setDataSetColor(final int color) {
        mColor = color;
    }

    public int findPositionFor(final float id) {
        return contacts.indexOf(new Contact((long) id));
    }

    public boolean isValid(final int position) {
        return ListUtil.isPositionValid(mTransfers, position);
    }
}
