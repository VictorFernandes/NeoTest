package com.voidcorp.neon.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voidcorp.neon.R;
import com.voidcorp.neon.databinding.FragSendToBinding;
import com.voidcorp.neon.view.adapter.AdpContact;
import com.voidcorp.neon.view.util.RecyclerViewItemClickSupport;
import com.voidcorp.neon.view.widget.DialogInputAmount;
import com.voidcorp.neon.viewmodel.SendToViewModel;

import javax.inject.Inject;

public class FragSendTo extends BaseFragment implements ActMain.ChildViewContract {

    private DialogInputAmount.OnAmountChosenListener mOnAmountChosenListener = new DialogInputAmount.OnAmountChosenListener() {
        @Override
        public void onAmountChosen(final double amount, final int position) {
            mViewModel.send(position, amount);
        }
    };

    private final RecyclerViewItemClickSupport.OnItemClickListener mOnItemClickListener = new RecyclerViewItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(final RecyclerView recyclerView, final int position, final View v) {
            if (mViewModel.canSend(position)) {
                final DialogInputAmount dialogInputAmount = new DialogInputAmount(getContext());
                dialogInputAmount.setOnAmountChoosenListener(mOnAmountChosenListener);
                dialogInputAmount.show(mViewModel.getImage(position), position);
            }
        }
    };

    @Inject
    SendToViewModel mViewModel;

    @Inject
    AdpContact mAdapter;

    private FragSendToBinding mBinding;

    @Inject
    public FragSendTo() {
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        getFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        mBinding = FragSendToBinding.inflate(inflater, container, false);
        mBinding.setModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter.setViewModel(mViewModel);
        mBinding.contacts.setAdapter(mAdapter);
        RecyclerViewItemClickSupport.addTo(mBinding.contacts).setOnItemClickListener(mOnItemClickListener);
        mViewModel.load();
    }

    @Override
    public int getTitle() {
        return R.string.send;
    }

}
