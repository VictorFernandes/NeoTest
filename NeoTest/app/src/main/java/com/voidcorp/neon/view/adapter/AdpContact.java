package com.voidcorp.neon.view.adapter;

import android.databinding.ViewDataBinding;

import com.voidcorp.neon.R;
import com.voidcorp.neon.viewmodel.SendToContract;

import javax.inject.Inject;

public class AdpContact extends BindableAdapter {

    private ContactViewModel mViewModel;

    @Inject
    AdpContact() {

    }

    public void setViewModel(final ContactViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    protected void onBindViewHolder(final BindableViewHolder holder, final ViewDataBinding viewBinding, final int position) {
        mViewModel.putValues(holder, viewBinding, position);
    }

    @Override
    protected int getLayout(final int viewType) {
        return R.layout.view_item_contact;
    }

    @Override
    public int getItemCount() {
        return mViewModel.getItemCount();
    }

    public interface ContactViewModel {

        void putValues(final BindableViewHolder holder, final ViewDataBinding viewBinding, final int position);

        int getItemCount();

    }

}
