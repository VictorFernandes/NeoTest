package com.voidcorp.neon.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BindableAdapter<E extends BindableViewHolder> extends RecyclerView.Adapter<E> {
    @Override
    public E onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayout(viewType), parent, false);
        return (E) new BindableViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final E holder, final int position) {
        onBindViewHolder(holder, holder.getViewBinding(), position);
    }

    protected abstract void onBindViewHolder(final E holder, final ViewDataBinding viewBinding, final int position);

    @LayoutRes
    protected abstract int getLayout(final int viewType);


}
