package com.voidcorp.neon.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BindableViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding mViewBinding;

    public BindableViewHolder(final ViewDataBinding binding) {
        super(binding.getRoot());
        mViewBinding = binding;
        mViewBinding.executePendingBindings();
    }

    public ViewDataBinding getViewBinding() {
        return mViewBinding;
    }

}
